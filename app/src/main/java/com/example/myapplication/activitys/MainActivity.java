package com.example.myapplication.activitys;

import android.os.Bundle;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.activitys.BaseActivity;
import com.example.myapplication.adapters.FoodGridAdapter;
import com.example.myapplication.adapters.FoodListAdapter;
import com.example.myapplication.helps.RealmHelp;
import com.example.myapplication.models.FoodSourceModel;
import com.example.myapplication.views.GridSpaceItemDecoration;

public class MainActivity extends BaseActivity {
   private RecyclerView mRvGrid,mRvList;
   private FoodGridAdapter mGridAdapter;
   private FoodListAdapter mListAdapter;
   private RealmHelp mRealmHelper;
   private FoodSourceModel mFoodSourceModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
        // 定位空指针位置 你操作
    }
    private void initData(){
         mRealmHelper = new RealmHelp();
         mFoodSourceModel= mRealmHelper.getFoodSource();

    }
    private void initView(){
       initNavBar(false,"Sweet Pastry",true);
       mRvGrid = fd(R.id.rv_grid);
       mRvGrid.setLayoutManager(new GridLayoutManager(this,3));
       mRvGrid.addItemDecoration(new GridSpaceItemDecoration(getResources().getDimensionPixelSize(R.dimen.albumMarginSize),mRvGrid));
       mRvGrid.setNestedScrollingEnabled(false);
        mGridAdapter = new FoodGridAdapter(this,mFoodSourceModel.getAlbum());
       mRvGrid.setAdapter(mGridAdapter);

       mRvList = fd(R.id.rv_list);
       mRvList.setLayoutManager(new LinearLayoutManager(this));
        mRvList.setNestedScrollingEnabled(false);
        mRvList.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
       mListAdapter = new FoodListAdapter(this,mRvList,mFoodSourceModel.getHot());
       mRvList.setAdapter(mListAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRealmHelper.close();
    }
}
