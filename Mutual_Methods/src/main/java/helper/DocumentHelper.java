package helper;


import enums.DocumentType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;

import static enums.DocumentType.*;

public class DocumentHelper {
    // TODO: 11/6/2022 3. buradan baslanilacak anlatmaya
    /**
     * asagida documentler ile ilgili update veya json xml mi diye check iceren metodlar bulunur
     */
    private final Logger log = LogManager.getLogger(DocumentHelper.class);

    protected DocumentType isJsonOrXml(String body) {
        if (body.startsWith("<")) {
            return XML;
        } else if (body.startsWith("{") || body.startsWith("[")) {
            return JSON;
        } else {
            log.warn("{}, is not a xml or json", body);
        }
        return UNDEFINED_TYPE;
    }

    /**
     *     update document en son bodyi istenen selector ve new valuesina gore degistirir sonrada yollamak mistersek yollariz
     *     json veya xml olarak ayirt edip ona gore degistirir ikiside degilse undefined type der!
     *
     *
     * @param body
     * @param selector
     * @param newValue
     * @return
     */
    public Object updateDocument(String body, String selector, String newValue) {
        JsonHelper jsonHelper = new JsonHelper();
        if (isJsonOrXml(body) == XML) {
            XmlHelper xmlHelper = new XmlHelper();
            Document newBody = xmlHelper.updateXmlNodesByXpath(body, selector, newValue);
            return xmlHelper.docToXmlString(newBody);
        } else if (isJsonOrXml(body) == JSON) {
            return jsonHelper.updateJsonValue(body, selector, newValue);
        } else if (isJsonOrXml(body) == UNDEFINED_TYPE) {
            return body;
        } else {
            return null;
        }
    }
}
