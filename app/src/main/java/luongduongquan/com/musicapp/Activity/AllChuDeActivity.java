package luongduongquan.com.musicapp.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import luongduongquan.com.musicapp.Adapter.AllChuDeAdapter;
import luongduongquan.com.musicapp.Model.ChuDe;
import luongduongquan.com.musicapp.R;
import luongduongquan.com.musicapp.Service.APIServiceUtils;
import luongduongquan.com.musicapp.Service.DataserviceListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllChuDeActivity extends AppCompatActivity {

	RecyclerView recyclerViewChuDe;
	Toolbar toolbar;
	AllChuDeAdapter allChuDeAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_all_chu_de);

		initView();
		getDataChuDe();
	}

	private void getDataChuDe() {

		DataserviceListener dataserviceListener = APIServiceUtils.getDataFromService();

		Call<List<ChuDe>> callback = dataserviceListener.getAllChuDe();
		callback.enqueue(new Callback<List<ChuDe>>() {
			@Override
			public void onResponse(Call<List<ChuDe>> call, Response<List<ChuDe>> response) {
				ArrayList<ChuDe> listChuDe = (ArrayList<ChuDe>) response.body();
				Log.d("QUAN123", "onResponse: " + listChuDe.get(1).getTenChuDe().toString());

				allChuDeAdapter = new AllChuDeAdapter(AllChuDeActivity.this, listChuDe);
				recyclerViewChuDe.setLayoutManager(new LinearLayoutManager(AllChuDeActivity.this));
				recyclerViewChuDe.setAdapter(allChuDeAdapter);

			}

			@Override
			public void onFailure(Call<List<ChuDe>> call, Throwable t) {

			}
		});

	}

	private void initView() {

		recyclerViewChuDe = findViewById(R.id.recyclerView_DanhSachChuDe);
		toolbar = findViewById(R.id.toolBar_DanhSachChuDe);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setTitle("Tất cả chủ đề");
		toolbar.setTitleTextColor(getResources().getColor(R.color.toolBarColor));
		toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

	}
}
