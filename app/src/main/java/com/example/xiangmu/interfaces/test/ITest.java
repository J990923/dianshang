package com.example.xiangmu.interfaces.test;


import com.example.xiangmu.interfaces.Callback;
import com.example.xiangmu.interfaces.IBaseModel;
import com.example.xiangmu.interfaces.IBasePresenter;
import com.example.xiangmu.interfaces.IBaseView;
import com.example.xiangmu.model.test.TestBean;

public interface ITest {

    interface View extends IBaseView {
        void getListReturn(TestBean testBean);
    }

    interface Presenter extends IBasePresenter <View> {
        void getList();
    }


    interface Model extends IBaseModel {
        void getList(Callback callback);
    }

}
