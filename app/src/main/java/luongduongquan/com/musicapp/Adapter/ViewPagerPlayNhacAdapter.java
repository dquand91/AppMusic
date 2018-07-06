package luongduongquan.com.musicapp.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class ViewPagerPlayNhacAdapter extends FragmentPagerAdapter {

	public final ArrayList<Fragment> array_fragment_playNhac = new ArrayList<>(); // Chứa danh sách Fragment
	public final ArrayList<String> array_title_tab_playNhac = new ArrayList<>(); // Chứa danh sách Title

	public ViewPagerPlayNhacAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {
		return array_fragment_playNhac.get(position);
	}

	@Override
	public int getCount() {
		return array_fragment_playNhac.size();
	}

	public void addFragment(Fragment fragmentAdd, String titleFragment) {
		array_fragment_playNhac.add(fragmentAdd);
		array_title_tab_playNhac.add(titleFragment);
	}

	@Nullable
	@Override
	public CharSequence getPageTitle(int position) {
		// Để hiển thị title của từng Page
		return array_title_tab_playNhac.get(position);
	}
}