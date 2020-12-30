package com.example.xiangmu.ui.home.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.xiangmu.R;
import com.example.xiangmu.base.BaseAdapter;
import com.example.xiangmu.model.home.bean.HomeBean;

import java.util.List;

public class HotGoodsAdapter extends BaseAdapter<HomeBean.DataBean.HotGoodsListBean> {


    public HotGoodsAdapter(Context context, List <HomeBean.DataBean.HotGoodsListBean> data) {
        super(context, data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.item_hotgood;
    }

    @Override
    protected void bindData(HomeBean.DataBean.HotGoodsListBean data, VH vh) {
        TextView name = (TextView) vh.getViewById(R.id.tv_name);
        TextView brief = (TextView) vh.getViewById(R.id.tv_brief);
        TextView price = (TextView) vh.getViewById(R.id.tv_price);
        ImageView image = (ImageView) vh.getViewById(R.id.image);
        name.setText(data.getName());
        brief.setText(data.getGoods_brief());
        price.setText("￥"+data.getRetail_price());
        Glide.with(context).load(data.getList_pic_url()).into(image);
    }
}
