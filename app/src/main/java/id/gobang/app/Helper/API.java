package id.gobang.app.Helper;

public class API {

    public static final String USERNAME = "gobangapp";
    public static final String PASSWORD = "92aa502a8323c9a4ffb155615158b0f3";

//    public static final String KEY = "gobangapp";
//    private static final String INTI_URL = "http://api.gobang-dev.kejari-purwokerto.go.id/";
//    private static final String INTI_URL = "http://kejari-purwokerto.kejaksaan.go.id/gobang/api/";
    private static final String INTI_URL = "http://api.gobang.id/";
    private static final String REQUEST = INTI_URL + "request/";
    public static final String URL_CEK_DATA = REQUEST + "cek_data";
    public static final String URL_PERMINTAAN = REQUEST + "add_permintaan";

    private static final String URL_API_DAERAH_INDONESIA = "https://dev.farizdotid.com/api/daerahindonesia/";
    public static final String URL_PROVINSI = URL_API_DAERAH_INDONESIA + "provinsi";

    public static String URL_KABUPATEN(String ID_PROVINSI){
        return URL_API_DAERAH_INDONESIA + "kota/?id_provinsi=" + ID_PROVINSI;
    }

    public static String URL_KECAMATAN(String ID_KABUPATEN){
        return URL_API_DAERAH_INDONESIA + "kecamatan?id_kota=" + ID_KABUPATEN;
    }

    public static String URL_DESA(String ID_KECAMATAN){
        return URL_API_DAERAH_INDONESIA + "kelurahan?id_kecamatan=" + ID_KECAMATAN;
    }
}
