package com.example.xiangmu.interfaces.home;


import com.example.xiangmu.interfaces.Callback;
import com.example.xiangmu.interfaces.IBaseModel;
import com.example.xiangmu.interfaces.IBasePresenter;
import com.example.xiangmu.interfaces.IBaseView;
import com.example.xiangmu.model.home.bean.BrandBean;
import com.example.xiangmu.model.home.bean.CategoryBean;
import com.example.xiangmu.model.home.bean.GoodsBean;
import com.example.xiangmu.model.home.bean.HomeBean;

public interface IHome {

    interface View extends IBaseView {
        void getHomeReturn(HomeBean result);
        void getCategoryReturn(CategoryBean result);
        void getGoodsReturn(GoodsBean result);
        void getBrandsReturn(BrandBean result);
    }

    interface Presenter extends IBasePresenter <View> {
        void getHome();
        void getCategory(String url);
        void getGoods(int categoryId,int size,int page);
        void getBrands();
    }

    interface Model extends IBaseModel {
        void getHome(Callback callback);
        void getCategory(String url,Callback callback);
        void getGoods(int categoryId,int size,int page,Callback callback);
        void getBrands(Callback callback);
    }

}
