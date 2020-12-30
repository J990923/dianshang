package com.example.xiangmu.ui.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.xiangmu.R;
import com.example.xiangmu.base.BaseActivity;
import com.example.xiangmu.interfaces.home.IBrand;
import com.example.xiangmu.model.home.bean.BrandGoodsBean;
import com.example.xiangmu.model.home.bean.BrandListDetailsBean;
import com.example.xiangmu.presenter.home.BrandPresenter;
import com.example.xiangmu.ui.home.adapter.BrandGoodsAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BrandListDetailsActivity extends BaseActivity <IBrand.Presenter> implements IBrand.View {

    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_desc)
    TextView tvDesc;
    @BindView(R.id.recyler)
    RecyclerView recyler;

    private int id;

    @Override
    protected int getLayout() {
        return R.layout.activity_brandlistdetails;
    }

    @Override
    protected IBrand.Presenter createPrenter() {
        return new BrandPresenter(this);
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
    }

    @Override
    protected void initData() {
        presenter.getBrandListDetails(id); //初始化加载数据
        presenter.getBrandGoods(id,1000,1); //初始化加载数据

    }

    @Override
    public void getBrandListDetailsReturn(BrandListDetailsBean result) {
        Log.i(TAG, "getBrandListDetailsReturn: " + result.getData().getBrand().getName());
        initBrand(result.getData().getBrand());
    }

    @Override
    public void getBrandGoodsReturn(BrandGoodsBean result) {

        initBrandGoods(result.getData().getData());
    }

    private void initBrandGoods(List <BrandGoodsBean.DataBeanX.DataBean> data) {
        //
        recyler.setLayoutManager(new GridLayoutManager(this,2));
        BrandGoodsAdapter brandGoodsAdapter = new BrandGoodsAdapter(this,data);
        recyler.setAdapter(brandGoodsAdapter);
    }

    private void initBrand(BrandListDetailsBean.DataBean.BrandBean brand) {


        Glide.with(this).load(brand.getApp_list_pic_url()).into(image);
        tvName.setText(brand.getName());
        tvDesc.setText(brand.getSimple_desc());
    }

    private static final String TAG = "BrandListDetailsActivit";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
