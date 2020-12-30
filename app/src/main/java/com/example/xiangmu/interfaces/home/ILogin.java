package com.example.xiangmu.interfaces.home;

import com.example.xiangmu.interfaces.Callback;
import com.example.xiangmu.interfaces.IBaseModel;
import com.example.xiangmu.interfaces.IBasePresenter;
import com.example.xiangmu.interfaces.IBaseView;
import com.example.xiangmu.model.home.bean.LoginBean;

public interface ILogin {
    interface View extends IBaseView {
        void loginReturn(LoginBean loginBean);
    }

    interface Presenter extends IBasePresenter <View> {
        void login(String username,String pw);
    }


    interface Model extends IBaseModel {
        void login(String username,String pw, Callback callback);
    }
}
