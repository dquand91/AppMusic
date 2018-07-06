package luongduongquan.com.musicapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import luongduongquan.com.musicapp.Model.BaiHat;
import luongduongquan.com.musicapp.R;

public class PlayDanhSachBaiHatAdapter extends RecyclerView.Adapter<PlayDanhSachBaiHatAdapter.PlayBaiHatViewHolder> {

	Context context;
	ArrayList<BaiHat> listPlayBaiHat;

	public PlayDanhSachBaiHatAdapter(Context context, ArrayList<BaiHat> listPlayBaiHat) {
		this.context = context;
		this.listPlayBaiHat = listPlayBaiHat;
	}

	@NonNull
	@Override
	public PlayBaiHatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

		LayoutInflater layoutInflater = LayoutInflater.from(context);
		View view =  layoutInflater.inflate(R.layout.row_play_danh_sach_bai_hat_item, parent, false);

		return new PlayBaiHatViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull PlayBaiHatViewHolder holder, int position) {

		BaiHat baiHat = listPlayBaiHat.get(position);

		holder.tvIndex.setText(position + 1 + "");
		holder.tvTenBaiHat.setText(baiHat.getTenbaihat());
		holder.tvTenCasi.setText(baiHat.getCasi());


	}

	@Override
	public int getItemCount() {
		return listPlayBaiHat.size();
	}

	public class PlayBaiHatViewHolder extends RecyclerView.ViewHolder {

		TextView tvTenCasi, tvIndex, tvTenBaiHat;

		public PlayBaiHatViewHolder(View itemView) {
			super(itemView);

			tvIndex = itemView.findViewById(R.id.tvIndex_PlayNhacItem);
			tvTenBaiHat = itemView.findViewById(R.id.tvTenBaiHat_PlayNhacItem);
			tvTenCasi = itemView.findViewById(R.id.tvCaSi_PlayNhacItem);

		}
	}

}
