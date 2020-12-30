package com.example.xiangmu.presenter.home;

import com.example.xiangmu.base.BasePresenter;
import com.example.xiangmu.interfaces.Callback;
import com.example.xiangmu.interfaces.home.IHotGood;
import com.example.xiangmu.model.home.HotGoodModel;
import com.example.xiangmu.model.home.bean.HotGoodListBean;
import com.example.xiangmu.model.home.bean.NewGoodsBean;

import java.util.HashMap;

public class HotGoodPresenter extends BasePresenter <IHotGood.View> implements IHotGood.Presenter {
    IHotGood.Model model;
    public HotGoodPresenter(){
        model = new HotGoodModel();
    }

    @Override
    public void getHotGood(HashMap<String,String> map) {
        model.getHotGood(map,new Callback <HotGoodListBean>() {
            @Override
            public void success(HotGoodListBean data) {
                if(mView != null){
                    mView.getHotGood(data);
                }
            }

            @Override
            public void fail(String err) {

            }
        });
    }

    @Override
    public void getNewGood() {
            model.getNewGood(new Callback<NewGoodsBean>() {
                @Override
                public void success(NewGoodsBean data) {
                    mView.getNewGood(data);
                }

                @Override
                public void fail(String err) {
                    mView.showToast(err);
                }
            });

    }
}
