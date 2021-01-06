package com.example.xiangmu.model.topic;

import com.example.xiangmu.base.BaseModel;
import com.example.xiangmu.interfaces.Callback;

import com.example.xiangmu.interfaces.topic.ISpecialDetails;
import com.example.xiangmu.net.CommonSubscriber;
import com.example.xiangmu.net.HttpManager;
import com.example.xiangmu.ui.topic.bean.SpecialDetailsBean;
import com.example.xiangmu.ui.topic.bean.SpecialDetailsButtomBean;
import com.example.xiangmu.ui.topic.bean.SpecialDetailsCommentBean;
import com.example.xiangmu.utils.RxUtils;

import java.util.Map;

public class SpecialModel extends BaseModel implements ISpecialDetails.Model {
    @Override
    public void getSpecialDetailsComment(Map <String, String> map, Callback callback) {
        addDisposible(HttpManager.getInstance().getShopApi().getSpecialDetailsComment(map)
        .compose(RxUtils.rxScheduler())
        .subscribeWith(new CommonSubscriber <SpecialDetailsCommentBean>(callback) {
            @Override
            public void onNext(SpecialDetailsCommentBean specialDetailsCommentBean) {
                callback.success(specialDetailsCommentBean);
            }
        }));
    }

    @Override
    public void getSpecialDetails(int id, Callback callback) {
        addDisposible(HttpManager.getInstance().getShopApi().getSpecialDetails(id)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber <SpecialDetailsBean>(callback) {
                    @Override
                    public void onNext(SpecialDetailsBean specialDetailsCommentBean) {
                        callback.success(specialDetailsCommentBean);
                    }
                }));
    }

    @Override
    public void getSpecialDetailsButtom(int id, Callback callback) {
        addDisposible(HttpManager.getInstance().getShopApi().getSpecialDetailsButtom(id)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber <SpecialDetailsButtomBean>(callback) {
                    @Override
                    public void onNext(SpecialDetailsButtomBean specialDetailsCommentBean) {
                        callback.success(specialDetailsCommentBean);
                    }
                }));
    }
}
