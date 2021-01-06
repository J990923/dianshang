package com.example.xiangmu.model.home;

import com.example.xiangmu.base.BaseModel;

import com.example.xiangmu.interfaces.Callback;
import com.example.xiangmu.interfaces.home.ISort;
import com.example.xiangmu.model.home.bean.SortBean;
import com.example.xiangmu.model.home.bean.SortDataBean;
import com.example.xiangmu.net.CommonSubscriber;
import com.example.xiangmu.net.HttpManager;
import com.example.xiangmu.utils.RxUtils;

public class SortModel  extends BaseModel implements ISort.Model{
    @Override
    public void getSort(Callback callback) {
        addDisposible(HttpManager.getInstance().getShopApi().getSort()
        .compose(RxUtils.rxScheduler())
        .subscribeWith(new CommonSubscriber <SortBean>(callback) {
            @Override
            public void onNext(SortBean sortBean) {
                callback.success(sortBean);
            }
        }));
    }

    @Override
    public void getSortData(int id, Callback callback) {
        addDisposible(HttpManager.getInstance().getShopApi().getSortData(id)
        .compose(RxUtils.rxScheduler())
        .subscribeWith(new CommonSubscriber <SortDataBean>(callback) {
            @Override
            public void onNext(SortDataBean sortDataBean) {
                callback.success(sortDataBean);
            }
        }));
        }
}
