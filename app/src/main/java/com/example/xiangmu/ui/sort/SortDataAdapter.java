package com.example.xiangmu.ui.sort;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.xiangmu.R;
import com.example.xiangmu.base.BaseAdapter;
import com.example.xiangmu.model.home.bean.SortDataBean;

import java.util.List;

public class SortDataAdapter extends BaseAdapter {

    public SortDataAdapter(Context context, List data) {
        super(context, data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.item_sortdata;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        ImageView image = (ImageView) vh.getViewById(R.id.image);
        TextView tv_name = (TextView) vh.getViewById(R.id.tv_name);
        SortDataBean.DataBean.CurrentCategoryBean.SubCategoryListBean subCategoryListBean = (SortDataBean.DataBean.CurrentCategoryBean.SubCategoryListBean) data;
        Glide.with(context).load(subCategoryListBean.getWap_banner_url()).into(image);
        tv_name.setText(subCategoryListBean.getName());

    }
}
