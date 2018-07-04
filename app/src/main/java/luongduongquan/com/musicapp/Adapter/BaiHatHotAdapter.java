package luongduongquan.com.musicapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import luongduongquan.com.musicapp.Model.BaiHat;
import luongduongquan.com.musicapp.R;
import luongduongquan.com.musicapp.Utils.MyAppUtils;

public class BaiHatHotAdapter extends RecyclerView.Adapter<BaiHatHotAdapter.BaiHatHotViewHolder> {

	Context context;
	ArrayList<BaiHat> listBaiHat;

	public BaiHatHotAdapter(Context context, ArrayList<BaiHat> listBaiHat) {
		this.context = context;
		this.listBaiHat = listBaiHat;
	}

	@NonNull
	@Override
	public BaiHatHotViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

		LayoutInflater layoutInflater = LayoutInflater.from(context);
		View view = layoutInflater.inflate(R.layout.row_baihatnoibat_item, parent, false);

		return new BaiHatHotViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull BaiHatHotViewHolder holder, int position) {
		BaiHat baihat = listBaiHat.get(position);
		holder.tvCaSi.setText(baihat.getCasi());
		holder.tvTenBaiHat.setText(baihat.getTenbaihat());

		Picasso.with(context).load(MyAppUtils.replaceHTTPStoHTTP(baihat.getHinhbaihat())).fit().into(holder.imgBaiHat);

	}

	@Override
	public int getItemCount() {
		return listBaiHat.size();
	}

	public class BaiHatHotViewHolder extends RecyclerView.ViewHolder {

		TextView tvTenBaiHat, tvCaSi;
		ImageView imgBaiHat, imgLuotThich;

		public BaiHatHotViewHolder(View itemView) {
			super(itemView);
			tvTenBaiHat = itemView.findViewById(R.id.tvTenBaiHat_BaiHatHot);
			tvCaSi = itemView.findViewById(R.id.tvTenCaSi_BaiHatHot);

			imgBaiHat = itemView.findViewById(R.id.imgBaiHat_BaiHatHot);
			imgLuotThich = itemView.findViewById(R.id.imgLuotThich_BaiHatHot);

		}
	}

}
