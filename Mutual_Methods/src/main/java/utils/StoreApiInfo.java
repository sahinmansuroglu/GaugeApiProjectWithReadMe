package utils;

import java.util.concurrent.ConcurrentHashMap;

public class StoreApiInfo {
    /**
     * senaryo boyunca bizim api ve response bilgilerimizi tutuyoruz(threade ozel tutuyor).Sonra istedigimiz zaman cagirabiliyoruz Diger threadler bunu ezmiyor
     * sadece bu threadlocal objesini remove etmemiz lazim isimiz bittikten sonra static oldugundan daha sonra ulasilabilir bu bilgilere
     *
     *  var r= new ConcurrentHashMap<String,Object>();
     *  var t= new ThreadLocal<ConcurrentHashMap<String,Object>>()
     *  biz t.set(r) diyerek thread lokale r yi ekleriz ayni tipte zaten ConcurrentHashMap. sonrada t.get diyerek onu geri aliriz.threadlocal bir map degil icinde get() set() metodlari mevcut
     *
     *  biz asagida MAP_THREAD_LOCAL objecsi yarattik thread local classindan ConcurrentHashMap tipinde o sebeple once threadlocain icine atadigimizi get ile alip sonra map metodlarini kullan
     *  ama get dedikten sonra mapin metodlari gelir icinde ConcurrentHashMap metodlari var cunku
     *  MAP_THREAD_LOCAL.get().put(key,value)
     *
     *  eger var e= new ThreadLocal<Integer>() deseydik bize put metodu gelmezdi cunku threadlocal icindeki integer su anda map degil
     *
     */
    private static final ThreadLocal<ConcurrentHashMap<String, Object>> MAP_THREAD_LOCAL = ThreadLocal.withInitial(ConcurrentHashMap::new);

    private StoreApiInfo() {
        throw new IllegalStateException("Utility class");
    }


    /**
     * direk mapi kullanamiyoruz once get ile map getirirp sonra islemini yapariz
     */
    public static synchronized void put(String key, Object value) {

        if (key != null && value != null) {
            MAP_THREAD_LOCAL.get().put(key, value);
        }

    }

    /**
     * asagida ise biz keyini verdigimiz map objesini kaldirir tum map objelerini silmez!!!
     * @param key
     * @return
     */
    public static synchronized Object remove(Object key ) {
        return key != null ? MAP_THREAD_LOCAL.get().remove(key) : null;
    }
    /**
     * asagida biz isimizi bitirdikten sonra direk thread localin remove uni cagirip o threadlocalin icindeki hashmapleri siler
     * @return
     * mesela biz storeAPi infoya reqeust response infoyu attik biz asagidaki remove u kullanirsak request/response gider cunku icindeki mapler silinecek
     */
    public static synchronized void remove() {
        MAP_THREAD_LOCAL.remove();
    }

    /**
     *burda yapilan eger key bos degilse o keyi get ediyor
     */
    public static synchronized Object get(Object key) {
        return key != null ? MAP_THREAD_LOCAL.get().get(key) : null;
    }

    public static synchronized void clear() {
        MAP_THREAD_LOCAL.get().clear();
    }
}
