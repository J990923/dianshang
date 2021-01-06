package com.example.xiangmu.interfaces.home;

import com.example.xiangmu.interfaces.Callback;
import com.example.xiangmu.interfaces.IBaseModel;
import com.example.xiangmu.interfaces.IBasePresenter;
import com.example.xiangmu.interfaces.IBaseView;
import com.example.xiangmu.model.home.bean.BrandBean;
import com.example.xiangmu.model.home.bean.BrandGoodsBean;
import com.example.xiangmu.model.home.bean.BrandListDetailsBean;
import com.example.xiangmu.model.home.bean.CategoryBean;
import com.example.xiangmu.model.home.bean.GoodsBean;
import com.example.xiangmu.model.home.bean.HomeBean;

public interface IBrand {
    interface View extends IBaseView {
        void getBrandListDetailsReturn(BrandListDetailsBean result);
        void getBrandGoodsReturn(BrandGoodsBean result);

    }

    interface Presenter extends IBasePresenter <View> {
        void getBrandListDetails(int categoryId);
        void getBrandGoods(int brandId,int size,int page);

    }

    interface Model extends IBaseModel {
        void getBrandListDetails(int categoryId,Callback callback);
        void getBrandGoods(int brandId,int size,int page,Callback callback);

    }
}
