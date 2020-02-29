package com.example.myapplication.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.R;
import com.example.myapplication.utils.UserUtil;
import com.example.myapplication.views.InputView;

public class LoginActivity extends BaseActivity {

     private InputView mInputPhone,mInputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }
    private void initView(){
        initNavBar(false,"登录",false);

        mInputPhone=fd(R.id.input_phone);
        mInputPassword=fd(R.id.input_password);
    }

    public void onRegisterClick(View v){
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);

    }
    public void onCommitClick(View v){

        String phone = mInputPhone.getInputStr();
        String password = mInputPassword.getInputStr();

       if(!UserUtil.validateLogin(this,phone,password)){
             return;
       }
        Intent intent = new Intent(this,MainActivity.class);
       startActivity(intent);
       finish();
    }

}
