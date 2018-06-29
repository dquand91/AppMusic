package luongduongquan.com.musicapp.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class CustomViewPagerAdapter extends FragmentPagerAdapter {

	private ArrayList<Fragment> array_fragment = new ArrayList<>(); // Chứa danh sách Fragment
	private ArrayList<String> array_title_tab = new ArrayList<>(); // Chứa danh sách Title

	public CustomViewPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {
		return array_fragment.get(position);
	}

	@Override
	public int getCount() {
		return array_fragment.size();
	}

	public void addFragment(Fragment fragmentAdd, String titleFragment){
		array_fragment.add(fragmentAdd);
		array_title_tab.add(titleFragment);
	}

	@Nullable
	@Override
	public CharSequence getPageTitle(int position) {
		// Để hiển thị title của từng Page
		return array_title_tab.get(position);
	}
}
