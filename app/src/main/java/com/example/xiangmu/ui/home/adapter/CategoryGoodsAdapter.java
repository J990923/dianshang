package com.example.xiangmu.ui.home.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.xiangmu.R;
import com.example.xiangmu.base.BaseAdapter;
import com.example.xiangmu.model.home.bean.GoodsBean;

import java.util.List;

public class CategoryGoodsAdapter extends BaseAdapter {
    public CategoryGoodsAdapter(Context context, List data) {
        super(context, data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.item_category_goods;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        GoodsBean.DataBeanX.DataBean dataBean= (GoodsBean.DataBeanX.DataBean) data;
        ImageView image = (ImageView) vh.getViewById(R.id.image);
        TextView tv_name = (TextView) vh.getViewById(R.id.tv_name);
        TextView tv_price = (TextView) vh.getViewById(R.id.tv_price);
        Glide.with(context).load(dataBean.getList_pic_url()).into(image);
        tv_name.setText(dataBean.getName());
        tv_price.setText("￥"+dataBean.getRetail_price());
    }


}
