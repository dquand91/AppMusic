package luongduongquan.com.musicapp.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import luongduongquan.com.musicapp.Adapter.PlaylistAdapter;
import luongduongquan.com.musicapp.Model.PlayList;
import luongduongquan.com.musicapp.R;
import luongduongquan.com.musicapp.Service.APIServiceUtils;
import luongduongquan.com.musicapp.Service.DataserviceListener;
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
			}

			@Override
			public void onFailure(Call<List<PlayList>> call, Throwable t) {

			}
		});
	}
}
