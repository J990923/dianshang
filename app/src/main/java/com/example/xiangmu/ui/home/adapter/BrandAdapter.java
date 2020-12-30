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

public class BrandAdapter extends RecyclerView.Adapter <BrandAdapter.ViewHolder> {
    private Context context;
    private List <HomeBean.DataBean.BrandListBean> brandList;

    public BrandAdapter(Context context, List <HomeBean.DataBean.BrandListBean> brandList) {
        this.context = context;
        this.brandList = brandList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_brand, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HomeBean.DataBean.BrandListBean brandListBean = brandList.get(position);
        holder.price.setText(brandListBean.getFloor_price()+"元起");
        holder.tv_name.setText(brandListBean.getName());
        Glide.with(context).load(brandListBean.getNew_pic_url()).into(holder.image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickItem!=null){
                    onClickItem.ClicItem(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return brandList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView tv_name;
        public TextView price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.image = (ImageView) itemView.findViewById(R.id.image);
            this.tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            this.price = (TextView) itemView.findViewById(R.id.price);
        }
    }
    public interface OnClickItem{
        void ClicItem(int pos);
    }
    OnClickItem onClickItem;

    public void setOnClickItem(OnClickItem onClickItem) {
        this.onClickItem = onClickItem;
    }
}
