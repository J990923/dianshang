package com.example.xiangmu.ui.shop;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.xiangmu.R;
import com.example.xiangmu.base.BaseAdapter;
import com.example.xiangmu.interfaces.Callback;
import com.example.xiangmu.model.shap.CarBean;
import com.example.xiangmu.utils.ImageLoader;
import com.example.xiangmu.widget.NumberSelect;

import java.util.List;

public class ShopAdapter extends BaseAdapter<CarBean.DataBean.CartListBean> {

    private boolean isEdit; //是否是编辑状态
    private UpdateItem updateItem;
    public void setUpdateItem(UpdateItem item){
        this.updateItem = item;
    }
    public ShopAdapter(Context context, List <CarBean.DataBean.CartListBean> data) {
        super(context, data);
    }

    public void setEditState(boolean bool){
        isEdit = bool;
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.item_car;
    }

    @Override
    protected void bindData(CarBean.DataBean.CartListBean data, VH vh) {

        CheckBox checkBox = (CheckBox) vh.getViewById(R.id.checkbox);
        ImageView imgItem = (ImageView) vh.getViewById(R.id.img_item);
        TextView txtName = (TextView) vh.getViewById(R.id.txt_name);
        TextView txtPrice = (TextView) vh.getViewById(R.id.txt_price);
        TextView txtNumber = (TextView) vh.getViewById(R.id.txt_number);
        TextView txtEditTitle = (TextView) vh.getViewById(R.id.txt_edit_title);
        NumberSelect numberSelect = (NumberSelect) vh.getViewById(R.id.layout_change);

        txtName.setVisibility(isEdit?View.GONE:View.VISIBLE);
        txtNumber.setVisibility(isEdit?View.GONE:View.VISIBLE);
        txtEditTitle.setVisibility(isEdit?View.VISIBLE:View.GONE);
        numberSelect.setVisibility(isEdit?View.VISIBLE:View.GONE);

        // 设置选中状态
        checkBox.setChecked(isEdit?data.selectEdit:data.selectOrder);
        Glide.with(context).load(data.getList_pic_url()).into(imgItem);
        txtPrice.setText("￥"+data.getRetail_price());
        txtNumber.setText("X"+String.valueOf(data.getNumber()));
        numberSelect.addPage(R.layout.layout_number_change);
        numberSelect.addChangeNumber(new NumberSelect.ChangeNumber() {
            @Override
            public void change(int number) {
                //修改本地数据得值
                data.setNumber(number);
                if (updateItem!=null){
                    updateItem.updateItemDate(data);
                }
            }
        });
        checkBox.setTag(data.getId());
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (iItemViewClick!=null){
                    int id = (int) buttonView.getTag();
                    iItemViewClick.itemViewClick(id,isChecked);
                }
            }
        });
    }

    public interface UpdateItem{
        void updateItemDate(CarBean.DataBean.CartListBean data);
    }

}
