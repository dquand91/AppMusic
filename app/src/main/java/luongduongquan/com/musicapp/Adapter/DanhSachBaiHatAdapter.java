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
import android.widget.Toast;

import java.util.ArrayList;

import luongduongquan.com.musicapp.Activity.PlayNhacActivity;
import luongduongquan.com.musicapp.Model.BaiHat;
import luongduongquan.com.musicapp.R;
import luongduongquan.com.musicapp.Service.APIServiceUtils;
import luongduongquan.com.musicapp.Service.DataserviceListener;
import luongduongquan.com.musicapp.Utils.MyAppUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachBaiHatAdapter extends RecyclerView.Adapter<DanhSachBaiHatAdapter.DanhSachBaiHatViewHolder> {

	Context context;
	ArrayList<BaiHat> listBaiHat;


	public DanhSachBaiHatAdapter(Context context, ArrayList<BaiHat> listBaiHat) {
		this.context = context;
		this.listBaiHat = listBaiHat;
	}

	@NonNull
	@Override
	public DanhSachBaiHatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

		LayoutInflater layoutInflater = LayoutInflater.from(context);
		View view = layoutInflater.inflate(R.layout.row_danhsachbaihat_item, parent, false);


		return new DanhSachBaiHatViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull DanhSachBaiHatViewHolder holder, int position) {

		holder.tvIndex.setText((position + 1)+"");
		holder.tvTenBaiHat.setText(listBaiHat.get(position).getTenbaihat());
		holder.tvTenCasi.setText(listBaiHat.get(position).getCasi());


	}

	@Override
	public int getItemCount() {
		return listBaiHat.size();
	}

	public class DanhSachBaiHatViewHolder extends RecyclerView.ViewHolder {

		TextView tvTenBaiHat, tvTenCasi, tvIndex;
		ImageView imgLuotThich;

		public DanhSachBaiHatViewHolder(View itemView) {
			super(itemView);

			tvIndex = itemView.findViewById(R.id.tvIndexBaiHat_ItemDanhsachbaihat);
			tvTenBaiHat = itemView.findViewById(R.id.tvTenBaiHat_ItemDanhsachbaihat);
			tvTenCasi = itemView.findViewById(R.id.tvTenCaSi_ItemDanhsachbaihat);
			imgLuotThich = itemView.findViewById(R.id.imgLuotThich_ItemDanhsachbaihat);

			imgLuotThich.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					imgLuotThich.setImageDrawable(context.getResources().getDrawable(R.drawable.iconloved));
					DataserviceListener dataserviceListener = APIServiceUtils.getDataFromService();
					Call<String> callBack = dataserviceListener.updateLuotThich("1", listBaiHat.get(getAdapterPosition()).getIdbaihat());
					callBack.enqueue(new Callback<String>() {
						@Override
						public void onResponse(Call<String> call, Response<String> response) {
							String ketqua = response.body();
							if(ketqua.equals("Success")){
								Toast.makeText(context, "Đã thích", Toast.LENGTH_SHORT).show();
								imgLuotThich.setEnabled(false);
							} else {
								Toast.makeText(context, "Lỗi update Luot Thich", Toast.LENGTH_SHORT).show();
								imgLuotThich.setImageDrawable(context.getResources().getDrawable(R.drawable.iconlove));
							}
						}

						@Override
						public void onFailure(Call<String> call, Throwable t) {

						}
					});

				}
			});

			itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intentToPlayNhac = new Intent(context, PlayNhacActivity.class);
					intentToPlayNhac.putExtra(MyAppUtils.KEY_INTENT_BAIHAT, listBaiHat.get(getAdapterPosition()));
					context.startActivity(intentToPlayNhac);

				}
			});

		}
	}


}
