package luongduongquan.com.musicapp.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import luongduongquan.com.musicapp.Activity.PlayNhacActivity;
import luongduongquan.com.musicapp.Adapter.PlayDanhSachBaiHatAdapter;
import luongduongquan.com.musicapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_PlayDanhSachBaiHat extends Fragment {

	View view;
	RecyclerView recyclerViewPlayNhac;

	PlayDanhSachBaiHatAdapter playDanhSachBaiHatAdapter;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		view = inflater.inflate(R.layout.fragment_play_danh_sach_bai_hat, container, false);
		recyclerViewPlayNhac = view.findViewById(R.id.recyclerView_PlayBaiHatFragment);

		if(PlayNhacActivity.listBaiHatPlay != null && PlayNhacActivity.listBaiHatPlay.size()>0){
			playDanhSachBaiHatAdapter = new PlayDanhSachBaiHatAdapter(getActivity(), PlayNhacActivity.listBaiHatPlay);
			recyclerViewPlayNhac.setLayoutManager(new LinearLayoutManager(getActivity()));
			recyclerViewPlayNhac.setAdapter(playDanhSachBaiHatAdapter);
		}

		return view;
	}

	public void updateSelectedPosition(int selectedPosition){
		playDanhSachBaiHatAdapter.setSelected_position(selectedPosition);
		playDanhSachBaiHatAdapter.notifyDataSetChanged();
	}

}
