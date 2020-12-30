package com.example.xiangmu.presenter.home;

import com.example.xiangmu.base.BasePresenter;
import com.example.xiangmu.interfaces.Callback;
import com.example.xiangmu.interfaces.home.IBrand;

import com.example.xiangmu.model.home.BrandModel;
import com.example.xiangmu.model.home.bean.BrandGoodsBean;
import com.example.xiangmu.model.home.bean.BrandListDetailsBean;
import com.example.xiangmu.model.home.bean.HomeBean;


public class BrandPresenter extends BasePresenter <IBrand.View> implements IBrand.Presenter {
    IBrand.Model model;
    public BrandPresenter(IBrand.View view){
        model = new BrandModel();
    }

    @Override
    public void getBrandListDetails(int categoryId) {
        model.getBrandListDetails(categoryId, new Callback<BrandListDetailsBean>(){

            @Override
            public void success(BrandListDetailsBean data) {
                mView.getBrandListDetailsReturn(data);
            }

            @Override
            public void fail(String err) {

            }
        });
    }

    @Override
    public void getBrandGoods(int brandId, int size, int page) {
        model.getBrandGoods(brandId, size, page, new Callback<BrandGoodsBean>() {


            @Override
            public void success(BrandGoodsBean data) {
                if(data!=null){

                    mView.getBrandGoodsReturn(data);
                }
            }

            @Override
            public void fail(String err) {

            }
        });
    }
}
