package luongduongquan.com.musicapp.Activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import luongduongquan.com.musicapp.Adapter.CustomViewPagerAdapter;
import luongduongquan.com.musicapp.Fragment.Fragment_TimKiem;
import luongduongquan.com.musicapp.Fragment.Fragment_TrangChu;
import luongduongquan.com.musicapp.R;

public class MainActivity extends AppCompatActivity {

	TabLayout tabLayout;
	ViewPager viewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		init();
	}

	private void initView() {
		tabLayout = findViewById(R.id.myTabLayout); // Phần bottom bar, hiển thị Trang Chủ và Tìm Kiếm.
		viewPager = findViewById(R.id.myViewPager); // Phần nội dung Fragment cần hiển thị.
	}

	private void init() {
		CustomViewPagerAdapter customViewPagerAdapter = new CustomViewPagerAdapter(getSupportFragmentManager());
		customViewPagerAdapter.addFragment(new Fragment_TrangChu(), "Trang Chủ");
		customViewPagerAdapter.addFragment(new Fragment_TimKiem(), "Tìm Kiếm");
		viewPager.setAdapter(customViewPagerAdapter);

		tabLayout.setupWithViewPager(viewPager);
		tabLayout.getTabAt(0).setIcon(R.drawable.icontrangchu);
		tabLayout.getTabAt(1).setIcon(R.drawable.iconsearch);
	}


}
