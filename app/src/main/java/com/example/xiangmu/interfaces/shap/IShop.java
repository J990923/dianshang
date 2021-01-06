package com.example.xiangmu.interfaces.shap;


import com.example.xiangmu.interfaces.Callback;
import com.example.xiangmu.interfaces.IBaseModel;
import com.example.xiangmu.interfaces.IBasePresenter;
import com.example.xiangmu.interfaces.IBaseView;
import com.example.xiangmu.model.shap.AddCarBean;
import com.example.xiangmu.model.shap.GoodDetailBean;

import java.util.Map;

public interface IShop {
    interface View extends IBaseView {
        void getGoodDetail(GoodDetailBean goodDetailBean);
        void addGoodCarReturn(AddCarBean addCarBean);
    }

    interface Presenter extends IBasePresenter <View> {
        void getGoodDetail(int id);
        //添加进购物车
        void addGoodCar(Map <String,String> map);
    }


    interface Model extends IBaseModel {
        void getGoodDetail(int id, Callback callback);
       // /添加进购物车
        void addGoodCar(Map<String,String> map,Callback callback);
    }
}
