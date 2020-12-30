package com.example.xiangmu.ui.topic.adapter;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;


import com.example.xiangmu.R;
import com.example.xiangmu.base.BaseAdapter;
import com.example.xiangmu.utils.ImageLoaderUtils;

import java.util.List;

public class SpecialDetailsAdapter extends BaseAdapter {

    public SpecialDetailsAdapter(Context context, List Data) {
        super(context, Data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.layout_special_details_item;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        String str = (String) data;
        ImageView img = (ImageView) vh.getViewById(R.id.iv_special_details_item_img);
        ImageLoaderUtils.loadImage(str,img);
    }
}
