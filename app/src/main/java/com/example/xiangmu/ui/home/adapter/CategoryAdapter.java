package com.example.xiangmu.ui.home.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.xiangmu.R;
import com.example.xiangmu.base.BaseAdapter;
import com.example.xiangmu.model.home.bean.HomeBean;

import java.util.List;

public class CategoryAdapter extends BaseAdapter<HomeBean.DataBean.CategoryListBean.GoodsListBean> {

    public CategoryAdapter(Context context, List <HomeBean.DataBean.CategoryListBean.GoodsListBean> data) {
        super(context, data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.item_good;
    }

    @Override
    protected void bindData(HomeBean.DataBean.CategoryListBean.GoodsListBean data, VH vh) {
        ImageView image = (ImageView) vh.getViewById(R.id.image);
        TextView tv_name = (TextView) vh.getViewById(R.id.tv_name);
        TextView tv_price = (TextView) vh.getViewById(R.id.tv_price);
        Glide.with(context).load(data.getList_pic_url()).into(image);
        tv_name.setText(data.getName());
        tv_price.setText("￥"+data.getRetail_price());
    }
}
