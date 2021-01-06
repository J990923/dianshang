package com.example.xiangmu.model.shap;


import com.example.xiangmu.base.BaseModel;
import com.example.xiangmu.interfaces.Callback;
import com.example.xiangmu.interfaces.shap.IShop;
import com.example.xiangmu.net.CommonSubscriber;
import com.example.xiangmu.net.HttpManager;
import com.example.xiangmu.utils.RxUtils;

import java.util.Map;

public class ShopModel extends BaseModel implements IShop.Model {
    @Override
    public void getGoodDetail(int id, Callback callback) {
        addDisposible(HttpManager.getInstance().getShopApi().getGoodDetail(id).
                compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber <GoodDetailBean>(callback) {
                    @Override
                    public void onNext(GoodDetailBean goodDetailBean) {
                        callback.success(goodDetailBean);
                    }
                }));

    }
    // 添加进购物车
    @Override
    public void addGoodCar(Map<String, String> map, Callback callback) {
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
