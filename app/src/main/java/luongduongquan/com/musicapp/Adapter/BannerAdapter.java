package luongduongquan.com.musicapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import luongduongquan.com.musicapp.Activity.DanhSachBaiHatActivity;
import luongduongquan.com.musicapp.Model.QuangCao;
import luongduongquan.com.musicapp.R;
import luongduongquan.com.musicapp.Utils.MyAppUtils;

;import static luongduongquan.com.musicapp.Utils.MyAppUtils.KEY_INTENT_BANNER;

public class BannerAdapter extends PagerAdapter {

	Context context;
	ArrayList<QuangCao> listBanner;


	public BannerAdapter(Context context, ArrayList<QuangCao> listBanner) {
		this.context = context;
		this.listBanner = listBanner;
	}

	@Override
	public int getCount() {
		return listBanner.size();
	}

	@Override
	public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
		return view == object;
	}

	@NonNull
	@Override
	public Object instantiateItem(@NonNull final ViewGroup container, final int position) {

		final LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(R.layout.row_banner_item, null);

		final ImageView imgBackground = view.findViewById(R.id.imgBackground_bannerItem);
		ImageView imgSongThumbBanner = view.findViewById(R.id.imgSongThumb_banner);
		TextView tvTitleSongBanner = view.findViewById(R.id.tvSongTitle_banner);
		TextView tvDescriptionSongBanner = view.findViewById(R.id.tvDescript_banner);
		View backgroundViewBanner = view.findViewById(R.id.backgroundView_bannerItem);

//		String urlHinhAnh = listBanner.get(position).getHinhAnh().replace("https", "http");
//		String urlHinhBaiHat = listBanner.get(position).getHinhBaiHat().replace("https", "http");

//		Picasso.with(context).load(listBanner.get(position).getHinhAnh()).fit().into(imgBackground);
//		Picasso.with(context).load(listBanner.get(position).getHinhBaiHat()).fit().into(imgSongThumbBanner);

		Picasso.with(context).load(MyAppUtils.replaceHTTPStoHTTP(listBanner.get(position).getHinhAnh())).fit().into(imgBackground);
		Picasso.with(context).load(MyAppUtils.replaceHTTPStoHTTP(listBanner.get(position).getHinhBaiHat())).fit().into(imgSongThumbBanner);

		tvTitleSongBanner.setText(listBanner.get(position).getTenBaiHat());
		tvDescriptionSongBanner.setText(listBanner.get(position).getNoiDung());

		view.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intentToDanhSachBaiHat = new Intent(context, DanhSachBaiHatActivity.class);
				intentToDanhSachBaiHat.putExtra(KEY_INTENT_BANNER, listBanner.get(position));
				context.startActivity(intentToDanhSachBaiHat);
			}
		});
		container.addView(view);

		return view;
	}

	@Override
	public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
		container.removeView((View) object);
	}
}
