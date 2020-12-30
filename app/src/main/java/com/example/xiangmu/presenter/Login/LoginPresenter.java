package com.example.xiangmu.presenter.Login;


import com.example.xiangmu.base.BasePresenter;
import com.example.xiangmu.interfaces.Callback;
import com.example.xiangmu.interfaces.home.ILogin;
import com.example.xiangmu.model.Login.LoginModel;
import com.example.xiangmu.model.home.bean.LoginBean;

public class LoginPresenter extends BasePresenter <ILogin.View> implements ILogin.Presenter {
    ILogin.Model model;
    public LoginPresenter(){
        model = new LoginModel();
    }
    @Override
    public void login(String username,String pw) {
        model.login(username,pw,new Callback <LoginBean>() {
            @Override
            public void success(LoginBean data) {
                if(mView != null){
                    mView.loginReturn(data);
                }
            }

            @Override
            public void fail(String err) {

            }
        });
    }
}
