package com.example.xiangmu.ui.home.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.xiangmu.R;
import com.example.xiangmu.base.BaseAdapter;
import com.example.xiangmu.model.home.bean.BrandBean;

import java.util.List;

public class BrandListAdapter extends BaseAdapter <BrandBean.DataBeanX.DataBean> {
    public BrandListAdapter(Context context, List <BrandBean.DataBeanX.DataBean> data) {
        super(context, data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.item_brandlist;
    }

    @Override
    protected void bindData(BrandBean.DataBeanX.DataBean data, VH vh) {
        ImageView image = (ImageView) vh.getViewById(R.id.image);
        TextView tv_name = (TextView) vh.getViewById(R.id.tv_name);
        TextView tv_price = (TextView) vh.getViewById(R.id.tv_price);
        Glide.with(context).load(data.getApp_list_pic_url()).into(image);
        tv_name.setText(data.getName());
        tv_price.setText(data.getFloor_price());
    }


}
