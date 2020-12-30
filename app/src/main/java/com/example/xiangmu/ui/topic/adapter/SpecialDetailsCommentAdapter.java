package com.example.xiangmu.ui.topic.adapter;

import android.content.Context;
import android.widget.TextView;


import com.example.xiangmu.R;
import com.example.xiangmu.base.BaseAdapter;
import com.example.xiangmu.ui.topic.bean.SpecialDetailsCommentBean;
import com.example.xiangmu.utils.TxtUtils;

import java.util.List;

public class SpecialDetailsCommentAdapter extends BaseAdapter {

    public SpecialDetailsCommentAdapter(Context context, List Data) {
        super(context, Data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.layout_special_details_comment_item;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        SpecialDetailsCommentBean.DataBeanX.DataBean bean = (SpecialDetailsCommentBean.DataBeanX.DataBean) data;

        TextView name = (TextView) vh.getViewById(R.id.iv_special_details_comment_item_name);
        TextView date = (TextView) vh.getViewById(R.id.iv_special_details_comment_item_date);
        TextView desc = (TextView) vh.getViewById(R.id.iv_special_details_comment_item_desc);

        TxtUtils.setTextView(name,bean.getUser_info().getUsername());
        TxtUtils.setTextView(date,bean.getAdd_time());
        TxtUtils.setTextView(desc,bean.getContent());
    }
}
