package com.example.myapplication.views;

import android.graphics.Rect;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GridSpaceItemDecoration extends RecyclerView.ItemDecoration {

    private  int mSpace;

     public GridSpaceItemDecoration(int space,RecyclerView parent){
         mSpace =space;
         getRecyclerViewOffsets(parent);
     }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.left = mSpace;


    }
    private void getRecyclerViewOffsets(RecyclerView parent){
        LinearLayout.LayoutParams layoutParams=(LinearLayout.LayoutParams)parent.getLayoutParams();
        layoutParams.leftMargin = -mSpace;
        parent.setLayoutParams(layoutParams);
    }
}
