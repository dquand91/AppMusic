package luongduongquan.com.musicapp.Fragment;


import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

import de.hdodenhof.circleimageview.CircleImageView;
import luongduongquan.com.musicapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_DiaNhac extends Fragment {

	View view;

	CircleImageView circleImageView;

	ObjectAnimator objectAnimator;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		view = inflater.inflate(R.layout.fragment_dia_nhac, container, false);

		circleImageView = view.findViewById(R.id.imgDiaNhac_DiaNhacFragment);

		objectAnimator = ObjectAnimator.ofFloat(circleImageView ,
				"rotation", 0f, 360f);
		objectAnimator.setDuration(10000); // miliseconds
		objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
		objectAnimator.setRepeatMode(ValueAnimator.RESTART);
		objectAnimator.setInterpolator(new LinearInterpolator());
		objectAnimator.start();


		return view;
	}

}
