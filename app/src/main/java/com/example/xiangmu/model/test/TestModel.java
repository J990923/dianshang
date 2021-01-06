package com.example.xiangmu.model.test;


import com.example.xiangmu.base.BaseModel;
import com.example.xiangmu.interfaces.Callback;
import com.example.xiangmu.interfaces.test.ITest;
import com.example.xiangmu.net.CommonSubscriber;
import com.example.xiangmu.net.HttpManager;
import com.example.xiangmu.utils.RxUtils;

public class TestModel extends BaseModel implements ITest.Model {

    @Override
    public void getList(Callback callback) {
        addDisposible(HttpManager.getInstance().getService().getList()
        .compose(RxUtils.rxScheduler())
        .subscribeWith(new CommonSubscriber <TestBean>(callback) {
            @Override
            public void onNext(TestBean testBean) {
                callback.success(testBean);
            }
        }));
    }
}
