package com.example.xiangmu.model.home;


import com.example.xiangmu.base.BaseModel;
import com.example.xiangmu.interfaces.Callback;
import com.example.xiangmu.interfaces.home.IHotGood;
import com.example.xiangmu.model.home.bean.HotGoodListBean;
import com.example.xiangmu.model.home.bean.NewGoodsBean;
import com.example.xiangmu.net.CommonSubscriber;
import com.example.xiangmu.net.HttpManager;
import com.example.xiangmu.utils.RxUtils;

import java.util.HashMap;

public class HotGoodModel extends BaseModel implements IHotGood.Model {
    @Override
    public void getHotGood(HashMap<String, String> map, Callback callback) {
        addDisposible(HttpManager.getInstance().getBrandApi().getHotGoodList(map)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber <HotGoodListBean>(callback) {
                    @Override
                    public void onNext(HotGoodListBean hotGoodListBean) {
                        callback.success(hotGoodListBean);
                    }
                }));
    }

    @Override
    public void getNewGood(Callback callback) {
        addDisposible(HttpManager.getInstance().getBrandApi().getNewGoods()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<NewGoodsBean>(callback) {
                    @Override
                    public void onNext(NewGoodsBean newGoodsBean) {
                        callback.success(newGoodsBean);
                    }
                }));
    }
}
