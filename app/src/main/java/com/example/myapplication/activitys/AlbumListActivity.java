package com.example.myapplication.activitys;

import android.os.Bundle;
import android.util.Log;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapters.FoodListAdapter;
import com.example.myapplication.helps.RealmHelp;
import com.example.myapplication.models.AlbumModel;
import com.example.myapplication.models.FoodModel;

import io.realm.RealmList;

public class AlbumListActivity extends BaseActivity {
    public  static final String ALBUM_ID="albumId";

   private RecyclerView mRvList;
   private FoodListAdapter mAdapter;

   private  String mAlbumId;
   private RealmHelp mRealmHelper;
   private AlbumModel mAlbumModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_list);
        /**
         *  执行顺序颠倒
         */
        initData();
        initView();

    }
     private void initData(){
        // 从哪个activitytiaozhuan 过来的
        mRealmHelper = new RealmHelp();
        mAlbumId = getIntent().getStringExtra(ALBUM_ID);
         if (mAlbumId != null) {
             mAlbumModel=mRealmHelper.getAlbum(mAlbumId);
         }


     }
    private void initView(){
        initNavBar(true,"甜点",false);
        mRvList = fd(R.id.rv_list);
        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mRvList.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        if (mAlbumModel != null) {
            RealmList<FoodModel> list = mAlbumModel.getList();
            Log.i("Tag", "initView: "+list.size());
            mAdapter = new FoodListAdapter(this,null, list);
            mRvList.setAdapter(mAdapter);
        }else {
            Log.i("Tag", "initView: data==null");
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRealmHelper.close();
    }
}
