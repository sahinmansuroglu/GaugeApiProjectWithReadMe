package helper;

import enums.RequestInfo;
import exceptions.NullResponse;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.StoreApiInfo;

import java.util.concurrent.TimeUnit;

public class ResponseTimeHelper extends ResponseBodyHelper {
    private final Logger log = LogManager.getLogger(ResponseTimeHelper.class);
    private Long responseTime;
    // TODO: 11/6/2022 15. buradan baslanilacak anlatmaya

    /**
     * asagida biz request gonderdikten sonra response ne kadar surede geliyor onu buluruz
     * tabi once requesti gonderdikten sonra storeApiInfoya
     * bilgileri attigimiz icin ordan get ederiz response u
     * request.getTimeIn(TimeUnit.SECONDS);
     */
    protected Long getRequestTimeInMillis() throws NullResponse {
        checkIfResponseNull();
        Response request = (Response) StoreApiInfo.get(RequestInfo.RESPONSE.value);
        responseTime = request.getTimeIn(TimeUnit.MILLISECONDS);
        log.info("Response time is {} milliseconds", responseTime);
        return responseTime;
    }

    protected Long getRequestTimeInSecond() throws NullResponse {
        checkIfResponseNull();
        Response request = (Response) StoreApiInfo.get(RequestInfo.RESPONSE.value);
        responseTime = request.getTimeIn(TimeUnit.SECONDS);
        log.info("Response time is {} seconds", responseTime);
        return responseTime;
    }
}
