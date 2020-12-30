package com.example.xiangmu.interfaces.home;


import com.example.xiangmu.interfaces.Callback;
import com.example.xiangmu.interfaces.IBaseModel;
import com.example.xiangmu.interfaces.IBasePresenter;
import com.example.xiangmu.interfaces.IBaseView;
import com.example.xiangmu.model.home.bean.HotGoodListBean;
import com.example.xiangmu.model.home.bean.NewGoodsBean;

import java.util.HashMap;

public interface IHotGood {
    interface View extends IBaseView {
        void getHotGood(HotGoodListBean result);
        void getNewGood(NewGoodsBean result);
    }

    interface Presenter extends IBasePresenter <View> {
        void getHotGood(HashMap <String, String> map);
        void getNewGood();
    }

    interface Model extends IBaseModel {
        void getHotGood(HashMap <String, String> map, Callback callback);
        void getNewGood(Callback <NewGoodsBean> callback);
    }
}
