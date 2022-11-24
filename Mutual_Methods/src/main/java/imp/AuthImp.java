package imp;

import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.datastore.ScenarioDataStore;
import com.thoughtworks.gauge.datastore.SpecDataStore;
import com.thoughtworks.gauge.datastore.SuiteDataStore;
import helper.AuthHelper;

public class AuthImp extends AuthHelper {
    // TODO: 11/10/2022 helperlar bittikten sonra 1.sira

    /**
     * Buranin mantigi specten/feature dan verilen keywordleri
     * yani username/pwd leri basic aut helperi yada bearer etc methodlari call edip
     * authentication bilgisini requestspece ekliyor yani burda  requesti gondermiyoruz daha
     * onu biz methods kisminda yapiyoruz get/post/put gibi
     *
     * @param user
     * @param password
     */



    @Step({"Basic auth with <username> and <password>",
            "Kullanıcı adı: <username>, Şifre <password> ile temel yetkilendirme yap"})
    public void basic(String user, String password){
        basicAuth(user, password);
    }

    @Step({"Basic auth with <username> and <password> as preemptive",
            "Kullanıcı adı: <username>, Şifre <password> ile preemptive temel yetkilendirme yap"})
    public void basicAuthWithPreemptive(String user, String password)  {
        preBasicAuth(user, password);
    }

    @Step({"Add Bearer token <token>",
            "Bearer token ekle <token>"})
    public void bearerAuth(String token) {
        addBearerToken(token);
    }

    /**
     * eger istersek o tokeni daha once key ile ScenarioDataStore a attiysak ordan cekebiliriz
     * @param key
     */
    @Step({"Add Bearer token from scenario store <key>",
            "Senaryo store'dan Bearer token ekle <token>"})
    public void bearerAuthFromScenarioStore(String key)  {
        String token = String.valueOf(ScenarioDataStore.get(key));
        addBearerToken(token);
    }
    /**
     * eger istersek o tokeni daha once key ile SuiteDataStore a attiysak ordan cekebiliriz
     *
     */
    @Step({"Add Bearer token from suit store <key>",
            "Suit store'dan Bearer token ekle <token>"})
    public void bearerAuthFromSuitStore(String key) {
        String token = String.valueOf(SuiteDataStore.get(key));
        addBearerToken(token);
    }

    /**
     * biz SpecDataStore.get(key));  ister spec ister suit isterse scenario boyunca istedigimiz degeri tutup geri cagirabiliriz icinden
     * @param key
     */
    @Step({"Add Bearer token from spec store <key>",
            "Spec store'dan Bearer token ekle <token>"})
    public void bearerAuthFromSpecStore(String key)  {
        String token = String.valueOf(SpecDataStore.get(key));
        addBearerToken(token);
    }
}
