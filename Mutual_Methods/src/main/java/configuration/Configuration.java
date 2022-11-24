package configuration;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.util.Properties;

import static enums.PropertiesType.*;

public class Configuration {
    // TODO: 11/6/2022 1.BURAYA ILK BASTA DEGIN
    /**
     * config dosyasi kisaca proje ayaga kalktiginda ilk olarak config prop icerisindeki datalari okuyor istersek bu config proptaki paramlari ezebiliriz
     * -DdbClass="test" dersek bunu aldirabiliriz  yani config proptan almaz boylece onu ezer verdigimizde.   mvn -Denv=dev diyebiliriz yani run ederken
     * System.getProperty("env") dedigimde -Denv=dev    dev getirecek cunku ben disardan dev verdim. Ama configProps = new Properties() dersem disardan verilen hersey siifirlanir
     * cunku yeniden initalize etmis oldum properties i new dedigim icin
     *
     *
     */
    private static Configuration instance;
    Properties configProps;
    protected static final Logger log = LogManager.getLogger(Configuration.class);
    static final String PROP_FILE_NAME = "config.properties";
    private String slackToken;
    private String connectionString;
    private String dbClass;
    private String dbPassword;
    private String dbUser;
    private String webhook;

    public static Configuration getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }

    private static synchronized void createInstance() {
        if (instance == null) {
            instance = new Configuration();
        }
    }

    /**
     * ClassLoader.getSystemResourceAsStream  --> java en basta ayaga kaldiriyor bizde onu cagirdigimizda en basta bunu kullanabiliyoruz
     * once disardan verilmis propertyleri configProps ta tutuyorum
     */
    private Configuration() {
        try (InputStream is = ClassLoader.getSystemResourceAsStream(PROP_FILE_NAME)) {
            configProps = new Properties();
            configProps.load(is);
            slackToken = configProps.getProperty(SLACK_TOKEN.getText());
            connectionString = configProps.getProperty(CONNECTION_STRING.getText());
            dbClass = configProps.getProperty(DB_CLASS.getText());
            dbUser = configProps.getProperty(DB_USER.getText());
            dbPassword = configProps.getProperty(DB_PASSWORD.getText());
            webhook = configProps.getProperty(WEBHOOK.getText());
        } catch (Exception e) {
            log.error(e);
        } finally {
            log.info("Properties read finished.");
        }
    }

    public String getSlackToken() {
        return slackToken;
    }

    public String getConnectionString() {
        return connectionString;
    }

    public String getDbClass() {
        return dbClass;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public String getDbUser() {
        return dbUser;
    }

    public String webhook() {
        return webhook;
    }

    /**
     * burda da o property nin string value sini aliyorum veya integer value sini aliyorum
     * @param propKey
     * @return
     */
    public String getStringValueOfProp(String propKey) {
        return configProps.getProperty(propKey);
    }

    /**
     * eger istenen property null degilse integerea ceviriyorum
     * @param propKey
     * @return
     */
    public Integer getIntegerValueOfProp(String propKey) {
        var portString = configProps.getProperty(propKey);
        return portString != null ? Integer.parseInt(portString) : null;
    }
}