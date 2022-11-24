package enums;

public enum RequestInfo {
    REQUEST("request"),
    RESPONSE("response");

    public final String value;

    RequestInfo(String value) {
        this.value = value;
    }
}
/**
 *StoreApiInfo.get(RequestInfo.REQUEST.value);  bu komutla enuma ulasip constructor ile icindeki istedigimiz value yi public olan value ile aliriz
 */