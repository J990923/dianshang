package com.example.xiangmu.interfaces.me;



import com.example.xiangmu.interfaces.Callback;
import com.example.xiangmu.interfaces.IBaseModel;
import com.example.xiangmu.interfaces.IBasePresenter;
import com.example.xiangmu.interfaces.IBaseView;
import com.example.xiangmu.model.Login.LogoutBean;
import com.example.xiangmu.model.me.UserInfoBean;

import java.util.Map;

public interface IUser {
    interface View extends IBaseView {
        void updateUserInfoReturn(UserInfoBean result);

        void logoutReturn(LogoutBean result);
    }

    interface Presenter extends IBasePresenter <View> {
        void updateUserInfo(Map <String, String> map);
        void logout();
    }


    interface Model extends IBaseModel {
        void updateUserInfo(Map <String, String> map, Callback callback);
        void logout(Callback callback);
    }
}
