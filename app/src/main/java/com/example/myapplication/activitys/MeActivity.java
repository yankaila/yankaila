package com.example.myapplication.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.helps.UserHelper;
import com.example.myapplication.utils.UserUtil;

public class MeActivity extends BaseActivity {
    private TextView mTvUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);
        initView();
    }
    private  void initView(){
        initNavBar(true,"个人中心",false);
        mTvUser = fd(R.id.tv_user);
        mTvUser.setText("用户名"+ UserHelper.getInstance().getPhone());
    }
    public void onChangeClick(View v){
        startActivity(new Intent(this,ChangePasswordActivity.class));

    }
    public void onLogoutClick (View v){
        UserUtil.logout(this);
    }
}
