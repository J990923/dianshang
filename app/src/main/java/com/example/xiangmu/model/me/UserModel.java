package com.example.xiangmu.model.me;

import android.util.Log;


import com.example.xiangmu.base.BaseModel;
import com.example.xiangmu.interfaces.Callback;
import com.example.xiangmu.interfaces.me.IUser;
import com.example.xiangmu.model.Login.LogoutBean;
import com.example.xiangmu.net.CommonSubscriber;
import com.example.xiangmu.net.HttpManager;
import com.example.xiangmu.utils.RxUtils;

import java.util.Map;

public class UserModel extends BaseModel implements IUser.Model {
    @Override
    public void updateUserInfo(Map<String, String> map, Callback callback) {
        addDisposible(HttpManager.getInstance().getShopApi().updateUserInfo(map).
                compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber <UserInfoBean>(callback) {
                    @Override
                    public void onNext(UserInfoBean userInfoBean) {
                        callback.success(userInfoBean);
                    }
                }));
    }

    @Override
    public void logout(Callback callback) {
        Log.i("TAG","logout model");
        addDisposible(HttpManager.getInstance().getShopApi().logout().
                compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<LogoutBean>(callback) {
                    @Override
                    public void onNext(LogoutBean logoutBean) {
                        Log.i("TAG","logout onNext");
                        callback.success(logoutBean);
                    }
                }));
    }
}
