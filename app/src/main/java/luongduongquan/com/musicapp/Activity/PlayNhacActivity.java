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
import java.util.Random;

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

	int position = 0;
	boolean repeat = false;
	boolean checkRandom = false;
	boolean next = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		progressDialog = new ProgressDialog(PlayNhacActivity.this);
		progressDialog.show();
		setContentView(R.layout.activity_play_nhac);

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
						if(listBaiHatPlay.size() > 0){
							getSupportActionBar().setTitle(listBaiHatPlay.get(0).getTenbaihat());
							new PlayMusicMp3().execute(listBaiHatPlay.get(0).getLinkbaihat());
							btnPlay.setImageResource(R.drawable.iconpause);
						}
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

		btnRepeat.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(!repeat){
					if(checkRandom){
						checkRandom = false;
//						btnRepeat.setImageResource(R.drawable.iconsyned);
						btnShuffle.setImageResource(R.drawable.iconsuffle);
					}
					btnRepeat.setImageResource(R.drawable.iconsyned);
					repeat = true;
				} else {
					btnRepeat.setImageResource(R.drawable.iconrepeat);
					repeat = false;
				}
			}
		});

		btnShuffle.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(!checkRandom){
					if(repeat){
						repeat = false;
						btnRepeat.setImageResource(R.drawable.iconrepeat);
//						btnShuffle.setImageResource(R.drawable.iconshuffled);
					}
					btnShuffle.setImageResource(R.drawable.iconshuffled);
					checkRandom = true;
				} else {
					btnShuffle.setImageResource(R.drawable.iconsuffle);
					checkRandom = false;
				}
			}
		});

		seakBarTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {

			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				mediaPlayer.seekTo(seekBar.getProgress());
			}
		});

		btnNext.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(listBaiHatPlay.size() > 0){
					// Nếu đang playing thì dừng rồi mới next
					if(mediaPlayer.isPlaying() || mediaPlayer != null){
						mediaPlayer.stop();
						mediaPlayer.release();
						mediaPlayer = null;
					}

					// Nếu position hiện tại vẫn còn nhỏ hơn listSize thì xủ lý
					if(position < listBaiHatPlay.size()){
						btnPlay.setImageResource(R.drawable.iconpause);
						position++;

						if(repeat){
							// trường hợp bài đang play là bài cuối cùng của List, mà mình bấm Next => position = 0 => mình sẽ cho nó play lại bài cuối cùng đó.
							if(position == 0){
								position = listBaiHatPlay.size();
							}
							// Nếu đang setting repeat thì bấm next sẽ quay về phát bài hiện tại
							position = position - 1;
						}

						if(checkRandom){
							Random random = new Random();
							int indexRandom = random.nextInt(listBaiHatPlay.size());
							if(indexRandom == position){
								position = indexRandom - 1;
							} else {
								position = indexRandom;
							}
						}
						if(position > (listBaiHatPlay.size() -1)){
							position = 0;
						}
						new PlayMusicMp3().execute(listBaiHatPlay.get(position).getLinkbaihat());
						fragment_diaNhac.setImagePlayNhac(MyAppUtils.replaceHTTPStoHTTP(listBaiHatPlay.get(position).getHinhbaihat()));
						getSupportActionBar().setTitle(listBaiHatPlay.get(position).getTenbaihat());
					}
				}
				// Delay 5s mỗi lần nhấn button. Để tránh bấm liên tiếp nhiều lần.
				btnPrev.setClickable(false);
				btnNext.setClickable(false);
				Handler handler = new Handler();
				handler.postDelayed(new Runnable() {
					@Override
					public void run() {
						btnPrev.setClickable(true);
						btnNext.setClickable(true);
					}
				}, 5000);
			}
		});

		btnPrev.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(listBaiHatPlay.size() > 0){
					if(mediaPlayer.isPlaying() || mediaPlayer != null){
						mediaPlayer.stop();
						mediaPlayer.release();
						mediaPlayer = null;
					}
					if(position < listBaiHatPlay.size()){
						btnPlay.setImageResource(R.drawable.iconpause);
						position--;

						if(position < 0){
							position = listBaiHatPlay.size() - 1;
						}

						if(repeat){
							position = position + 1;
						}
						if(checkRandom){
							Random random = new Random();
							int indexRandom = random.nextInt(listBaiHatPlay.size());
							if(indexRandom == position){
								position = indexRandom - 1;
							}
							position = indexRandom;
						}
						new PlayMusicMp3().execute(listBaiHatPlay.get(position).getLinkbaihat());
						fragment_diaNhac.setImagePlayNhac(MyAppUtils.replaceHTTPStoHTTP(listBaiHatPlay.get(position).getHinhbaihat()));
						getSupportActionBar().setTitle(listBaiHatPlay.get(position).getTenbaihat());
					}
				}
				// Delay 5s mỗi lần nhấn button. Để tránh bấm liên tiếp nhiều lần.
				btnPrev.setClickable(false);
				btnNext.setClickable(false);
				Handler handler = new Handler();
				handler.postDelayed(new Runnable() {
					@Override
					public void run() {
						btnPrev.setClickable(true);
						btnNext.setClickable(true);
					}
				}, 5000);
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
		protected void onPreExecute() {

			super.onPreExecute();

		}

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
			progressDialog.dismiss();

		}
	}

	private void TimeSong() {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
		tvTotalTimeSong.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
		seakBarTime.setMax(mediaPlayer.getDuration());


	}
}
