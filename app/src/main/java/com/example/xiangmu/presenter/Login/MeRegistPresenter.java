package com.example.xiangmu.presenter.Login;

import com.example.xiangmu.base.BasePresenter;
import com.example.xiangmu.interfaces.Callback;
import com.example.xiangmu.interfaces.Login.IMeRegist;
import com.example.xiangmu.interfaces.home.ILogin;
import com.example.xiangmu.model.Login.LoginModel;
import com.example.xiangmu.model.Login.MeRegisterBean;
import com.example.xiangmu.model.Login.MeRegisterModel;
import com.example.xiangmu.model.home.bean.LoginBean;

public class MeRegistPresenter extends BasePresenter <IMeRegist.View> implements IMeRegist.Presenter{
    IMeRegist.Model model;
    public MeRegistPresenter(IMeRegist.View view){
        model = new MeRegisterModel();
    }


    @Override
    public void login(String username, String password) {
        model.login(username, password, new Callback<MeRegisterBean>() {
            @Override
            public void success(MeRegisterBean data) {
                mView.loginReturn(data);
            }

            @Override
            public void fail(String err) {

            }
        });
    }
}
