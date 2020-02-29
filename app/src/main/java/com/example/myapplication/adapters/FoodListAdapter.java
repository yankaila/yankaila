package com.example.myapplication.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.activitys.ShowActivity;
import com.example.myapplication.models.FoodModel;

import java.util.List;

public class FoodListAdapter extends RecyclerView.Adapter<FoodListAdapter.ViewHolder> {

     private Context mContext;
     private View mItemView;
     private RecyclerView mRv;
     private  boolean isCalcaulationRvHeight;
     private List<FoodModel> mDataSource;

    public FoodListAdapter(Context context,RecyclerView recyclerView,List<FoodModel> dataSource){
         mContext=context;
         mRv=recyclerView;
         this.mDataSource = dataSource;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        mItemView=LayoutInflater.from(mContext).inflate(R.layout.item_list_food, parent, false);
        return new ViewHolder(mItemView);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
           setRecyclerViewHeight();

           final FoodModel foodModel = mDataSource.get(position);
        Glide.with(mContext)
                .load(foodModel.getPoster())
                 .into(holder.ivIcon);

        holder.tvName.setText(foodModel.getName());
        holder.tvAuthor.setText(foodModel.getAuthor());

          holder.itemView.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Intent intent = new Intent(mContext, ShowActivity.class);
                  intent.putExtra(ShowActivity.FOOD_ID,foodModel.getFoodId());
                  mContext.startActivity(intent);
              }
          });

    }

    @Override
    public int getItemCount() {
        return mDataSource.size();
    }
    private void setRecyclerViewHeight(){
        if(isCalcaulationRvHeight || mRv == null) return;
        isCalcaulationRvHeight = true;
        //获取ItemView的高度
        RecyclerView.LayoutParams itemViewLp = (RecyclerView.LayoutParams) mItemView.getLayoutParams();//
         //获取ItemView的数量
        int itemCount = getItemCount();
        //获取ItemView的高度*获取ItemView的数量=RecyclerView的高度
        int recyclerViewHeight= itemViewLp.height* itemCount;
        //设置
        LinearLayout.LayoutParams rvLp = (LinearLayout.LayoutParams) mRv.getLayoutParams();
        rvLp.height = recyclerViewHeight;
        mRv.setLayoutParams(rvLp);

    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView ivIcon;
        View itemView;
        TextView tvName,tvAuthor;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView= itemView;
            ivIcon = itemView.findViewById(R.id.iv_icon);
            tvName=itemView.findViewById(R.id.tv_name);
            tvAuthor=itemView.findViewById(R.id.tv_author);
        }
    }
}
