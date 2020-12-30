package com.example.xiangmu.interfaces.Login;

import com.example.xiangmu.interfaces.Callback;
import com.example.xiangmu.interfaces.IBaseModel;
import com.example.xiangmu.interfaces.IBasePresenter;
import com.example.xiangmu.interfaces.IBaseView;
import com.example.xiangmu.interfaces.home.ILogin;
import com.example.xiangmu.model.Login.MeRegisterBean;
import com.example.xiangmu.model.home.bean.LoginBean;

public interface IMeRegist {
    interface View extends IBaseView {
        void loginReturn(MeRegisterBean meRegisterBean);
    }

    interface Presenter extends IBasePresenter <View> {
        void login(String username,String password);
    }


    interface Model extends IBaseModel {
        void login(String username,String password,Callback callback);
    }
}
