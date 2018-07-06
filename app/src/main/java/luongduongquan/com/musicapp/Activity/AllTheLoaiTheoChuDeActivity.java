package luongduongquan.com.musicapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import luongduongquan.com.musicapp.Adapter.AllTheLoaiTheoChuDeAdapter;
import luongduongquan.com.musicapp.Model.ChuDe;
import luongduongquan.com.musicapp.Model.TheLoai;
import luongduongquan.com.musicapp.R;
import luongduongquan.com.musicapp.Service.APIServiceUtils;
import luongduongquan.com.musicapp.Service.DataserviceListener;
import luongduongquan.com.musicapp.Utils.MyAppUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllTheLoaiTheoChuDeActivity extends AppCompatActivity {

	ChuDe chude;
	Toolbar toolbar;
	RecyclerView recyclerViewTheLoai;

	AllTheLoaiTheoChuDeAdapter allTheLoaiTheoChuDeAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_all_the_loai_theo_chu_de);

		getDataFromIntent();
		initView();
		getDataTheLoaiTheoChuDe();
	}

	private void getDataTheLoaiTheoChuDe() {

		DataserviceListener dataserviceListener = APIServiceUtils.getDataFromService();

		Call<List<TheLoai>> callback = dataserviceListener.getTheLoaiTheoChuDe(chude.getIdChuDe());
		callback.enqueue(new Callback<List<TheLoai>>() {
			@Override
			public void onResponse(Call<List<TheLoai>> call, Response<List<TheLoai>> response) {
				ArrayList<TheLoai> listTheLoai = (ArrayList<TheLoai>) response.body();
				allTheLoaiTheoChuDeAdapter = new AllTheLoaiTheoChuDeAdapter(AllTheLoaiTheoChuDeActivity.this, listTheLoai);

				recyclerViewTheLoai.setLayoutManager(new GridLayoutManager(AllTheLoaiTheoChuDeActivity.this, 2));
				recyclerViewTheLoai.setAdapter(allTheLoaiTheoChuDeAdapter);


			}

			@Override
			public void onFailure(Call<List<TheLoai>> call, Throwable t) {

			}
		});

	}

	private void initView() {

		recyclerViewTheLoai = findViewById(R.id.recyclerView_TheLoaiTheoChuDe);
		toolbar = findViewById(R.id.toolBar_TheLoaiTheoChuDe);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setTitle(chude.getTenChuDe());
		toolbar.setTitleTextColor(getResources().getColor(R.color.toolBarColor));
		toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

	}

	private void getDataFromIntent() {

		Intent intent = getIntent();
		if(intent.hasExtra(MyAppUtils.KEY_INTENT_CHUDE)){
			chude = (ChuDe) intent.getSerializableExtra(MyAppUtils.KEY_INTENT_CHUDE);

		}

	}
}
