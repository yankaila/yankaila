package com.example.myapplication.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication.R;
import com.example.myapplication.helps.RealmHelp;
import com.example.myapplication.models.FoodModel;
import com.example.myapplication.views.ShowView;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class ShowActivity extends BaseActivity {
    public static final String FOOD_ID="foodId";

    private ImageView mIvBg;
     private ShowView  mShowView;
     private String mFoodId;
     private RealmHelp mRealmHelper;
     private FoodModel mFoodModel;
     private TextView mTvName,mTvAuthor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initData();
        initView();
    }
    private void  initData(){
        mFoodId = getIntent().getStringExtra(FOOD_ID);
        mRealmHelper = new RealmHelp();
        mFoodModel=mRealmHelper.getFood(mFoodId);
    }
    private void initView(){
       mIvBg = fd(R.id.iv_bg);
       mTvName=fd(R.id.tv_name);
       mTvAuthor=fd(R.id.tv_author);
        Glide.with(this)
                .load(mFoodModel.getPoster())
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(25,10)))
                .into(mIvBg);
        mTvName.setText(mFoodModel.getName());
        mTvAuthor.setText(mFoodModel.getAuthor());
        mShowView=fd(R.id.show_1);
        mShowView.setFoodIcon(mFoodModel.getPoster());
    }
    public void onBackClick(View view){
         onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mRealmHelper.close();

    }
}
