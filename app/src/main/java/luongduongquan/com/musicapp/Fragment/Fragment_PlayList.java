package luongduongquan.com.musicapp.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import luongduongquan.com.musicapp.Activity.DanhSachBaiHatActivity;
import luongduongquan.com.musicapp.Adapter.PlaylistAdapter;
import luongduongquan.com.musicapp.Model.PlayList;
import luongduongquan.com.musicapp.R;
import luongduongquan.com.musicapp.Service.APIServiceUtils;
import luongduongquan.com.musicapp.Service.DataserviceListener;
import luongduongquan.com.musicapp.Utils.MyAppUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_PlayList extends Fragment {

	final String TAG = this.getClass().getSimpleName();

	View view;
	ListView listViewPlayList;
	TextView tvTitlePlayList, tvMorePlayList;
	PlaylistAdapter playlistAdapter;
	ArrayList<PlayList> listPlayList;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		view = inflater.inflate(R.layout.fragment_play_list, container, false);
		listViewPlayList = view.findViewById(R.id.listViewPlayList_Playlist);
		tvTitlePlayList = view.findViewById(R.id.tvTitle_Playlist);
		tvMorePlayList = view.findViewById(R.id.tvViewMorePlayList);

		getData();

		return view;
	}

	public void getData() {
		DataserviceListener dataserviceListener = APIServiceUtils.getDataFromService();

		Call<List<PlayList>> callback = dataserviceListener.getPlayListCurrentDay();
		Log.d(TAG, "getData: " );

		callback.enqueue(new Callback<List<PlayList>>() {
			@Override
			public void onResponse(Call<List<PlayList>> call, Response<List<PlayList>> response) {
				listPlayList = (ArrayList<PlayList>) response.body();
				Log.d(TAG, "onResponse: " + listPlayList.size());
				playlistAdapter = new PlaylistAdapter(getActivity(),android.R.layout.simple_list_item_1,listPlayList);
				listViewPlayList.setAdapter(playlistAdapter);
				setListViewHeightBasedOnChildren(listViewPlayList);

				listViewPlayList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
						Intent intentToDanhSachBaiHat = new Intent(getActivity(), DanhSachBaiHatActivity.class);
						intentToDanhSachBaiHat.putExtra(MyAppUtils.KEY_INTENT_PLAYLIST, listPlayList.get(position));
						startActivity(intentToDanhSachBaiHat);
					}
				});
			}

			@Override
			public void onFailure(Call<List<PlayList>> call, Throwable t) {

			}
		});
	}

	public void setListViewHeightBasedOnChildren(ListView listView) {
		ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter == null) {
			// pre-condition
			return;
		}

		int totalHeight = listView.getPaddingTop() + listView.getPaddingBottom();
		int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);
		for (int i = 0; i < listAdapter.getCount(); i++) {
			View listItem = listAdapter.getView(i, null, listView);

			if(listItem != null){
				// This next line is needed before you call measure or else you won't get measured height at all. The listitem needs to be drawn first to know the height.
				listItem.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
				listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
				totalHeight += listItem.getMeasuredHeight();

			}
		}

		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		listView.setLayoutParams(params);
		listView.requestLayout();
	}
}
