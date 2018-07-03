package luongduongquan.com.musicapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import luongduongquan.com.musicapp.Model.QuangCao;
import luongduongquan.com.musicapp.R;

;

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
	public Object instantiateItem(@NonNull ViewGroup container, int position) {

		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(R.layout.row_banner_item, null);

		final ImageView imgBackground = view.findViewById(R.id.imgBackground_bannerItem);
		ImageView imgSongThumbBanner = view.findViewById(R.id.imgSongThumb_banner);
		TextView tvTitleSongBanner = view.findViewById(R.id.tvSongTitle_banner);
		TextView tvDescriptionSongBanner = view.findViewById(R.id.tvDescript_banner);
		View backgroundViewBanner = view.findViewById(R.id.backgroundView_bannerItem);

		String urlHinhAnh = listBanner.get(position).getHinhAnh().replace("https", "http");
		String urlHinhBaiHat = listBanner.get(position).getHinhBaiHat().replace("https", "http");

//		Picasso.with(context).load(listBanner.get(position).getHinhAnh()).fit().into(imgBackground);
//		Picasso.with(context).load(listBanner.get(position).getHinhBaiHat()).fit().into(imgSongThumbBanner);

		Picasso.with(context).load(urlHinhAnh).fit().into(imgBackground);
		Picasso.with(context).load(urlHinhBaiHat).fit().into(imgSongThumbBanner);

//		backgroundViewBanner.setVisibility(View.INVISIBLE);

		Log.d("QUAN123", "getHinhAnh: " + urlHinhAnh);
		Log.d("QUAN123", "getHinhBaiHat: " + urlHinhBaiHat);
		tvTitleSongBanner.setText(listBanner.get(position).getTenBaiHat());
		tvDescriptionSongBanner.setText(listBanner.get(position).getNoiDung());

		container.addView(view);

		return view;
	}

	@Override
	public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
		container.removeView((View) object);
	}
}
