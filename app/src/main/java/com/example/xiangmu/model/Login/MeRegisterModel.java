package com.example.xiangmu.model.Login;

import com.example.xiangmu.base.BaseModel;
import com.example.xiangmu.interfaces.Callback;
import com.example.xiangmu.interfaces.Login.IMeRegist;
import com.example.xiangmu.model.home.bean.LoginBean;
import com.example.xiangmu.net.CommonSubscriber;
import com.example.xiangmu.net.HttpManager;
import com.example.xiangmu.utils.RxUtils;


public class MeRegisterModel extends BaseModel implements IMeRegist.Model{


    @Override
    public void login(String username, String password, Callback callback) {
        addDisposible(HttpManager.getInstance().getShopApi().getMeRegist(username, password)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber <MeRegisterBean>(callback) {
                    @Override
                    public void onNext(MeRegisterBean loginBean) {
                        callback.success(loginBean);
                    }
                }));
    }
}
