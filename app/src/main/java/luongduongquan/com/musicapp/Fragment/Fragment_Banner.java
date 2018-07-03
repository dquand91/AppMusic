package luongduongquan.com.musicapp.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import luongduongquan.com.musicapp.Model.QuangCao;
import luongduongquan.com.musicapp.R;
import luongduongquan.com.musicapp.Service.APIServiceUtils;
import luongduongquan.com.musicapp.Service.DataserviceListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Fragment_Banner extends Fragment {

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_banner, container, false);

//        getData();

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getData();
    }

    public void getData() {

        DataserviceListener dataserviceListener = APIServiceUtils.getDataFromService();

        Call<List<QuangCao>> callback = dataserviceListener.getDataBanner();
        callback.enqueue(new Callback<List<QuangCao>>() {
            @Override
            public void onResponse(Call<List<QuangCao>> call, Response<List<QuangCao>> response) {
                ArrayList<QuangCao> bannerList = (ArrayList<QuangCao>) response.body();
                Log.d("QUAN123", "onResponse: " + bannerList.get(0).getTenBaiHat());
            }

            @Override
            public void onFailure(Call<List<QuangCao>> call, Throwable t) {

            }
        });

    }
}
