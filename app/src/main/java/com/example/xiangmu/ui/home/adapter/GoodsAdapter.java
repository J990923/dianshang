package com.example.xiangmu.ui.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.xiangmu.R;
import com.example.xiangmu.model.home.bean.HomeBean;

import java.util.List;

public class GoodsAdapter extends RecyclerView.Adapter <GoodsAdapter.ViewHolder> {
    private Context context;
    List <HomeBean.DataBean.NewGoodsListBean> newGoodsList;

    public GoodsAdapter(Context context, List <HomeBean.DataBean.NewGoodsListBean> newGoodsList) {
        this.context = context;
        this.newGoodsList = newGoodsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_good, parent, false);
        return new ViewHolder(inflate);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HomeBean.DataBean.NewGoodsListBean newGoodsListBean = newGoodsList.get(position);
        holder.tv_name.setText(newGoodsListBean.getName());
        holder.tv_price.setText("￥"+newGoodsListBean.getRetail_price());
        Glide.with(context).load(newGoodsListBean.getList_pic_url()).into(holder.image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickItem!=null){
                    onClickItem.ClickItem(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return newGoodsList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView tv_name;
        public TextView tv_price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.image = (ImageView) itemView.findViewById(R.id.image);
            this.tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            this.tv_price = (TextView) itemView.findViewById(R.id.tv_price);
        }
    }
public interface onClickItem{
        void ClickItem(int id);
}
onClickItem onClickItem;

    public void setOnClickItem(GoodsAdapter.onClickItem onClickItem) {
        this.onClickItem = onClickItem;
    }
}
