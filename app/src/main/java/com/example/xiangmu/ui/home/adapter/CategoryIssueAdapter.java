package com.example.xiangmu.ui.home.adapter;

import android.content.Context;
import android.widget.TextView;


import com.example.xiangmu.R;
import com.example.xiangmu.base.BaseAdapter;
import com.example.xiangmu.model.home.bean.CategoryBean;
import com.example.xiangmu.model.home.bean.SPGoodsDetail;
import com.example.xiangmu.utils.TxtUtils;

import java.util.ArrayList;
import java.util.List;

public class CategoryIssueAdapter extends BaseAdapter {

    public CategoryIssueAdapter(Context context, List Data) {
        super(context, Data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.layout_category_issue_item;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        SPGoodsDetail.DataBeanX.IssueBean bean = (SPGoodsDetail.DataBeanX.IssueBean) data;
        TextView key= (TextView) vh.getViewById(R.id.tv_category_issue_key);
        TextView value= (TextView) vh.getViewById(R.id.tv_category_issue_value);

        TxtUtils.setTextView(key,bean.getQuestion());
        TxtUtils.setTextView(value,bean.getAnswer());
    }
}
