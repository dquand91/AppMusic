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
import luongduongquan.com.musicapp.Model.PlayList;
import luongduongquan.com.musicapp.R;
import luongduongquan.com.musicapp.Utils.MyAppUtils;

public class AllPlayListAdapter extends RecyclerView.Adapter<AllPlayListAdapter.AllPlayListViewHolder> {

	Context context;
	ArrayList<PlayList> listPlayList;

	public AllPlayListAdapter(Context context, ArrayList<PlayList> listPlayList) {
		this.context = context;
		this.listPlayList = listPlayList;
	}

	@NonNull
	@Override
	public AllPlayListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

		LayoutInflater layoutInflater = LayoutInflater.from(context);
		View view = layoutInflater.inflate(R.layout.row_all_playlist_item, parent, false);

		return new AllPlayListViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull AllPlayListViewHolder holder, int position) {
		PlayList playList = listPlayList.get(position);
		Picasso.with(context).load(MyAppUtils.replaceHTTPStoHTTP(playList.getHinhAnh())).fit().into(holder.imgHinhNen);
		holder.tvTenPlayList.setText(playList.getTenPlayList());


	}

	@Override
	public int getItemCount() {
		return listPlayList.size();
	}

	public class AllPlayListViewHolder extends RecyclerView.ViewHolder {

		ImageView imgHinhNen;
		TextView tvTenPlayList;


		public AllPlayListViewHolder(final View itemView) {
			super(itemView);

			imgHinhNen = itemView.findViewById(R.id.imgAllPlayList_AllPlayListItem);
			tvTenPlayList = itemView.findViewById(R.id.tvTenPlayList_AllPlayListItem);
			itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intentToDanhSachBaiHatActivity = new Intent(context, DanhSachBaiHatActivity.class);
					intentToDanhSachBaiHatActivity.putExtra(MyAppUtils.KEY_INTENT_PLAYLIST, listPlayList.get(getPosition()));
					context.startActivity(intentToDanhSachBaiHatActivity);
				}
			});

		}
	}

}
