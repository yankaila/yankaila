package com.example.myapplication.helps;

public class UserHelper {
     //1.当用户没有登录，利用SharedPreferences保存登录标记，退出，则删除掉
    //用UserHelper保存登录用户信息
    //检测SharedPreferences是否有登录用户
    //存在则为UserHelp赋值
    //不存在，进入登录页面
    private  static  UserHelper instance;

    private UserHelper(){};
    public static  UserHelper getInstance(){
        if (instance == null) {
            synchronized (UserHelper.class){
                if (instance == null){
                     instance = new UserHelper();
                }
        }
        }
        return instance;
    }
    private String phone;
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


}
