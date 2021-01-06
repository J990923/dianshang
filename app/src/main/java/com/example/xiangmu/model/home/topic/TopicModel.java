package com.example.xiangmu.model.home.topic;

import com.example.xiangmu.api.GoodsApi;
import com.example.xiangmu.base.BaseModel;
import com.example.xiangmu.interfaces.Callback;
import com.example.xiangmu.interfaces.topic.ITopic;
import com.example.xiangmu.model.topic.TwoBean;
import com.example.xiangmu.net.CommonSubscriber;
import com.example.xiangmu.net.HttpManager;
import com.example.xiangmu.utils.RxUtils;

public class TopicModel extends BaseModel implements ITopic.Model {
    private GoodsApi api;

    public TopicModel() {
        api= HttpManager.getInstance().getGoodsApi();
    }

    @Override
    public void getTwo(Callback callback) {
        addDisposible(api.getTwo().compose(RxUtils.rxScheduler()).subscribeWith(new CommonSubscriber <TwoBean>(callback) {
            @Override
            public void onNext(TwoBean twoBean) {
                callback.success(twoBean);
            }
        }));
    }

    @Override
    public void getTwoo(Callback callback) {
        addDisposible(api.getTwoo().compose(RxUtils.rxScheduler()).subscribeWith(new CommonSubscriber<TwoBean>(callback) {
            @Override
            public void onNext(TwoBean twooBean) {
                callback.success(twooBean);
            }
        }));
    }
}
