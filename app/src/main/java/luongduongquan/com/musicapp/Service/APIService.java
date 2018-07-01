package luongduongquan.com.musicapp.Service;

public class APIService {

    private static String base_url = "https://dquand91.000webhostapp.com/Server/";

    public static DataserviceListener getDataFromService(){
        return APIretrofitClient.getClient(base_url).create(DataserviceListener.class);
    }

}
