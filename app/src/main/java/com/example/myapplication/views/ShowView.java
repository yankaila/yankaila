package com.example.myapplication.views;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;

public class ShowView extends FrameLayout {
    private Context mContext;
    private View mView;
    private ImageView mIvIcon;

    public ShowView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public ShowView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }


    public ShowView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ShowView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }
    private void  init(Context context){
      mContext = context;
      mView=LayoutInflater.from(mContext).inflate(R.layout.activity_show_view,this,false);
      mIvIcon = mView.findViewById(R.id.iv_icon);
      addView(mView);
         }
         //设置圆形图像
         public void setFoodIcon(String icon){
             Glide.with(mContext)
                  .load(icon)
                     .into(mIvIcon);
         }
}
