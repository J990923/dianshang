package com.example.xiangmu.ui.home.adapter;

import android.content.Context;
import android.widget.TextView;


import com.example.xiangmu.R;
import com.example.xiangmu.base.BaseAdapter;
import com.example.xiangmu.model.home.bean.CategoryBean;
import com.example.xiangmu.model.home.bean.SPGoodsDetail;
import com.example.xiangmu.utils.TxtUtils;

import java.util.List;

public class CategoryParameterAdapter extends BaseAdapter {

    public CategoryParameterAdapter(Context context, List Data) {
        super(context, Data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.layout_category_parameter_item;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        SPGoodsDetail.DataBeanX.AttributeBean bean = (SPGoodsDetail.DataBeanX.AttributeBean) data;
        TextView key= (TextView) vh.getViewById(R.id.tv_category_parameter_key);
        TextView value= (TextView) vh.getViewById(R.id.tv_category_parameter_value);

        TxtUtils.setTextView(key,bean.getName());
        TxtUtils.setTextView(value,bean.getValue());
    }
}
