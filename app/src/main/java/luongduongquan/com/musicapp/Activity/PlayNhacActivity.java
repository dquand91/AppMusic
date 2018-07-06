package luongduongquan.com.musicapp.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

import luongduongquan.com.musicapp.Adapter.ViewPagerPlayNhacAdapter;
import luongduongquan.com.musicapp.Fragment.Fragment_DiaNhac;
import luongduongquan.com.musicapp.Fragment.Fragment_PlayDanhSachBaiHat;
import luongduongquan.com.musicapp.Model.BaiHat;
import luongduongquan.com.musicapp.R;
import luongduongquan.com.musicapp.Utils.MyAppUtils;

public class PlayNhacActivity extends AppCompatActivity {

	android.support.v7.widget.Toolbar toolbar;
	TextView tvTimeSong, tvTotalTimeSong;
	SeekBar seakBarTime;
	ImageButton btnShuffle, btnPrev, btnPlay, btnNext, btnRepeat;
	ViewPager viewPager;

	Fragment_DiaNhac fragment_diaNhac;
	Fragment_PlayDanhSachBaiHat fragment_playDanhSachBaiHat;

	public static ViewPagerPlayNhacAdapter adapterViewPagerPlayNhac;
	public static ArrayList<BaiHat> listBaiHatPlay = new ArrayList<>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_play_nhac);

		getDataIntent();
		initView();

	}

	private void initView() {

		toolbar = findViewById(R.id.toolBar_PlayNhac);
		tvTimeSong = findViewById(R.id.tvTimeSong_PlayNhac);
		tvTotalTimeSong= findViewById(R.id.tvTotalTimeSong_PlayNhac);

		seakBarTime = findViewById(R.id.seekBarSong_PlayNhac);
		btnShuffle = findViewById(R.id.btnShuffle_PlayNhac);
		btnNext = findViewById(R.id.btnNext_PlayNhac);
		btnPlay = findViewById(R.id.btnPlay_PlayNhac);
		btnPrev = findViewById(R.id.btnPrev_PlayNhac);
		btnRepeat = findViewById(R.id.btnRepeat_PlayNhac);

		viewPager = findViewById(R.id.viewpager_PlayNhac);

		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
				//////////////////////////////////////////////////////////
				//////////////////////////////////////////////////////////
				//////////////////////////////////////////////////////////
				listBaiHatPlay.clear();
				//////////////////////////////////////////////////////////
				//////////////////////////////////////////////////////////
				//////////////////////////////////////////////////////////

			}
		});
		toolbar.setTitleTextColor(Color.WHITE);
		toolbar.setTitle("Play Nhac");

		fragment_diaNhac = new Fragment_DiaNhac();
		fragment_playDanhSachBaiHat = new Fragment_PlayDanhSachBaiHat();

		adapterViewPagerPlayNhac = new ViewPagerPlayNhacAdapter(getSupportFragmentManager());
		adapterViewPagerPlayNhac.addFragment(fragment_playDanhSachBaiHat, "Play Nhac");
		adapterViewPagerPlayNhac.addFragment(fragment_diaNhac, "Dia nhac");

		viewPager.setAdapter(adapterViewPagerPlayNhac);





	}

	private void getDataIntent() {

		Intent intent = getIntent();
		if(intent != null){

			if(intent.hasExtra(MyAppUtils.KEY_INTENT_BAIHAT)){
				BaiHat baiHat = intent.getParcelableExtra(MyAppUtils.KEY_INTENT_BAIHAT);
				listBaiHatPlay.add(baiHat);

			}
			if(intent.hasExtra(MyAppUtils.KEY_INTENT_LIST_BAIHAT)){
				ArrayList<BaiHat> listBaiHat = intent.getParcelableArrayListExtra(MyAppUtils.KEY_INTENT_LIST_BAIHAT);
				listBaiHatPlay = listBaiHat;
			}
		}

	}
}
