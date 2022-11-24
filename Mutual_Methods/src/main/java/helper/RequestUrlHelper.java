package helper;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RequestUrlHelper {
    // TODO: 11/6/2022 14. buradan baslanilacak anlatmaya

    /**
     *Asagida requestimize base url veya base path yani endpointimizi ekleriz!
     * sonrada bunu ulr impte cagiririz
     */
    private final Logger log = LogManager.getLogger(RequestUrlHelper.class);

    protected void addBaseUrl(String baseUrl) {
        ApiHelper.getInstance().getRequestSpecification().baseUri(baseUrl);
        log.info("{} added as a base url", baseUrl);
    }

    protected void addBasePath(String endpoint) {
        ApiHelper.getInstance().getRequestSpecification().basePath(endpoint);
        log.info("{} added as a base path", endpoint);
    }
}
