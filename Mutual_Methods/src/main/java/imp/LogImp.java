package imp;


import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import com.thoughtworks.gauge.TableRow;
import com.thoughtworks.gauge.datastore.ScenarioDataStore;
import helper.FilterHelper;
import helper.ParseHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;


public class LogImp extends FilterHelper {

    // TODO: 11/10/2022 helperlar bittikten sonra 6.sira

    private final Logger log = LogManager.getLogger(LogImp.class);

    /**
     * Biz iki sekilde rest assured filter kullanabiliyoruz
     * @param table
     * ilki spece asagidaki gibi yazariz
     * * Add log filter with errorStatus
     *     |Status |
     *     |500    |
     *     |400    |
     *     |405    |
     *     eger request 500 400 405 olarak donerse error diger turlu info olarak loglayacak
     *     biz asagidaki filteri bi concept haline getirip gelecek tum status codlari ekletiriz error log diye
     *    istegi atmadan once rest assured asagidaki log filterlari bilir ve ona gore doner
     *
     */
    @Step({"Add log filter with errorStatus <table>", "Bu statü kodları için log filtresi ekle <table>"})
    public void addFilter(Table table) {
        ParseHelper parseHelper = new ParseHelper();
        List<TableRow> rows = table.getTableRows();
        var statusCodes = rows
                .stream()
                .map(row -> parseHelper.parsStringToInt(row.getCellValues().get(0))).toList();
        Integer[] status = new Integer[statusCodes.size()];
        statusCodes.toArray(status);
        addCustomLogFilter(status);
    }

    public void addFilter(List<Integer> statusCodes) {
        ParseHelper parseHelper = new ParseHelper();
        Integer[] status = new Integer[statusCodes.size()];
        statusCodes.toArray(status);
        addCustomLogFilter(status);
    }


    /**
     * asagidaki startLog metodunda biz disardan istedigimiz sekilde log yazabiliyoruz
     * @param log
     */
    @Step({"Log <log>", "Logla <log>"})
    public void startLog(String log) {
        this.log.info(log);
    }
    /**
     * asagidaki startLog metodunda biz disardan istedigimiz sekilde logu scenario storedan get ile alabiliriz
     */
    @Step({"Log this param <key>", "Kayıtlı parametreyi logla <key>"})
    public void logThisParam(String key) {
        this.log.info(ScenarioDataStore.get(key));
    }
}
