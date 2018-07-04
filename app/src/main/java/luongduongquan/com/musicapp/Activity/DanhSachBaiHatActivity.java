package luongduongquan.com.musicapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import luongduongquan.com.musicapp.Model.QuangCao;
import luongduongquan.com.musicapp.R;
import luongduongquan.com.musicapp.Utils.MyAppUtils;

public class DanhSachBaiHatActivity extends AppCompatActivity {

	QuangCao quangCao;

	CoordinatorLayout coordinatorLayout;
	CollapsingToolbarLayout collapsingToolbarLayout;
	Toolbar toolbar;
	RecyclerView recyclerViewDanhSachBaiHat;
	FloatingActionButton floatingActionButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_danh_sach_bai_hat);
		getDataIntent();
		initView();
	}

	private void initView() {
		coordinatorLayout = findViewById(R.id.coordinator_DanhSachBaiHat);
		collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar_DanhSachBaiHat);
		toolbar = findViewById(R.id.toolbar_DanhSachBaiHat);
		recyclerViewDanhSachBaiHat = findViewById(R.id.recyclerView_DanhSachBaiHat);
		floatingActionButton = findViewById(R.id.btnPlay_DanhSachBaiHat);
	}

	private void getDataIntent() {

		Intent intent = getIntent();
		if(intent != null){
			if(intent.hasExtra(MyAppUtils.KEY_INTENT_BANNER)){
				quangCao = (QuangCao) intent.getSerializableExtra(MyAppUtils.KEY_INTENT_BANNER);

			}
		}

	}


}
