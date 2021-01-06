package com.example.xiangmu.ui.home.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.xiangmu.R;
import com.example.xiangmu.base.BaseAdapter;
import com.example.xiangmu.model.home.bean.HomeBean;
import com.example.xiangmu.utils.TxtUtils;

import java.util.List;

public class TopicAdapter extends BaseAdapter <HomeBean.DataBean.TopicListBean> {
    public TopicAdapter(Context context, List <HomeBean.DataBean.TopicListBean> data) {
        super(context, data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.item_topic;
    }

    @Override
    protected void bindData(HomeBean.DataBean.TopicListBean data, VH vh) {
        ImageView image = (ImageView) vh.getViewById(R.id.image);
        TextView tv_title = (TextView) vh.getViewById(R.id.tv_title);
        TextView tv_info = (TextView) vh.getViewById(R.id.tv_info);
        TextView tv_subtitle = (TextView) vh.getViewById(R.id.tv_subtitle);
        Glide.with(context).load(data.getItem_pic_url()).into(image);
        TxtUtils.setTextView(tv_info,"￥"+data.getPrice_info());
        TxtUtils.setTextView(tv_title,data.getTitle());
        TxtUtils.setTextView(tv_subtitle,data.getSubtitle());
    }
}
