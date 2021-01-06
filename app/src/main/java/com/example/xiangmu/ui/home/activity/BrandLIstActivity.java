package com.example.xiangmu.ui.home.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.xiangmu.R;
import com.example.xiangmu.app.MyApp;
import com.example.xiangmu.base.BaseActivity;
import com.example.xiangmu.base.BaseAdapter;
import com.example.xiangmu.interfaces.IBasePresenter;
import com.example.xiangmu.model.home.bean.BrandBean;
import com.example.xiangmu.ui.home.adapter.BrandListAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BrandLIstActivity extends BaseActivity {


    @BindView(R.id.recycler)
    RecyclerView recycler;
    private BrandListAdapter brandListAdapter;
    private List <BrandBean.DataBeanX.DataBean> data;

    @Override
    protected int getLayout() {
        return R.layout.activity_brandlist;
    }

    @Override
    protected IBasePresenter createPrenter() {
        return null;
    }

    @Override
    protected void initView() {
        data = (List <BrandBean.DataBeanX.DataBean>) MyApp.getMap().get("name");
        recycler.setLayoutManager(new LinearLayoutManager(this));
        brandListAdapter = new BrandListAdapter(this, data);
        recycler.setAdapter(brandListAdapter);

    }

    @Override
    protected void initData() {
        brandListAdapter.addListClick(new BaseAdapter.IListClick() {
            @Override
            public void itemClick(int pos) {
                Intent intent = new Intent(BrandLIstActivity.this, BrandListDetailsActivity.class);
                int id = data.get(pos).getId();
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
