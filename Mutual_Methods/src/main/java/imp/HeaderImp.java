package imp;


import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import exceptions.RequestNotDefined;
import helper.HeaderHelper;
import utils.Utils;

import java.util.Map;


public class HeaderImp extends HeaderHelper {
// TODO: 11/10/2022 helperlar bittikten sonra 3.sira
    /**
     *asagidada ayni sekilde
     *
     */
    @Step({"Add as a header <key> = <value>", "Header ekle <key> = <value>"})
    public void addHeaderToReq(String key, String value) {
        addHeader(key, value);
    }
    /**
     * asagida utils classimizdan faydalaniyoruz amacimiz biz table olarak ekledigimizde headerslari onu mape ceviriyor
     * Add Headers
     |key            |value              |
     |---------------|-------------------|
     |accept         |application/json   |
     |Content-Type   |application/json   |
     |Cache-Control  |max-age=0          |
     */
    @Step({"Add Headers <table>", "Header Ekle <TableRow>"})
    public void addHeadersToReq(Table table) {
        Utils utils = new Utils();
        Map<String, Object> headers = utils.gaugeDataTableToMap(table);
        addHeaders(headers);
    }

    /**
     * Cucumberda direk map olarak alabiliyorsun table i ama bunun icin cucumberin ayri bir sinifi var ordan
     * ayarliyorsun data tableconfig diye bir class onu override metod yazdiginda parametre olarak aldirabiliyoruz
     * @param map
     */
    public void addFormParametersFromTable(Map<String, Object> map) {
        addHeaders(map);
    }

    @Step({"Add SOAPAction <action>", "SOAPAction ekle <action>"})
    public void addSOAPActionToReq(String action) throws RequestNotDefined {
        addSOAPAction(action);
    }
    @Step({"Set contentType is JSON"})
    public void setContentTypeJsonImp() {
        addContentTypeJson();
    }

    @Step({"Add multi-part data as content-type to header with default boundary <boundary>",
            "Multi-part verileri, ekleyin header varsayılan boundary <boundary> ile"})
    public void addMultipleDataContentTypeAsHeader(String boundary) {
        addMultiPartContentType(boundary);
    }


}
