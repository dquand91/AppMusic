package luongduongquan.com.musicapp.Service;

import java.util.List;

import luongduongquan.com.musicapp.Model.QuangCao;
import retrofit2.Call;
import retrofit2.http.GET;

public interface DataserviceListener {

    // Call<List<QuangCao>>: vì ở đây server trả về response dạng List<QuangCao>. Nên dùng List<QuangCao>.
    // Ở đây là 1 list Object luôn. Nếu muốn 1 Object thôi thì để Call<QuangCao>
    // Dùng GET và gọi API "songbanner.php"
    @GET("songbanner.php")
    Call<List<QuangCao>> getDataBanner();

}
