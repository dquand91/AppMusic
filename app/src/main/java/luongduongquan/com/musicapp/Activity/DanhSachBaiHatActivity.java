package luongduongquan.com.musicapp.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import luongduongquan.com.musicapp.Adapter.DanhSachBaiHatAdapter;
import luongduongquan.com.musicapp.Model.Album;
import luongduongquan.com.musicapp.Model.BaiHat;
import luongduongquan.com.musicapp.Model.PlayList;
import luongduongquan.com.musicapp.Model.QuangCao;
import luongduongquan.com.musicapp.Model.TheLoai;
import luongduongquan.com.musicapp.R;
import luongduongquan.com.musicapp.Service.APIServiceUtils;
import luongduongquan.com.musicapp.Service.DataserviceListener;
import luongduongquan.com.musicapp.Utils.MyAppUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachBaiHatActivity extends AppCompatActivity {

	QuangCao quangCao;
	PlayList playlist;
	TheLoai theloai;
	Album album;

	CoordinatorLayout coordinatorLayout;
	CollapsingToolbarLayout collapsingToolbarLayout;
	Toolbar toolbar;
	RecyclerView recyclerViewDanhSachBaiHat;
	FloatingActionButton floatingActionButton;
	ImageView imgDanhSachBaiHat;

	DanhSachBaiHatAdapter danhSachBaiHatAdapter;

	ArrayList<BaiHat> listBaiHat;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_danh_sach_bai_hat);

		// Để kiểm tra mạng tránh lỗi
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);

		getDataIntent();
		initView();
		init();
		if(quangCao != null && !quangCao.getTenBaiHat().isEmpty()){
			setValueInView(quangCao.getTenBaiHat(), MyAppUtils.replaceHTTPStoHTTP(quangCao.getHinhAnh()));
			getDataQuangCao(quangCao.getIdQuangCao());
		}
		if(playlist != null && !playlist.getTenPlayList().isEmpty()){
			setValueInView(playlist.getTenPlayList(), MyAppUtils.replaceHTTPStoHTTP(playlist.getHinhAnh()));
			getDataPlayList(playlist.getIdPlayList());
		}
		if(theloai != null && !theloai.getTenTheLoai().isEmpty()){
			setValueInView(theloai.getTenTheLoai(), MyAppUtils.replaceHTTPStoHTTP(theloai.getHinhTheLoai()));
			getDataTheLoai(theloai.getIdTheLoai());
		}
		if(album != null && !album.getTenAlbum().isEmpty()){
			setValueInView(album.getTenAlbum(), MyAppUtils.replaceHTTPStoHTTP(album.getHinhanhAlbum()));
			getDataTheLoai(album.getIdAlbum());
		}
	}

	private void getDataTheLoai(String getIdTheLoai) {

		DataserviceListener dataserviceListener = APIServiceUtils.getDataFromService();

		Call<List<BaiHat>> callback = dataserviceListener.getListBaiHatTheoTheLoai(getIdTheLoai);
		callback.enqueue(new Callback<List<BaiHat>>() {
			@Override
			public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
				listBaiHat = (ArrayList<BaiHat>) response.body();

				danhSachBaiHatAdapter = new DanhSachBaiHatAdapter(DanhSachBaiHatActivity.this, listBaiHat);

				LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DanhSachBaiHatActivity.this);
				linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
				recyclerViewDanhSachBaiHat.setLayoutManager(linearLayoutManager);
				recyclerViewDanhSachBaiHat.setAdapter(danhSachBaiHatAdapter);
				eventClick();
			}

			@Override
			public void onFailure(Call<List<BaiHat>> call, Throwable t) {

			}
		});

	}

	private void getDataPlayList(String idPlayList) {

		DataserviceListener dataserviceListener = APIServiceUtils.getDataFromService();

		Call<List<BaiHat>> callback = dataserviceListener.getListBaiHatTheoPlayList(idPlayList);
		callback.enqueue(new Callback<List<BaiHat>>() {
			@Override
			public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
				listBaiHat = (ArrayList<BaiHat>) response.body();

				danhSachBaiHatAdapter = new DanhSachBaiHatAdapter(DanhSachBaiHatActivity.this, listBaiHat);

				LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DanhSachBaiHatActivity.this);
				linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
				recyclerViewDanhSachBaiHat.setLayoutManager(linearLayoutManager);
				recyclerViewDanhSachBaiHat.setAdapter(danhSachBaiHatAdapter);
				eventClick();

			}

			@Override
			public void onFailure(Call<List<BaiHat>> call, Throwable t) {

			}
		});

	}

	private void setValueInView(String ten, String hinh) {

		collapsingToolbarLayout.setTitle(ten);

		// Phần này là thiết lập cho Background của collapsingToolbar. Vì ko lấy ra được cái View của nó nên mới làm dài dòng như vậy
		try {
			URL url = new URL(hinh);
			Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
			BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bitmap);
			bitmapDrawable.setAlpha(150);
			collapsingToolbarLayout.setBackground(bitmapDrawable);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Còn đây là set cái hình thumb cho cái DanhSachBaiHat
		Picasso.with(this).load(hinh).into(imgDanhSachBaiHat);
	}

	private void getDataQuangCao(String idQuangCao) {

		DataserviceListener dataserviceListener = APIServiceUtils.getDataFromService();

		Call<List<BaiHat>> callback = dataserviceListener.getListBaiHatTheoQuangCao(idQuangCao);
		callback.enqueue(new Callback<List<BaiHat>>() {
			@Override
			public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
				listBaiHat = (ArrayList<BaiHat>) response.body();

				danhSachBaiHatAdapter = new DanhSachBaiHatAdapter(DanhSachBaiHatActivity.this, listBaiHat);

				LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DanhSachBaiHatActivity.this);
				linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
				recyclerViewDanhSachBaiHat.setLayoutManager(linearLayoutManager);
				recyclerViewDanhSachBaiHat.setAdapter(danhSachBaiHatAdapter);
				eventClick();

			}

			@Override
			public void onFailure(Call<List<BaiHat>> call, Throwable t) {

			}
		});

	}

	private void init() {

		// Phần setting cho cái toolbar trên cùng
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

		//Phần scroll ảnh hưởng toolbar
		collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
		collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);

		floatingActionButton.setEnabled(false);



	}

	private void initView() {
		coordinatorLayout = findViewById(R.id.coordinator_DanhSachBaiHat);
		collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar_DanhSachBaiHat);
		toolbar = findViewById(R.id.toolbar_DanhSachBaiHat);
		recyclerViewDanhSachBaiHat = findViewById(R.id.recyclerView_DanhSachBaiHat);
		floatingActionButton = findViewById(R.id.btnPlay_DanhSachBaiHat);
		imgDanhSachBaiHat = findViewById(R.id.imgHinhAnh_DanhSachBaiHat);
	}

	private void getDataIntent() {

		Intent intent = getIntent();
		if(intent != null){
			if(intent.hasExtra(MyAppUtils.KEY_INTENT_BANNER)){
				quangCao = (QuangCao) intent.getSerializableExtra(MyAppUtils.KEY_INTENT_BANNER);

			}
			if(intent.hasExtra(MyAppUtils.KEY_INTENT_PLAYLIST)){
				playlist = (PlayList) intent.getSerializableExtra(MyAppUtils.KEY_INTENT_PLAYLIST);
			}
			if(intent.hasExtra(MyAppUtils.KEY_INTENT_THELOAI)){
				theloai = (TheLoai) intent.getSerializableExtra(MyAppUtils.KEY_INTENT_THELOAI);
			}
			if(intent.hasExtra(MyAppUtils.KEY_INTENT_ALBUM)){
				album = (Album) intent.getSerializableExtra(MyAppUtils.KEY_INTENT_ALBUM);
			}

		}

	}

	private  void eventClick(){
		floatingActionButton.setEnabled(true);
		floatingActionButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.d(getClass().getSimpleName(), "onClick: ");
				Intent intentToPlayNhac = new Intent(DanhSachBaiHatActivity.this, PlayNhacActivity.class);
				intentToPlayNhac.putExtra(MyAppUtils.KEY_INTENT_LIST_BAIHAT, listBaiHat);
				startActivity(intentToPlayNhac);
			}
		});
	}


}
