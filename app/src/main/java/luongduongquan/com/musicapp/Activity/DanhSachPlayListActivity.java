package luongduongquan.com.musicapp.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import luongduongquan.com.musicapp.Adapter.AllPlayListAdapter;
import luongduongquan.com.musicapp.Model.PlayList;
import luongduongquan.com.musicapp.R;
import luongduongquan.com.musicapp.Service.APIServiceUtils;
import luongduongquan.com.musicapp.Service.DataserviceListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachPlayListActivity extends AppCompatActivity {

	Toolbar toolbar;
	RecyclerView recyclerView;
	AllPlayListAdapter allPlayListAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_danh_sach_play_list);
		
		initView();
		init();
		
		getData();

	}

	private void getData() {

		DataserviceListener dataserviceListener = APIServiceUtils.getDataFromService();

		Call<List<PlayList>> callback = dataserviceListener.getAllPlayList();
		callback.enqueue(new Callback<List<PlayList>>() {
			@Override
			public void onResponse(Call<List<PlayList>> call, Response<List<PlayList>> response) {
				ArrayList<PlayList> listPlayList = (ArrayList<PlayList>) response.body();

				allPlayListAdapter = new AllPlayListAdapter(DanhSachPlayListActivity.this, listPlayList);

				recyclerView.setLayoutManager(new GridLayoutManager(DanhSachPlayListActivity.this, 2));
				recyclerView.setAdapter(allPlayListAdapter);
			}

			@Override
			public void onFailure(Call<List<PlayList>> call, Throwable t) {

			}
		});
	}

	private void init() {

		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setTitle("PlayLists");
		toolbar.setTitleTextColor(getResources().getColor(R.color.toolBarColor));
		toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});


	}

	private void initView() {

		toolbar = findViewById(R.id.toolBar_DanhSachPlaylist);
		recyclerView = findViewById(R.id.recyclerView_DanhSachPlayList);

	}
}
