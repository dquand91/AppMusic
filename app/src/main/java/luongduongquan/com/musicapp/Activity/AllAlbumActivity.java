package luongduongquan.com.musicapp.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import luongduongquan.com.musicapp.Adapter.AlbumAdapter;
import luongduongquan.com.musicapp.Model.Album;
import luongduongquan.com.musicapp.R;
import luongduongquan.com.musicapp.Service.APIServiceUtils;
import luongduongquan.com.musicapp.Service.DataserviceListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllAlbumActivity extends AppCompatActivity {

	RecyclerView recyclerViewAllAlbum;
	Toolbar toolbarAllAlbum;
	AlbumAdapter albumAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_all_album);
		
		initView();
		getDataAllAlbum();
	}

	private void getDataAllAlbum() {

		DataserviceListener dataserviceListener = APIServiceUtils.getDataFromService();
		retrofit2.Call<List<Album>> callBack = dataserviceListener.getAllAlbum();
		callBack.enqueue(new Callback<List<Album>>() {
			@Override
			public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
				ArrayList<Album> listAlbum = (ArrayList<Album>) response.body();

				albumAdapter = new AlbumAdapter(AllAlbumActivity.this, listAlbum);

				recyclerViewAllAlbum.setLayoutManager(new GridLayoutManager(AllAlbumActivity.this, 2));
				recyclerViewAllAlbum.setAdapter(albumAdapter);

			}

			@Override
			public void onFailure(Call<List<Album>> call, Throwable t) {

			}
		});

	}

	private void initView() {

		recyclerViewAllAlbum = findViewById(R.id.recyclerView_AllAlbum);
		toolbarAllAlbum = findViewById(R.id.toolBar_AllAlbum);
		setSupportActionBar(toolbarAllAlbum);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setTitle("Tất cả Album");
		toolbarAllAlbum.setTitleTextColor(getResources().getColor(R.color.toolBarColor));
		toolbarAllAlbum.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

	}
}
