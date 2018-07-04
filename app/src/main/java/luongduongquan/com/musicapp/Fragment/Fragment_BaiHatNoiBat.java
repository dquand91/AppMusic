package luongduongquan.com.musicapp.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import luongduongquan.com.musicapp.Adapter.BaiHatHotAdapter;
import luongduongquan.com.musicapp.Model.BaiHat;
import luongduongquan.com.musicapp.R;
import luongduongquan.com.musicapp.Service.APIServiceUtils;
import luongduongquan.com.musicapp.Service.DataserviceListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_BaiHatNoiBat extends Fragment {
	View view;
	RecyclerView recyclerViewBaiHatHot;
	BaiHatHotAdapter baiHatHotAdapter;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		view = inflater.inflate(R.layout.fragment_bai_hat_noi_bat, container, false);
		recyclerViewBaiHatHot = view.findViewById(R.id.recyclerView_BaiHatHot);
		getData();
		return view;
	}

	private void getData() {

		DataserviceListener dataserviceListener = APIServiceUtils.getDataFromService();

		Call<List<BaiHat>> callback = dataserviceListener.getListBaiHatNoiBat();
		callback.enqueue(new Callback<List<BaiHat>>() {
			@Override
			public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
				ArrayList<BaiHat> listBaiHat = (ArrayList<BaiHat>) response.body();

				baiHatHotAdapter = new BaiHatHotAdapter(getActivity(), listBaiHat);

				LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
				linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
				recyclerViewBaiHatHot.setLayoutManager(linearLayoutManager);
				recyclerViewBaiHatHot.setAdapter(baiHatHotAdapter);


			}

			@Override
			public void onFailure(Call<List<BaiHat>> call, Throwable t) {

			}
		});
	}

}
