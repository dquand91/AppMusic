package luongduongquan.com.musicapp.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import luongduongquan.com.musicapp.Adapter.SeachAdapter;
import luongduongquan.com.musicapp.Model.BaiHat;
import luongduongquan.com.musicapp.R;
import luongduongquan.com.musicapp.Service.APIServiceUtils;
import luongduongquan.com.musicapp.Service.DataserviceListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_TimKiem extends Fragment {

	View view;
	android.support.v7.widget.Toolbar toolbar;
	RecyclerView recyclerView;
	TextView tvEmpty;
	SeachAdapter searchAdapter;

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_timkiem, container, false);

		toolbar = view.findViewById(R.id.toolBar_TimKiemFragment);
		recyclerView = view.findViewById(R.id.recyclerView_TimKiemFragment);
		tvEmpty = view.findViewById(R.id.tvEmpty_TimKiemFragment);
		((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
		toolbar.setTitle("");

		setHasOptionsMenu(true);



		return view;
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.search_menu, menu);
		MenuItem menuItem = menu.findItem(R.id.menu_search);
		SearchView searchView = (SearchView) menuItem.getActionView();
		searchView.setMaxWidth(Integer.MAX_VALUE);
		searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
			@Override
			public boolean onQueryTextSubmit(String query) {
				// Trả về khi nhập xong bấm enter
				Log.d("QUAN123 SEARCH", "onQueryTextSubmit: " + query);
				seachBaiHat(query);
				return true;
			}

			@Override
			public boolean onQueryTextChange(String newText) {
				return false;
			}
		});

		super.onCreateOptionsMenu(menu, inflater);
	}

	private void seachBaiHat(String keyWord){
		DataserviceListener dataserviceListener = APIServiceUtils.getDataFromService();
		Call<List<BaiHat>> callBack = dataserviceListener.getSeachBaiHat(keyWord);
		callBack.enqueue(new Callback<List<BaiHat>>() {
			@Override
			public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
				ArrayList<BaiHat> listBaiHatSearch = (ArrayList<BaiHat>) response.body();
				if(listBaiHatSearch.size() > 0){
					searchAdapter = new SeachAdapter(getActivity(), listBaiHatSearch);
					LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
					recyclerView.setLayoutManager(linearLayoutManager);
					recyclerView.setAdapter(searchAdapter);

					tvEmpty.setVisibility(View.GONE);
					recyclerView.setVisibility(View.VISIBLE);
				} else {
					recyclerView.setVisibility(View.GONE);
					tvEmpty.setVisibility(View.VISIBLE);

				}
			}

			@Override
			public void onFailure(Call<List<BaiHat>> call, Throwable t) {

			}
		});
	}
}
