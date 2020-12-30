package com.example.xiangmu.interfaces.topic;

import com.example.xiangmu.interfaces.Callback;
import com.example.xiangmu.interfaces.IBaseModel;
import com.example.xiangmu.interfaces.IBasePresenter;
import com.example.xiangmu.interfaces.IBaseView;
import com.example.xiangmu.model.topic.TwoBean;

public interface ITopic {
    interface View extends IBaseView {
        void getTwoReturn(TwoBean twoBean);
        void getTwooReturn(TwoBean twooBean);
    }

    interface Presenter extends IBasePresenter <View> {
        void getTwo();
        void getTwoo();
    }

    interface Model extends IBaseModel {
        void getTwo(Callback callback);
        void getTwoo(Callback callback);

    }
}
