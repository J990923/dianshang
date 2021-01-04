package com.example.xiangmu.presenter.me;

import android.util.Log;


import com.example.xiangmu.base.BasePresenter;
import com.example.xiangmu.interfaces.Callback;
import com.example.xiangmu.interfaces.me.IUser;
import com.example.xiangmu.model.Login.LogoutBean;
import com.example.xiangmu.model.me.UserInfoBean;
import com.example.xiangmu.model.me.UserModel;

import java.util.Map;

public class UserPresenter extends BasePresenter <IUser.View> implements IUser.Presenter {

    IUser.Model model;

    public UserPresenter(){
        model = new UserModel();
    }

    @Override
    public void updateUserInfo(Map<String, String> map) {
        model.updateUserInfo(map,new Callback <UserInfoBean>() {
            @Override
            public void success(UserInfoBean data) {
                if(mView != null){
                    mView.updateUserInfoReturn(data);
                }
            }

            @Override
            public void fail(String err) {

            }
        });
    }

    @Override
    public void logout() {
        Log.i("TAG","logout 调用");
        model.logout(new Callback<LogoutBean>() {
            @Override
            public void success(LogoutBean data) {
                Log.i("TAG","login    out");
                if(mView != null){
                    mView.logoutReturn(data);
                }
            }

            @Override
            public void fail(String err) {

            }
        });
    }

}
