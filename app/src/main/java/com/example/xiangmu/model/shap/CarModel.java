package com.example.xiangmu.model.shap;


import com.example.xiangmu.base.BaseModel;
import com.example.xiangmu.interfaces.Callback;
import com.example.xiangmu.interfaces.shap.ICar;
import com.example.xiangmu.net.CommonSubscriber;
import com.example.xiangmu.net.HttpManager;
import com.example.xiangmu.utils.RxUtils;

import java.util.Map;

public class CarModel extends BaseModel implements ICar.Model {
    @Override
    public void getCarList(Callback callback) {
        addDisposible(HttpManager.getInstance().getShopApi().getCarList().
                compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber <CarBean>(callback) {
                    @Override
                    public void onNext(CarBean carBean) {
                        callback.success(carBean);
                    }
                }));
    }
    /**
     * 更新购物车
     * @param map
     * @param callback
     */
    @Override
    public void updateCar(Map <String, String> map, Callback callback) {
        addDisposible(HttpManager.getInstance().getShopApi().updateCar(map).
                compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<UpdateCarBean>(callback) {
                    @Override
                    public void onNext(UpdateCarBean updateCarBean) {
                        callback.success(updateCarBean);
                    }
                }));
    }


    //删除购物车列表
    @Override
    public void deleteCar(String pIds, Callback callback) {
        addDisposible(HttpManager.getInstance().getShopApi().deleteCar(pIds).
                compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<DeleteCarBean>(callback) {
                    @Override
                    public void onNext(DeleteCarBean deleteCarBean) {
                        callback.success(deleteCarBean);
                    }
                }));
    }
}
