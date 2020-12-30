package com.example.xiangmu.ui.test;

import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.xiangmu.R;
import com.example.xiangmu.base.BaseActivity;
import com.example.xiangmu.interfaces.test.ITest;
import com.example.xiangmu.model.test.TestBean;
import com.example.xiangmu.presenter.test.TestPresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class TestActivity extends BaseActivity <ITest.Presenter> implements ITest.View {

    @BindView(R.id.recy)
    RecyclerView recyclerView;
    @BindView(R.id.btn_submit)
    Button btnSubmit;

    private List<TestBean.DataBean> myList;
    private MyAdapter myAdapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_test;
    }

    @Override
    protected TestPresenter createPrenter() {
        return new TestPresenter(this);
    }

    @Override
    protected void initView() {
        presenter.getList();
    }

    @Override
    protected void initData() {
        myList = new ArrayList<>();
        myAdapter = new MyAdapter(this,myList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter);
        presenter.getList();
    }

    @OnClick
    protected void onClick(View view){
        switch (view.getId()){
            case R.id.btn_submit:
                getChangeData();
                changeDataByMap();
                break;
        }
    }

    @Override
    public void getListReturn(TestBean result) {
        myList.addAll(result.getData());
        myAdapter.notifyDataSetChanged();
    }

    private List<TestBean.DataBean> getChangeData(){
        List<TestBean.DataBean> list = new ArrayList<>();

        for(TestBean.DataBean item:myList){
            if(item.isSelect() != item.currentSelect){
                list.add(item);
            }
        }
        return list;
    }

    private void changeDataByMap(){
        HashMap<String, TestBean.DataBean> map = myAdapter.getMap();
        map.keySet();
    }
}
