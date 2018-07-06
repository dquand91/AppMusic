package luongduongquan.com.musicapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import luongduongquan.com.musicapp.Activity.AllTheLoaiTheoChuDeActivity;
import luongduongquan.com.musicapp.Model.ChuDe;
import luongduongquan.com.musicapp.R;
import luongduongquan.com.musicapp.Utils.MyAppUtils;

public class AllChuDeAdapter extends RecyclerView.Adapter<AllChuDeAdapter.AllChuDeViewHolder> {

	Context context;
	ArrayList<ChuDe> listChuDe;

	public AllChuDeAdapter(Context context, ArrayList<ChuDe> listChuDe) {
		this.context = context;
		this.listChuDe = listChuDe;
	}

	@NonNull
	@Override
	public AllChuDeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(R.layout.row_all_chude_item, parent, false);


		return new AllChuDeViewHolder(view);
	}

	@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
	@Override
	public void onBindViewHolder(@NonNull AllChuDeViewHolder holder, int position) {
		ChuDe chuDe = listChuDe.get(position);
		Log.d("QUAN123", "onBindViewHolder: " + MyAppUtils.replaceHTTPStoHTTP(chuDe.getHinhChuDe()));
		Picasso.with(context).load(MyAppUtils.replaceHTTPStoHTTP(chuDe.getHinhChuDe())).placeholder(context.getResources().getDrawable(R.drawable.custom_background_banner)).into(holder.imgChuDe);
	}

	@Override
	public int getItemCount() {
		return listChuDe.size();
	}

	public class AllChuDeViewHolder extends RecyclerView.ViewHolder {

		ImageView imgChuDe;

		public AllChuDeViewHolder(View itemView) {
			super(itemView);

			imgChuDe = itemView.findViewById(R.id.imgChuDe_AllChuDeItem);
			imgChuDe.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intentToAllTheLoai = new Intent(context, AllTheLoaiTheoChuDeActivity.class);
					intentToAllTheLoai.putExtra(MyAppUtils.KEY_INTENT_CHUDE, listChuDe.get(getPosition()));
					context.startActivity(intentToAllTheLoai);
				}
			});

		}
	}

}
