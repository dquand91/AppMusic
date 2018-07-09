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

import com.squareup.picasso.Picasso;

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

public class SeachAdapter extends RecyclerView.Adapter<SeachAdapter.SearchViewHolder> {

	Context context;
	ArrayList<BaiHat> listBaiHatSearch;

	public SeachAdapter(Context context, ArrayList<BaiHat> listBaiHatSearch) {
		this.context = context;
		this.listBaiHatSearch = listBaiHatSearch;
	}

	@NonNull
	@Override
	public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

		LayoutInflater layoutInflater = LayoutInflater.from(context);
		View view = layoutInflater.inflate(R.layout.row_search_item, parent, false);

		return new SearchViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
		BaiHat baiHat = listBaiHatSearch.get(position);
		holder.tvTenBaiHat.setText(baiHat.getTenbaihat());
		holder.tvCaSi.setText(baiHat.getCasi());

		Picasso.with(context).load(MyAppUtils.replaceHTTPStoHTTP(baiHat.getHinhbaihat())).into(holder.imgBaihat);

	}

	@Override
	public int getItemCount() {
		return listBaiHatSearch.size();
	}

	public class SearchViewHolder extends RecyclerView.ViewHolder {

		TextView tvTenBaiHat, tvCaSi;
		ImageView imgBaihat, imgLuotThich;

		public SearchViewHolder(View itemView) {
			super(itemView);
			tvTenBaiHat = itemView.findViewById(R.id.tvTenBaihat_SearchItem);
			tvCaSi = itemView.findViewById(R.id.tvTenCaSi_SearchItem);

			imgBaihat = itemView.findViewById(R.id.imgBatHat_SearchItem);
			imgLuotThich = itemView.findViewById(R.id.imgLuotThich_SearchItem);

			itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intentToPlayNhac = new Intent(context, PlayNhacActivity.class);
					intentToPlayNhac.putExtra(MyAppUtils.KEY_INTENT_BAIHAT, listBaiHatSearch.get(getAdapterPosition()));
					context.startActivity(intentToPlayNhac);
				}
			});

			imgLuotThich.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					imgLuotThich.setImageResource(R.drawable.iconloved);
					DataserviceListener dataserviceListener = APIServiceUtils.getDataFromService();
					Call<String> callBack = dataserviceListener.updateLuotThich("1", listBaiHatSearch.get(getAdapterPosition()).getIdbaihat());
					callBack.enqueue(new Callback<String>() {
						@Override
						public void onResponse(Call<String> call, Response<String> response) {
							String result = response.body();
							if(result.equals("Success")){
								Toast.makeText(context, "Da thich", Toast.LENGTH_SHORT).show();
							} else {
								Toast.makeText(context, "Fail Updates Luot Thich", Toast.LENGTH_SHORT).show();
							}
						}

						@Override
						public void onFailure(Call<String> call, Throwable t) {

						}
					});
				}
			});
		}
	}

}
