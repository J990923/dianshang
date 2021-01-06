package com.example.xiangmu.model.home;

import com.example.xiangmu.base.BaseModel;
import com.example.xiangmu.interfaces.Callback;
import com.example.xiangmu.interfaces.home.IBrand;
import com.example.xiangmu.model.home.bean.BrandGoodsBean;
import com.example.xiangmu.model.home.bean.BrandListDetailsBean;
import com.example.xiangmu.net.CommonSubscriber;
import com.example.xiangmu.net.HttpManager;
import com.example.xiangmu.utils.RxUtils;

import org.greenrobot.greendao.annotation.Id;


public class BrandModel extends BaseModel implements IBrand.Model {

    @Override
    public void getBrandListDetails(int categoryId, Callback callback) {
        addDisposible(
                HttpManager.getInstance().getBrandApi().getBrand(categoryId)
                        .compose(RxUtils.rxScheduler())
                        .subscribeWith(new CommonSubscriber <BrandListDetailsBean>(callback) {
                    @Override
                    public void onNext(BrandListDetailsBean brandListDetailsBean) {
                    callback.success(brandListDetailsBean);
                    }
                })
        );
    }

    @Override
    public void getBrandGoods(int brandId, int size, int page, Callback callback) {
        addDisposible(HttpManager.getInstance().getBrandApi().getGoos(brandId, page, size)
        .compose(RxUtils.rxScheduler())
        .subscribeWith(new CommonSubscriber <BrandGoodsBean>(callback) {
            @Override
            public void onNext(BrandGoodsBean brandGoodsBean) {
                callback.success(brandGoodsBean);
            }
        }));
    }
}
