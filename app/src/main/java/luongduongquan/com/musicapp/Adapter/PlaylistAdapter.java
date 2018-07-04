package luongduongquan.com.musicapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import luongduongquan.com.musicapp.Model.PlayList;
import luongduongquan.com.musicapp.R;

public class PlaylistAdapter extends ArrayAdapter<PlayList> {

	Context mContext;

	public PlaylistAdapter(@NonNull Context context, int resource, @NonNull List<PlayList> objects) {
		super(context, resource, objects);
	}

	class ViewHolder{
		TextView tvName_playlist;
		ImageView imgBackground, imgPlayList;
	}

	@NonNull
	@Override
	public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

		ViewHolder viewHolder = null;
		if(convertView == null){
			LayoutInflater inflater = LayoutInflater.from(getContext());
			convertView = inflater.inflate(R.layout.row_playlist_item, null);

			viewHolder = new ViewHolder();
			viewHolder.tvName_playlist = convertView.findViewById(R.id.tvName_playlistItem);
			viewHolder.imgBackground = convertView.findViewById(R.id.imgBackGround_playlistItem);
			viewHolder.imgPlayList = convertView.findViewById(R.id.imgPlayListThumb_playlistItem);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		PlayList playlist = getItem(position);

		String urlHinhAnh = playlist.getHinhAnh().replace("https", "http");
		String urlHinhBaiHat = playlist.getIcon().replace("https", "http");
		Picasso.with(getContext()).load(urlHinhAnh).fit().into(viewHolder.imgBackground);
		Picasso.with(getContext()).load(urlHinhBaiHat).fit().into(viewHolder.imgPlayList);
		viewHolder.tvName_playlist.setText(playlist.getTenPlayList());

		return convertView;
	}
}
