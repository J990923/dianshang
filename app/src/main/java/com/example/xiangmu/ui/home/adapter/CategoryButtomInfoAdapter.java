package com.example.xiangmu.ui.home.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.xiangmu.R;
import com.example.xiangmu.base.BaseAdapter;
import com.example.xiangmu.model.home.bean.SPGoodsRelated;
import com.example.xiangmu.utils.TxtUtils;


import java.util.List;

public class CategoryButtomInfoAdapter extends BaseAdapter {

    public CategoryButtomInfoAdapter(Context context, List Data) {
        super(context, Data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.item_good;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        SPGoodsRelated.DataBean.GoodsListBean bean= (SPGoodsRelated.DataBean.GoodsListBean) data;

        ImageView image = (ImageView) vh.getViewById(R.id.image);
        TextView tv_name = (TextView) vh.getViewById(R.id.tv_name);
        TextView tv_price = (TextView) vh.getViewById(R.id.tv_price);
        Glide.with(context).load(bean.getList_pic_url()).into(image);
        tv_name.setText(bean.getName());
        tv_price.setText("￥"+bean.getRetail_price());
    }
}
