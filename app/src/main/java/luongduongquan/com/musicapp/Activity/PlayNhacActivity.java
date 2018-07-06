package luongduongquan.com.musicapp.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;
import java.text.SimpleDateFormat;
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

	MediaPlayer mediaPlayer;

	ProgressDialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_play_nhac);
		Log.d("QUAN123", "PlayNhacActivity onCreate: ");
		progressDialog = new ProgressDialog(PlayNhacActivity.this);
		progressDialog.show();

		// Để kiểm tra mạng tránh lỗi
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);

		getDataIntent();
		initView();

		eventClick();
		

	}

	private void eventClick() {

		final Handler handler = new Handler();
		// Hàm này là để chờ cho tới khi load dược hình ảnh ra UI rồi mới tắt cái handler.
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				if(adapterViewPagerPlayNhac.getItem(1) != null){
					if(listBaiHatPlay.size() > 0){
						fragment_diaNhac.setImagePlayNhac(MyAppUtils.replaceHTTPStoHTTP(listBaiHatPlay.get(0).getHinhbaihat()));
						handler.removeCallbacks(this);
					} else {
						// Khi mà Không thể load hình ảnh ra được, thì nó sẽ tiếp tục mỗi 300s sẽ chạy tiếp cái handler này để load hình ảnh.
						handler.postDelayed(this, 300);
					}
				}
			}
		}, 500);
		btnPlay.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(mediaPlayer.isPlaying()){
					mediaPlayer.pause();
					btnPlay.setImageResource(R.drawable.iconplay);
				} else {
					mediaPlayer.start();
					btnPlay.setImageResource(R.drawable.iconpause);
				}
			}
		});

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
				if(mediaPlayer!=null){
					mediaPlayer.stop();
				}
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

		fragment_diaNhac = (Fragment_DiaNhac) adapterViewPagerPlayNhac.getItem(1); // để lấy ra cái fragment đĩa nhạc đang hiển thị

		if(listBaiHatPlay.size() > 0){
			getSupportActionBar().setTitle(listBaiHatPlay.get(0).getTenbaihat());
			new PlayMusicMp3().execute(listBaiHatPlay.get(0).getLinkbaihat());
			btnPlay.setImageResource(R.drawable.iconpause);
		}





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

	class PlayMusicMp3 extends AsyncTask<String,Void,String>{

		@Override
		protected String doInBackground(String... strings) {
			return strings[0];
		}

		@Override
		protected void onPostExecute(String linkBaiHat) {
			super.onPostExecute(linkBaiHat);

			try {
				mediaPlayer = new MediaPlayer();
				mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
				mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
					@Override
					public void onCompletion(MediaPlayer mp) {
						mediaPlayer.stop();
						mediaPlayer.reset();
					}
				});
					mediaPlayer.setDataSource(linkBaiHat);
					mediaPlayer.prepare();
			} catch (IOException e) {
				Log.d("ERROR Media Player", "onPostExecute: " + e.toString());
				e.printStackTrace();
			}
			mediaPlayer.start();
//			progressDialog.dismiss();
			TimeSong();

		}
	}

	private void TimeSong() {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
		tvTotalTimeSong.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
		seakBarTime.setMax(mediaPlayer.getDuration());


	}
}
