package helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public class XmlHelper {

    /**
     * xml i aliyor icinden guncellemek istedigi seyi guncelleyip xml i tekrar olusturuyor
     * daha sonra bizim guncellemek istedigimiz yolu/elementi bulup guncelliyip tekrar eski haline getiriyor
     * xml yolladigin api var ama iki uc tane test senaryon var her test senaryosunda farkli parametre yollayacaksin bunun icin birden fazla xml yaratixcaksin onun yerine
     * senin xml template in olur ve her senaryo icin  sen valuelari degistireceksin
     *
     *   *  <Appenders>
     *            <RollingFile name="fileLogger" fileName="${basePath}/gauge_api_testing.log"
     *      *                      filePattern="${basePath}/dailyLog-%d{yyyy-MM-dd}-%i.log">
     *      *             <PatternLayout>
     *      *                 <pattern>[%-5level] %d{yyyy-MM-dd HH:mm:ss.SSS} %c{1}:%L - %msg%n</pattern>
     *      *             </PatternLayout>
     *      *             <Policies>
     *      *                 <TimeBasedTriggeringPolicy interval="1" modulate="true"/>
     *      *                 <SizeBasedTriggeringPolicy size="5 MB" />
     *      *             </Policies>
     *      *         </RollingFile>
     *      *
     *      *
     *      *         diyelim yukaridaki benim body deki yollayacagim xml olsun
     *      *         mesela bir testimi yukardaki data pattrninde yollamak istiyorum baska testtede baska patternde yollamak istiyorum
     *      *         ben simdi test yazarken resourcedan xml i okuyup yollayacam farkli pattern kullanacagim casede ilk xml i kullanamam pattern farkli baska xml olmasi lazim
     *      *         iki yol mevcut patterne $li isaret koyabilirsin string parse edersin yada xpath ile parent child vs
     *                o elementa/patterne ulasabilirsin sonra onun value sini alip guncelleyip geri ekliyorsun xml e
     *
     */

    private final Logger log = LogManager.getLogger(XmlHelper.class);

    /**
     * Convert string object to xml file.
     *
     * @param xml String xml
     * @return Document
     * bu method xml i aliyor ve parcalara ayirmani sagliyor veya xml i java objesi gibi kullanmani sagliyor bunlada xml e ulasip icini guncelliyorsun
     * DocumentBuilderFactory sifirdan bir dokumant builder olusturuyor ama icinde xml yok su anda xml i de gidip resourcedan okuruz
     * asagidada biz xml i document builder ile alip new InputSource(new StringReader(xml)) ile okuyoruz
     * string olan xml dosyasini builderda document builder ile Document yani javanin bir objesi haline getiriyoruz
     */
    public Document stringXmlToDoc(String xml) {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = builderFactory.newDocumentBuilder();
            return builder.parse(new InputSource(new StringReader(xml)));
        } catch (ParserConfigurationException | SAXException | IOException e) {
            log.error("Xml file couldn't convert to xml.");
        }
        return null;
    }

    /**
     * find element by xpath from xml or document file and return the element nodes.
     *
     * @param xPath Xpath for searching element
     * @param doc   xml or html document for searching.
     * @return found nodes
     *
     * asagida once xpath kullanabilmem icin disardan XPathFactory.newInstance().newXPath() getiriyorum
     * toparlarsak disardan bana string olarak gelen xpath ile birlikte xpath factory kullanilarak dokuman icerisindeki node yani patterni buluyoruz
     * (seleniumdaki web element bulma gibi birsey)
     */
    private NodeList getNodesFromXmlDocByXpath(String xPath, Document doc) {
        try {
            XPath xpath = XPathFactory.newInstance().newXPath();
            return (NodeList) xpath.evaluate(xPath, doc, XPathConstants.NODESET);
        } catch (XPathExpressionException e) {
            log.error(" Couldn't find element by {} from {}", doc, xPath);
        }
        return null;
    }

    /**
     * Finds the element by xpath and then update with new value.
     *
     * @param xml      The xml file to update
     * @param xPath    Xpath for searching element
     * @param newValue new value to update
     * @return updated document.
     *
     * once disardan guncellemek istedigim xml i string olarak aldim zaten dokumana kendi cevirecek
     * sonra derlemek istedigimiz nodu alacaz node xml in icindeki baktigimiz pattern mesela bir node
     * getNodesFromXmlDocByXpath bu metodun icine gidip guncellemek istedigimiz nodu getiriyor bize
     * xpath guncellemek istedigimiz nodu buluyor ben icine xpathi verip documani verip bana nodu geri donuyor
     * en asagidada yeni valuelari koyuyoruz biz iki valueeyuda guncelledik setTextContent htmldeki get text gibi  setNodeValue ise get attribute gibi guncelleme yapiyor
     */
    protected Document updateXmlNodesByXpath(String xml, String xPath, String newValue) {
        Document doc = stringXmlToDoc(xml);
        NodeList nodes = getNodesFromXmlDocByXpath(xPath, doc);

        for (int idx = 0; idx < nodes.getLength(); idx++) {
            nodes.item(idx).setTextContent(newValue);
            nodes.item(idx).setNodeValue(newValue);
        }
        return doc;
    }

    /**
     * converts to document object to string
     *
     * @param doc is document to convert to string
     * @return is document as string
     *
     * biz xml i reste dokuman olarak ekleyemiyoruz bu sebeple geri stringe cevirmek lazim
     */
    protected String docToXmlString(Document doc) {
        try {

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(doc), new StreamResult(writer));
            return writer.getBuffer().toString().replaceAll("[\n\r]", "");
        } catch (TransformerException e) {
            log.error("{},document couldn't convert to string", doc);
        }
        return null;
    }

}
//