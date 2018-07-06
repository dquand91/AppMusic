package luongduongquan.com.musicapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import luongduongquan.com.musicapp.Activity.DanhSachBaiHatActivity;
import luongduongquan.com.musicapp.Model.Album;
import luongduongquan.com.musicapp.R;
import luongduongquan.com.musicapp.Utils.MyAppUtils;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>  {

	Context context;
	ArrayList<Album> listAlbum;

	public AlbumAdapter(Context context, ArrayList<Album> listAlbum) {
		this.context = context;
		this.listAlbum = listAlbum;
	}

	@NonNull
	@Override
	public AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		LayoutInflater layoutInflater = LayoutInflater.from(context);
		View view = layoutInflater.inflate(R.layout.row_album_item, parent, false);
		return new AlbumViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull AlbumViewHolder holder, int position) {
		Album album = listAlbum.get(position);
		holder.tvCasiAlbum.setText(album.getTencasiAlbum());
		holder.tvTenAlbum.setText(album.getTenAlbum());
		Picasso.with(context).load(MyAppUtils.replaceHTTPStoHTTP(album.getHinhanhAlbum())).fit().into(holder.imgHinhAlbum);
	}

	@Override
	public int getItemCount() {
		return listAlbum.size();
	}

	public class AlbumViewHolder extends RecyclerView.ViewHolder {

		ImageView imgHinhAlbum;
		TextView tvTenAlbum, tvCasiAlbum;

		public AlbumViewHolder(View itemView) {
			super(itemView);
			imgHinhAlbum = itemView.findViewById(R.id.imgAlbum_album);
			tvTenAlbum = itemView.findViewById(R.id.tvTenAlbum_album);
			tvCasiAlbum = itemView.findViewById(R.id.tvTenCaSi_Album);
			imgHinhAlbum.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intentToDanhSachBaiHat = new Intent(context, DanhSachBaiHatActivity.class);
					intentToDanhSachBaiHat.putExtra(MyAppUtils.KEY_INTENT_ALBUM, listAlbum.get(getAdapterPosition()));
					context.startActivity(intentToDanhSachBaiHat);
				}
			});
		}
	}


}
