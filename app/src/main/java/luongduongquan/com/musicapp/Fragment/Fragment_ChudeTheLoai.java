package luongduongquan.com.musicapp.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import luongduongquan.com.musicapp.Activity.AllChuDeActivity;
import luongduongquan.com.musicapp.Activity.DanhSachBaiHatActivity;
import luongduongquan.com.musicapp.Model.ChuDe;
import luongduongquan.com.musicapp.Model.TheLoai;
import luongduongquan.com.musicapp.Model.TheLoaiTrongNgay;
import luongduongquan.com.musicapp.R;
import luongduongquan.com.musicapp.Service.APIServiceUtils;
import luongduongquan.com.musicapp.Service.DataserviceListener;
import luongduongquan.com.musicapp.Utils.MyAppUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Fragment_ChudeTheLoai extends Fragment {

	final String TAG = this.getClass().getSimpleName();
	View view;
	HorizontalScrollView horizontalScrollView;
	TextView tvXemTatCaChuDe;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		view = inflater.inflate(R.layout.fragment_chude_the_loai, container, false);
		horizontalScrollView = view.findViewById(R.id.horizontalScroll_ChudeTheloai);
		tvXemTatCaChuDe = view.findViewById(R.id.tvXemThem_ChudeTheloai);
		tvXemTatCaChuDe.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intentToAllChuDe = new Intent(getActivity(), AllChuDeActivity.class);
				startActivity(intentToAllChuDe);
			}
		});
		getData();
		return view;
	}


	public void getData() {
		DataserviceListener dataserviceListener = APIServiceUtils.getDataFromService();

		Call<TheLoaiTrongNgay> callback = dataserviceListener.getTheLoaiTrongNgay();
		Log.d(TAG, "getData: " );

		callback.enqueue(new Callback<TheLoaiTrongNgay>() {
			@Override
			public void onResponse(Call<TheLoaiTrongNgay> call, Response<TheLoaiTrongNgay> response) {
				TheLoaiTrongNgay theLoaiTrongNgay = response.body();

				final ArrayList<ChuDe> listChuDe = new ArrayList<>();
				listChuDe.addAll(theLoaiTrongNgay.getChuDe());

				final ArrayList<TheLoai> listTheLoai = new ArrayList<>();
				listTheLoai.addAll(theLoaiTrongNgay.getTheLoai());

				LinearLayout linearLayout = new LinearLayout(getActivity());
				linearLayout.setOrientation(LinearLayout.HORIZONTAL);

				LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(580, 250);
				layoutParams.setMargins(10,20,10,30);

				for (int i = 0; i <  listChuDe.size(); i++) {
					CardView cardView = new CardView(getActivity());
					cardView.setRadius(10);
					ImageView imageView = new ImageView(getActivity());
					imageView.setScaleType(ImageView.ScaleType.FIT_XY);
					if(listChuDe.get(i).getHinhChuDe() != null){
						Log.d("QUAN123 TAG", "getHinhChuDe: " + listChuDe.get(i).getHinhChuDe());
						String urlHinhAnh =listChuDe.get(i).getHinhChuDe().replace("https", "http");
						Picasso.with(getActivity()).load(urlHinhAnh).fit().into(imageView);
					}
					cardView.setLayoutParams(layoutParams);
					cardView.addView(imageView);
					linearLayout.addView(cardView);
				}

				for (int j = 0; j <  listTheLoai.size(); j++) {
					CardView cardView = new CardView(getActivity());
					cardView.setRadius(10);
					ImageView imageView = new ImageView(getActivity());
					imageView.setScaleType(ImageView.ScaleType.FIT_XY);
					if(listTheLoai.get(j).getHinhTheLoai() != null){
						Log.d("QUAN123 TAG", "HinhTheLoai: " + listTheLoai.get(j).getHinhTheLoai());
						String urlHinhAnh =listTheLoai.get(j).getHinhTheLoai().replace("https", "http");
						Picasso.with(getActivity()).load(urlHinhAnh).fit().into(imageView);
					}
					cardView.setLayoutParams(layoutParams);
					cardView.addView(imageView);
					final int finalJ = j;
					cardView.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							Intent intentToDanhSachBaiHat = new Intent(getActivity(), DanhSachBaiHatActivity.class);
							intentToDanhSachBaiHat.putExtra(MyAppUtils.KEY_INTENT_THELOAI, listTheLoai.get(finalJ));
							startActivity(intentToDanhSachBaiHat);
						}
					});
					linearLayout.addView(cardView);
				}
				horizontalScrollView.addView(linearLayout);



			}

			@Override
			public void onFailure(Call<TheLoaiTrongNgay> call, Throwable t) {

			}
		});
	}
}
