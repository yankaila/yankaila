package com.example.myapplication.utils;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

import com.blankj.utilcode.util.EncryptUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.StringUtils;
import com.example.myapplication.activitys.LoginActivity;
import com.example.myapplication.helps.RealmHelp;
import com.example.myapplication.helps.UserHelper;
import com.example.myapplication.models.UserModel;

import java.util.List;

public class UserUtil {

    public static  boolean validateLogin(Context context,String phone,String password){

         if (!RegexUtils.isMobileExact(phone)){
             Toast.makeText(context,"无效手机号",Toast.LENGTH_SHORT).show();
             return false;
         }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(context,"密码不能为空",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!UserUtil.userExistFromPhone(phone)){
            Toast.makeText(context,"当前手机号未被注册",Toast.LENGTH_SHORT).show();
            return  false;
        }
        RealmHelp realmHelper = new RealmHelp();
        boolean result = realmHelper.validateUser(phone,EncryptUtils.encryptMD5ToString(password));

         if (!result){
             Toast.makeText(context,"用户手机密码不正确"  ,Toast.LENGTH_SHORT).show();
             return  false;
         }
         //保存用户登录标记
        boolean isSave = SPUtils.saveUser(context,phone);
         if (!isSave){
             Toast.makeText(context,"系统错误"  ,Toast.LENGTH_SHORT).show();
              return false;
         }
         //保存用户标记，在全局单例类中
        UserHelper.getInstance().setPhone(phone);
         realmHelper.setFoodSource(context);
         realmHelper.close();
        return  true;

    }
    public static void logout(Context context){
        boolean isRemove=SPUtils.removeUser(context);
        if (!isRemove){
            Toast.makeText(context,"系统错误"  ,Toast.LENGTH_SHORT).show();
              return;
        }
        RealmHelp realmHelper = new RealmHelp();
        realmHelper.removeFoodSource();
        realmHelper.close();

        Intent intent = new Intent(context, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static boolean registerUser(Context context,String phone,String password,String passwordConfirm){
        if (!RegexUtils.isMobileExact(phone)){
            Toast.makeText(context,"无效手机号",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(StringUtils.isEmpty(password)||!password.equals(passwordConfirm)){
            Toast.makeText(context,"请确认密码",Toast.LENGTH_SHORT).show();
            return false;
        }
        //是否已被注册
        //保存
        if (UserUtil.userExistFromPhone(phone)){
            Toast.makeText(context,"该手机号码已存在",Toast.LENGTH_SHORT).show();
            return false;
        }
        UserModel userModel = new UserModel();
        userModel.setPhone(phone);
        userModel.setPassword(EncryptUtils.encryptMD5ToString(password));

        saveUser(userModel);
        return true;


    }
    public static void saveUser(UserModel userModel){
        RealmHelp realmHelp = new RealmHelp();
        realmHelp.saveUser(userModel);
        realmHelp.close();

    }
    //根据用户的手机号判断用户是否存在
    public  static boolean userExistFromPhone(String phone){
        boolean result = false;
        RealmHelp realmHelper = new RealmHelp();
        List<UserModel> allUser = realmHelper.getAllUser();
        for (UserModel userModel: allUser){
            if (userModel.getPhone().equals(phone)){
                result = true;
                break;
            }
        }
        realmHelper.close();
        return  result;
    }
    //验证是否存在已登录用户
    public static boolean validateUserLogin(Context context){
        return SPUtils.isLoginUser(context);
    }
     /**
      * 修改密码
      *   1.原密码是否输入
      *   2.新密码是否输入并且新密码与确定密码是否相同
      *   3.原密码输入是否正确（Realm获取模型，再匹配）
      * 2.利用Realm模型自动更新的特征完成密码的修改

      */
    public static boolean changePassword(Context context,String oldPassword,String password,String passwordConfirm){

        if (TextUtils.isEmpty(oldPassword)){
              Toast.makeText(context,"请输入原密码啊",Toast.LENGTH_SHORT).show();
              return false;
          }
        if (TextUtils.isEmpty(password)|| !password.equals(passwordConfirm)){
            Toast.makeText(context,"请确认密码",Toast.LENGTH_SHORT).show();
            return false;
        }
      RealmHelp realmHelper= new RealmHelp();
        UserModel userModel=  realmHelper.getUser();
        if (!EncryptUtils.encryptMD5ToString(oldPassword).equals(userModel.getPassword())){
            Toast.makeText(context,"原密码不正确",Toast.LENGTH_SHORT).show();
            return false;
        }
        realmHelper.changePassword(EncryptUtils.encryptMD5ToString(password));

        realmHelper.close();

         return true;
    }
}
