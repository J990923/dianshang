package com.example.xiangmu.model.Login;


import com.example.xiangmu.base.BaseModel;
import com.example.xiangmu.interfaces.Callback;
import com.example.xiangmu.interfaces.home.ILogin;
import com.example.xiangmu.model.home.bean.LoginBean;
import com.example.xiangmu.net.CommonSubscriber;
import com.example.xiangmu.net.HttpManager;
import com.example.xiangmu.utils.RxUtils;

public class LoginModel extends BaseModel implements ILogin.Model {
    @Override
    public void login(String username,String pw, Callback callback) {
        addDisposible(HttpManager.getInstance().getShopApi().login(username,pw).
                compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber <LoginBean>(callback) {
                    @Override
                    public void onNext(LoginBean loginBean) {
                        callback.success(loginBean);
                    }
                }));
    }
}
