package helper;

import io.restassured.builder.MultiPartSpecBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class MultiPartFormDataParametersHelper {
    /**
     * eger requeste pdf jpegler  vs eklemek istersek bunu kullaniriz
     * its add one file or more than one file
     */
    private final Logger log = LogManager.getLogger(MultiPartFormDataParametersHelper.class);
    private static final String LOG_INFO = "{} = {} add to request as multi-part form data";

    /**
     * it adds the file without any mim type
     * @param file
     */
    protected void addMultiPartFormData(File file) {
        ApiHelper.getInstance().getRequestSpecification().multiPart(file);
        log.info("{} add to request as multi-part form data", file.getName());
    }

    /**
     * Bazen sen sadece file eklersin ve key eklemen gerekebilir ve asagida gordugumuz gibi biz mim type i file a gore aldik!
     * @param key
     * @param file
     */
    protected void addMultiPartFormData(String key, File file) {
        String mimeType = new FileHelper().getFileMimeType(file);
        ApiHelper.getInstance().getRequestSpecification().multiPart(
                new MultiPartSpecBuilder(file)
                        .fileName(file.getName())
                        .controlName(key)
                        .mimeType(mimeType)
                        .build()
        );
        log.info(LOG_INFO, key, file.getName());
    }

    /**
     * bazen filesiz sekilde direk value olarak multipart data gonderebiliriz
     * cunku bazen path gerekli olur bazenden string valuesi yeterli olabilir
     * @param key
     * @param value
     */
    protected void addMultiPartFormData(String key, String value) {
        ApiHelper.getInstance().getRequestSpecification().multiPart(key, value);
        log.info(LOG_INFO, key, value);
    }

    protected void addMultiPartFormData(String key, Object object) {
        ApiHelper.getInstance().getRequestSpecification().multiPart(key, object);
        log.info(LOG_INFO, key, object.getClass().getName());
    }
}
