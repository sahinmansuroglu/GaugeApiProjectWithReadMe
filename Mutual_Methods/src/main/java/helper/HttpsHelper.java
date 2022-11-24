package helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HttpsHelper {
    // TODO: 11/6/2022 6. buradan baslanilacak anlatmaya
    private final Logger log = LogManager.getLogger(HttpsHelper.class);

    /**
     * bazen request handshake hatasi aliyor bunu cozmek icin relaxedHTTPSValidation() kullaniriz
     */
    protected void addRelaxedHTTPSValidation(){
        ApiHelper.getInstance().getRequestSpecification().relaxedHTTPSValidation();
        log.info("relaxedHTTPSValidation added");
    }



}
