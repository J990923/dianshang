package com.example.xiangmu.interfaces.home;

import com.example.xiangmu.interfaces.Callback;
import com.example.xiangmu.interfaces.IBaseModel;
import com.example.xiangmu.interfaces.IBasePresenter;
import com.example.xiangmu.interfaces.IBaseView;
import com.example.xiangmu.model.home.bean.BrandBean;
import com.example.xiangmu.model.home.bean.CategoryBean;
import com.example.xiangmu.model.home.bean.GoodsBean;
import com.example.xiangmu.model.home.bean.HomeBean;
import com.example.xiangmu.model.home.bean.SPGoodsDetail;
import com.example.xiangmu.model.home.bean.SPGoodsRelated;
import com.example.xiangmu.model.shap.AddCarBean;

import java.util.Map;

public interface IGoodsDetail {
    interface View extends IBaseView {
        void getGoodsDetailReturn(SPGoodsDetail result);
        void getGoodsRelatedReturn(SPGoodsRelated result);
        void addGoodCarReturn(AddCarBean addCarBean);
    }

    interface Presenter extends IBasePresenter <View> {
        void getGoodsDetail(int  id);
        void getGoodsRelated(int  id);
        //添加进购物车
        void addGoodCar(Map <String,String> map);
    }

    interface Model extends IBaseModel {
        void getGoodsDetail(int  id,Callback callback);
        void getGoodsRelated(int  id,Callback callback);

        //添加进购物车
        void addGoodCar(Map<String,String> map,Callback callback);
    }
}
