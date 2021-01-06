package com.example.xiangmu.presenter.shop;


import com.example.xiangmu.base.BasePresenter;
import com.example.xiangmu.interfaces.Callback;
import com.example.xiangmu.interfaces.shap.IShop;
import com.example.xiangmu.model.shap.AddCarBean;
import com.example.xiangmu.model.shap.GoodDetailBean;
import com.example.xiangmu.model.shap.ShopModel;

import java.util.Map;

public class ShopPresenter extends BasePresenter <IShop.View> implements IShop.Presenter {
    IShop.Model model;
    public ShopPresenter(){
        model = new ShopModel();
    }
    @Override
    public void getGoodDetail(int id) {
        model.getGoodDetail(id,new Callback <GoodDetailBean>() {
            @Override
            public void success(GoodDetailBean data) {
                if(mView != null){
                    mView.getGoodDetail(data);
                }
            }

            @Override
            public void fail(String err) {

            }
        });
    }
    // 添加到购物车
    @Override
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
