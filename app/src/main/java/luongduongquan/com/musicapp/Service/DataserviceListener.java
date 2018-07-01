package luongduongquan.com.musicapp.Service;

import java.util.List;

import luongduongquan.com.musicapp.Model.QuangCao;
import retrofit2.Call;
import retrofit2.http.GET;

public interface DataserviceListener {

    @GET("songbanner.php")
    Call<List<QuangCao>> getDataBanner();

}
