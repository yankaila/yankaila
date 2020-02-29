package com.example.myapplication.helps;

import android.content.Context;

import androidx.annotation.Nullable;

import com.example.myapplication.migration.Migration;
import com.example.myapplication.models.AlbumModel;
import com.example.myapplication.models.FoodModel;
import com.example.myapplication.models.FoodSourceModel;
import com.example.myapplication.models.UserModel;
import com.example.myapplication.utils.DataUtils;

import java.io.FileNotFoundException;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class RealmHelp {

    private Realm mRealm;

    public RealmHelp() {
        mRealm = Realm.getDefaultInstance();

    }
    public static void migration(){
        RealmConfiguration conf= getRealmConf();
        Realm.setDefaultConfiguration(conf);
       try {
           Realm.migrateRealm(conf);
       }catch (FileNotFoundException e){
           e.printStackTrace();
       }
    }

    //Realm数据库迁移
    private static RealmConfiguration getRealmConf(){
        return  new RealmConfiguration.Builder()
                      .schemaVersion(1)
                      .migration(new Migration())
                      .build();

    }
    //关闭数据库
    public void close(){
        if (mRealm !=null && !mRealm.isClosed()){
            mRealm.close();
        }
    }
    //保存用户信息
        public void saveUser (UserModel userModel) {
            /**
             *  手动同步事务
             */
            mRealm.beginTransaction();
            mRealm.insert(userModel);
           //  mRealm.insertOrUpdate(userModel);
            mRealm.commitTransaction();
    }
    //返回所有用户
    public List<UserModel> getAllUser(){
        RealmQuery<UserModel> query = mRealm.where(UserModel.class);
        RealmResults<UserModel> results= query.findAll();

        return results;

    }
    //验证用户信息
    public  boolean validateUser(String phone,String password){
        boolean result = false;
        RealmQuery<UserModel> query = mRealm.where(UserModel.class);
        query =query.equalTo("phone",phone)
                .equalTo("password",password);
       UserModel userModel= query.findFirst();

       if (userModel!=null){
           result =true;
       }
       return  result;
    }



    public UserModel getUser(){
       RealmQuery<UserModel> query=mRealm.where(UserModel.class);
       UserModel userModel=query.equalTo("phone",UserHelper.getInstance().getPhone()).findFirst();
       return userModel;
    }


    public void changePassword(String password){
        UserModel userModel=  getUser();
        mRealm.beginTransaction();
        userModel.setPassword(password);
        mRealm.commitTransaction();

    }
    //1.用户登录，存放数据 2.用户退出，删除数据
    public  void setFoodSource(Context context){
        String foodSourceJson =DataUtils.getJsonFromAssets(context,"DataSource.json");
        mRealm.beginTransaction();
        mRealm.createObjectFromJson(FoodSourceModel.class,foodSourceJson);
        mRealm.commitTransaction();
    }
    //删除数据 Realm delete

   public  void removeFoodSource(){
        mRealm.beginTransaction();
        mRealm.delete(FoodSourceModel.class);
       mRealm.delete(FoodModel.class);
       mRealm.delete(AlbumModel.class);
        mRealm.commitTransaction();
   }
   public FoodSourceModel getFoodSource(){
        return mRealm.where(FoodSourceModel.class).findFirst();
   }
   public AlbumModel getAlbum (String albumId){
        return  mRealm.where(AlbumModel.class).equalTo("albumId",albumId).findFirst();
   }
   public FoodModel getFood(String foodId){
        return mRealm.where(FoodModel.class).equalTo("foodId",foodId).findFirst();
   }

}
