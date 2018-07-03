package luongduongquan.com.musicapp.Service;

public class APIServiceUtils {

    // base_url này phải là 1 địa chỉ kết thuc bằng dấu /
    // Ví dụ: base_url = "http://jsonplaceholder.typicode.com/"
     private static String base_url = "http://dquand91.000webhostapp.com/Server/";
    // private static String base_url = "http://phatdroid94com.000webhostapp.com/Server/";


    public static DataserviceListener getDataFromService(){
        return APIretrofitClient.getClient(base_url).create(DataserviceListener.class);
    }

}
