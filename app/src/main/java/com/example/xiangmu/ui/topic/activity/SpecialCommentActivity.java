package com.example.xiangmu.ui.topic.activity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.xiangmu.R;
import com.example.xiangmu.app.MyApp;
import com.example.xiangmu.base.BaseActivity;
import com.example.xiangmu.interfaces.topic.ISpecialDetails;
import com.example.xiangmu.presenter.tpoic.SpecialDetails;
import com.example.xiangmu.ui.topic.bean.SpecialDetailsBean;
import com.example.xiangmu.ui.topic.bean.SpecialDetailsButtomBean;
import com.example.xiangmu.ui.topic.adapter.SpecialDetailsCommentAdapter;
import com.example.xiangmu.ui.topic.bean.SpecialDetailsCommentBean;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

public class SpecialCommentActivity extends BaseActivity <ISpecialDetails.Presenter> implements ISpecialDetails.View {

    @BindView(R.id.mRlv_special_comment)
    RecyclerView mRlv;
    private int id;

    @Override
    protected int getLayout() {
        return R.layout.activity_special_comment;
    }

    @Override
    protected ISpecialDetails.Presenter createPrenter() {
        return new SpecialDetails(this);
    }

    @Override
    protected void initView() {
    }


    @Override
    protected void initData() {
        id = (int) MyApp.getMap().get("specialId");

        presenter.getSpecialDetailsComment(getMap());
    }

    private HashMap<String, String> getMap() {
        HashMap<String, String> map = new HashMap<>();
        map.put("valueId",String.valueOf(id));
        map.put("typeId",String.valueOf(1));
        map.put("size","");
        return map;
    }

    @Override
    public void getSpecialDetailsReturn(SpecialDetailsBean result) {

    }

    @Override
    public void getSpecialDetailsCommentReturn(SpecialDetailsCommentBean result) {
        List<SpecialDetailsCommentBean.DataBeanX.DataBean> data = result.getData().getData();
        mRlv.setLayoutManager(new LinearLayoutManager(this));
        SpecialDetailsCommentAdapter specialDetailsCommentAdapter = new SpecialDetailsCommentAdapter(this, data);
        mRlv.setAdapter(specialDetailsCommentAdapter);
        specialDetailsCommentAdapter.notifyDataSetChanged();
    }

    @Override
    public void getSpecialDetailsButtomReturn(SpecialDetailsButtomBean result) {

    }
}