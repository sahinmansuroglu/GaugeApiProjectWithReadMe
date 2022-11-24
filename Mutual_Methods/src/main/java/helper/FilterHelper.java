package helper;

import filter.RestAssuredFilter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.stream.Collectors;

public class FilterHelper {

    private final Logger log = LogManager.getLogger(FilterHelper.class);

    /**
     * biz RestAssuredFilteri kendi yarattigimiz yani 2 sekilde ekleyebiliriz
     *
     * onceden biz defineNewRequestIfNull() metodu koyuyorduk
     * sonrada RequestSpecification request diyip sonra request.filter diyorduk
     * ama rest assured in given i zaten static ondan dolayi buna gerek yok7
     * direk get instancedan requesti alip filter cagiririz ApiHelper.getInstance().getRequestSpecification().filter(filter)
     */
    protected void addCustomLogFilter(Integer... statusCode) {
        ApiHelper.getInstance().getRequestSpecification().filter(new RestAssuredFilter(statusCode));
        log.info("Status added to log filter {}", Arrays.stream(statusCode)
                .map(String::valueOf)
                .collect(Collectors.joining(", ")));
    }


    protected void addFilter(RestAssuredFilter filter) {
        ApiHelper.getInstance().getRequestSpecification().filter(filter);
    }
}
