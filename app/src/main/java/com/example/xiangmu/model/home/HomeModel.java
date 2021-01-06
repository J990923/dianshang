package com.example.xiangmu.model.home;


import com.example.xiangmu.base.BaseModel;
import com.example.xiangmu.interfaces.Callback;
import com.example.xiangmu.interfaces.home.IHome;
import com.example.xiangmu.model.home.bean.BrandBean;
import com.example.xiangmu.model.home.bean.CategoryBean;
import com.example.xiangmu.model.home.bean.GoodsBean;
import com.example.xiangmu.model.home.bean.HomeBean;
import com.example.xiangmu.net.CommonSubscriber;
import com.example.xiangmu.net.HttpManager;
import com.example.xiangmu.utils.RxUtils;

public class HomeModel extends BaseModel implements IHome.Model {


    @Override
    public void getHome(Callback callback) {
        addDisposible(HttpManager.getInstance().getShopApi().getHome()
        .compose(RxUtils.rxScheduler())
        .subscribeWith(new CommonSubscriber <HomeBean>(callback) {
            @Override
            public void onNext(HomeBean homeBean) {
                callback.success(homeBean);
            }
        }));
    }

    @Override
    public void getCategory(String url, Callback callback) {
        addDisposible(HttpManager.getInstance().getShopApi().getCategory(url)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber <CategoryBean>(callback) {
                    @Override
                    public void onNext(CategoryBean categoryBean) {
                        callback.success(categoryBean);
                    }
                }));
    }

    @Override
    public void getGoods(int categoryId, int size, int page, Callback callback) {
        addDisposible(HttpManager.getInstance().getShopApi().getGoos(categoryId,size,page)
        .compose(RxUtils.rxScheduler())
        .subscribeWith(new CommonSubscriber <GoodsBean>(callback) {
            @Override
            public void onNext(GoodsBean goodsBean) {
                callback.success(goodsBean);
            }
        }));
    }

    @Override
    public void getBrands(Callback callback) {
        addDisposible(HttpManager.getInstance().getShopApi().getBrand()
        .compose(RxUtils.rxScheduler())
        .subscribeWith(new CommonSubscriber <BrandBean>(callback) {
            @Override
            public void onNext(BrandBean brandBean) {
                callback.success(brandBean);
            }
        }));
    }


}
