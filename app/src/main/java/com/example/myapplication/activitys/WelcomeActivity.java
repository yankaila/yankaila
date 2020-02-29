package com.example.myapplication.activitys;

import android.content.Intent;
import android.os.Bundle;

import com.example.myapplication.R;
import com.example.myapplication.activitys.BaseActivity;
import com.example.myapplication.activitys.MainActivity;
import com.example.myapplication.utils.UserUtil;

import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends BaseActivity {

    private Timer mTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        init();
    }
    private void init(){
        final boolean isLogin=UserUtil.validateUserLogin(this);
        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (isLogin) {
                    toMain();
                } else {
                    toLogin();
                }
            }
        },5*1000);
    }
  private  void toMain(){
      Intent intent = new Intent(this, MainActivity.class);
      startActivity(intent);
      finish();
  }
  private  void toLogin(){
      Intent intent = new Intent(this, LoginActivity.class);
      startActivity(intent);
      finish();
  }
}
