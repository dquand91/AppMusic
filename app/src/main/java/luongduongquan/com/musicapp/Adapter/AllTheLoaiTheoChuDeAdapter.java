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
import luongduongquan.com.musicapp.Model.TheLoai;
import luongduongquan.com.musicapp.R;
import luongduongquan.com.musicapp.Utils.MyAppUtils;

import static luongduongquan.com.musicapp.Utils.MyAppUtils.KEY_INTENT_THELOAI;

public class AllTheLoaiTheoChuDeAdapter extends RecyclerView.Adapter<AllTheLoaiTheoChuDeAdapter.AllTheLoaiTheoChuDeViewHolder> {

	Context context;
	ArrayList<TheLoai> listTheLoai;

	public AllTheLoaiTheoChuDeAdapter(Context context, ArrayList<TheLoai> listTheLoai) {
		this.context = context;
		this.listTheLoai = listTheLoai;
	}

	@NonNull
	@Override
	public AllTheLoaiTheoChuDeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

		LayoutInflater layoutInflater = LayoutInflater.from(context);
		View view = layoutInflater.inflate(R.layout.row_theloaitheochude_item, parent, false);

		return new AllTheLoaiTheoChuDeViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull AllTheLoaiTheoChuDeViewHolder holder, int position) {

		TheLoai theLoai = listTheLoai.get(position);
		Picasso.with(context).load(MyAppUtils.replaceHTTPStoHTTP(theLoai.getHinhTheLoai())).into(holder.imgHinh);
		holder.tvTenTheLoai.setText(theLoai.getTenTheLoai());

	}

	@Override
	public int getItemCount() {
		return listTheLoai.size();
	}

	public class AllTheLoaiTheoChuDeViewHolder extends RecyclerView.ViewHolder {

		ImageView imgHinh;
		TextView tvTenTheLoai;

		public AllTheLoaiTheoChuDeViewHolder(View itemView) {
			super(itemView);

			imgHinh = itemView.findViewById(R.id.img_TheLoaiTheoChuDeItem);
			tvTenTheLoai = itemView.findViewById(R.id.tvTenTheLoai_TheLoaiTheoChuDeItem);
			itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intentToDanhSachBaiHat = new Intent(context, DanhSachBaiHatActivity.class);
					intentToDanhSachBaiHat.putExtra(KEY_INTENT_THELOAI, listTheLoai.get(getPosition()));
					context.startActivity(intentToDanhSachBaiHat);

				}
			});

		}
	}

}
