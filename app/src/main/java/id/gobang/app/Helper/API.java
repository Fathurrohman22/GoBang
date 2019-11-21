package id.gobang.app.Helper;

public class API {

    public static final String KEY = "gobangapp";
    private static final String INTI_URL = "http://api.gobang.kejari-purwokerto.go.id/";
    private static final String REQUEST = INTI_URL + "request/";
    public static final String URL_CEK_DATA = REQUEST + "cek_data";
    public static final String URL_PERMINTAAN = REQUEST + "add_permintaan";

    private static final String URL_API_DAERAH_INDONESIA = "http://dev.farizdotid.com/api/daerahindonesia/";
    public static final String URL_PROVINSI = URL_API_DAERAH_INDONESIA + "provinsi";

    public static String URL_KABUPATEN(String ID_PROVINSI){
        return URL_API_DAERAH_INDONESIA + "provinsi/" + ID_PROVINSI + "/kabupaten";
    }

    public static String URL_KECAMATAN(String ID_KABUPATEN){
        return URL_API_DAERAH_INDONESIA + "provinsi/kabupaten/" + ID_KABUPATEN + "/kecamatan";
    }

    public static String URL_DESA(String ID_KECAMATAN){
        return URL_API_DAERAH_INDONESIA + "provinsi/kabupaten/kecamatan/" + ID_KECAMATAN + "/desa";
    }
}
