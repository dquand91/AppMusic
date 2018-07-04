package luongduongquan.com.musicapp.Service;

import java.util.List;

import luongduongquan.com.musicapp.Model.Album;
import luongduongquan.com.musicapp.Model.PlayList;
import luongduongquan.com.musicapp.Model.QuangCao;
import luongduongquan.com.musicapp.Model.TheLoaiTrongNgay;
import retrofit2.Call;
import retrofit2.http.GET;

public interface DataserviceListener {

    // Call<List<QuangCao>>: vì ở đây server trả về response là 1 cái mảng QuangCao => Nên dùng List<QuangCao>.
    // Ở đây là 1 list Object luôn. Nếu muốn 1 Object thôi thì để Call<QuangCao>
    // Dùng GET và gọi API "songbanner.php"
    @GET("songbanner.php")
    Call<List<QuangCao>> getDataBanner();

    @GET("playlistforcurrentday.php")
    Call<List<PlayList>> getPlayListCurrentDay();

    @GET("chudevatheloai.php")
    Call<TheLoaiTrongNgay> getTheLoaiTrongNgay();

    @GET("albumhot.php")
    Call<List<Album>> getListAlbumHost();



}
