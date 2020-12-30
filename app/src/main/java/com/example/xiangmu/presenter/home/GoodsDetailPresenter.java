package com.example.xiangmu.presenter.home;

import com.example.xiangmu.base.BasePresenter;

import com.example.xiangmu.interfaces.Callback;
import com.example.xiangmu.interfaces.home.IBrand;
import com.example.xiangmu.interfaces.home.IGoodsDetail;
import com.example.xiangmu.model.home.BrandModel;
import com.example.xiangmu.model.home.GoodsDetailModel;
import com.example.xiangmu.model.home.bean.BrandListDetailsBean;
import com.example.xiangmu.model.home.bean.SPGoodsDetail;
import com.example.xiangmu.model.home.bean.SPGoodsRelated;
import com.example.xiangmu.model.shap.AddCarBean;

import java.util.Map;

public class GoodsDetailPresenter extends BasePresenter <IGoodsDetail.View> implements IGoodsDetail.Presenter  {
    IGoodsDetail.Model model;

    public GoodsDetailPresenter(IGoodsDetail.View view) {
        model = new GoodsDetailModel();
    }

    @Override
    public void getGoodsDetail(int id) {
        model.getGoodsDetail(id, new Callback <SPGoodsDetail>(){

            @Override
            public void success(SPGoodsDetail data) {
                mView.getGoodsDetailReturn(data);
            }

            @Override
            public void fail(String err) {

            }
        });
    }

    @Override
    public void getGoodsRelated(int id) {
        model.getGoodsRelated(id, new Callback <SPGoodsRelated>(){

            @Override
            public void success(SPGoodsRelated data) {
                if(data!=null){

                    mView.getGoodsRelatedReturn(data);
                }
            }

            @Override
            public void fail(String err) {

            }
        });
    }
    public void addGoodCar(Map <String, String> map) {
        model.addGoodCar(map, new Callback<AddCarBean>() {
            @Override
            public void success(AddCarBean data) {
                if(mView != null){
                    mView.addGoodCarReturn(data);
                }
            }

            @Override
            public void fail(String err) {

            }
        });
    }
}
