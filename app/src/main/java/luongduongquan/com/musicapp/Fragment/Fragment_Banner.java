package luongduongquan.com.musicapp.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import luongduongquan.com.musicapp.Adapter.BannerAdapter;
import luongduongquan.com.musicapp.Model.QuangCao;
import luongduongquan.com.musicapp.R;
import luongduongquan.com.musicapp.Service.APIServiceUtils;
import luongduongquan.com.musicapp.Service.DataserviceListener;
import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Fragment_Banner extends Fragment {

    final String TAG = this.getClass().getSimpleName();

    View view;
    ViewPager viewPager;
    CircleIndicator circleIndicator;
    BannerAdapter bannerAdapter;

    Runnable runnable;
    Handler handler;
    int currentItem;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_banner, container, false);
        initView();

        getData();

        return view;
    }

    private void initView() {

        viewPager = view.findViewById(R.id.viewPager_banner);
        circleIndicator = view.findViewById(R.id.circleIndicator_banner);

    }

    public void getData() {

        DataserviceListener dataserviceListener = APIServiceUtils.getDataFromService();

        Call<List<QuangCao>> callback = dataserviceListener.getDataBanner();
        Log.d(TAG, "getData: " );
        callback.enqueue(new Callback<List<QuangCao>>() {
            @Override
            public void onResponse(Call<List<QuangCao>> call, Response<List<QuangCao>> response) {
                ArrayList<QuangCao> bannerList = (ArrayList<QuangCao>) response.body();
                bannerAdapter = new BannerAdapter(getActivity(), bannerList);
                viewPager.setAdapter(bannerAdapter);
                circleIndicator.setViewPager(viewPager);

                handler = new Handler();
                runnable = new Runnable() {
                    @Override
                    public void run() {
                        currentItem = viewPager.getCurrentItem();
                        currentItem++;
                        if(currentItem >= viewPager.getAdapter().getCount()){
                            currentItem =0;
                        }
                        viewPager.setCurrentItem(currentItem, true);
                        handler.postDelayed(runnable, 4500);
                    }
                };
                handler.postDelayed(runnable, 4500);
            }

            @Override
            public void onFailure(Call<List<QuangCao>> call, Throwable t) {

            }
        });

    }
}
