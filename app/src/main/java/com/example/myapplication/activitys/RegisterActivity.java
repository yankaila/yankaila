package com.example.myapplication.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.myapplication.R;
import com.example.myapplication.utils.UserUtil;
import com.example.myapplication.views.InputView;

public class RegisterActivity extends BaseActivity {

    private InputView mInputPhone,mInputPassword,mInputPasswordConfirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }
    private void initView(){
        initNavBar(true,"注册",false);
        mInputPhone=fd(R.id.input_phone);
        mInputPassword=fd(R.id.input_password);
        mInputPasswordConfirm=fd(R.id.input_password_confirm);
    }
    //输入合法性
    //是否输入密码和确定密码，并且这两次的输入是否相同
    //输入的手机号是否已经被注册
    //保存输入的手机号和密码
    //MD5加密密码

    public void onRegisterClick(View v){
       String phone = mInputPhone.getInputStr();
       String password = mInputPassword.getInputStr();
       String passwordConfirm = mInputPasswordConfirm.getInputStr();

        boolean result=UserUtil.registerUser(this,phone,password,passwordConfirm);
         if(!result) return;
        onBackPressed();
    }
}
