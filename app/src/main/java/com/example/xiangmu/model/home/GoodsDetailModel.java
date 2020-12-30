package com.example.xiangmu.model.home;

import com.example.xiangmu.base.BaseModel;
import com.example.xiangmu.interfaces.Callback;
import com.example.xiangmu.interfaces.home.IGoodsDetail;
import com.example.xiangmu.model.home.bean.SPGoodsDetail;
import com.example.xiangmu.model.home.bean.SPGoodsRelated;
import com.example.xiangmu.model.shap.AddCarBean;
import com.example.xiangmu.net.CommonSubscriber;
import com.example.xiangmu.net.HttpManager;
import com.example.xiangmu.utils.RxUtils;

import java.util.Map;

public class GoodsDetailModel extends BaseModel implements IGoodsDetail.Model{

    @Override
    public void getGoodsDetail(int id, Callback callback) {
        addDisposible(
                HttpManager.getInstance().getShopApi().getGoodsDetailData(id)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber <SPGoodsDetail>(callback) {

                    @Override
                    public void onNext(SPGoodsDetail spGoodsDetail) {
                        callback.success(spGoodsDetail);
                    }
                })
        );
    }

    @Override
    public void getGoodsRelated(int id, Callback callback) {
        addDisposible(
                HttpManager.getInstance().getShopApi().getGoodsRelatedData(id)
                        .compose(RxUtils.rxScheduler())
                        .subscribeWith(new CommonSubscriber <SPGoodsRelated>(callback) {

                            @Override
                            public void onNext(SPGoodsRelated spGoodsDetail) {
                                callback.success(spGoodsDetail);
                            }
                        })
        );
    }

    @Override
    public void addGoodCar(Map <String, String> map, Callback callback) {
        addDisposible(HttpManager.getInstance().getShopApi().addCar(map).
                compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<AddCarBean>(callback) {
                    @Override
                    public void onNext(AddCarBean addCarBean) {
                        callback.success(addCarBean);
                    }
                }));
    }
}
