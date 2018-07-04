package luongduongquan.com.musicapp.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import luongduongquan.com.musicapp.Adapter.AlbumAdapter;
import luongduongquan.com.musicapp.Model.Album;
import luongduongquan.com.musicapp.R;
import luongduongquan.com.musicapp.Service.APIServiceUtils;
import luongduongquan.com.musicapp.Service.DataserviceListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_AlbumHot extends Fragment {

	View view;
	RecyclerView recyclerViewAlbum;
	TextView tvThemAlbum;
	AlbumAdapter albumAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		view = inflater.inflate(R.layout.fragment_album_hot, container, false);
		recyclerViewAlbum = view.findViewById(R.id.recyclerView_album);
		tvThemAlbum = view.findViewById(R.id.tvXemThem_album);
		getData();
		return view;
	}

	private void getData() {

		DataserviceListener dataserviceListener = APIServiceUtils.getDataFromService();

		Call<List<Album>> callback = dataserviceListener.getListAlbumHost();
		callback.enqueue(new Callback<List<Album>>() {
			@Override
			public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
				ArrayList<Album> listAlbum = (ArrayList<Album>) response.body();

				albumAdapter = new AlbumAdapter(getActivity(), listAlbum);

				LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
				linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
				recyclerViewAlbum.setLayoutManager(linearLayoutManager);

				recyclerViewAlbum.setAdapter(albumAdapter);
			}

			@Override
			public void onFailure(Call<List<Album>> call, Throwable t) {

			}
		});

	}

}
