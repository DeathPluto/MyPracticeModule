package com.halcyon.practicedemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.halcyon.practicedemo.R;

import java.util.List;

/**
 * ©2015-2016 kcoin.inc.All Rights Reserved
 *
 * @author: L
 * Description:
 */
public class StaggeredAdapter<T> extends RecyclerView.Adapter<StaggeredAdapter.MyViewHolder> {
    private List<T> mDatas;

    public StaggeredAdapter(List<T> datas) {
        mDatas = datas;
    }

    @Override
    public StaggeredAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_staggered, viewGroup, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(StaggeredAdapter.MyViewHolder myViewHolder, int i) {
        myViewHolder.tv.setText(mDatas.get(i) + "");
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.id_num);
        }
    }
}
