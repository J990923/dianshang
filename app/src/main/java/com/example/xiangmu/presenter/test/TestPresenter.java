package com.example.xiangmu.presenter.test;


import com.example.xiangmu.base.BasePresenter;
import com.example.xiangmu.interfaces.Callback;
import com.example.xiangmu.interfaces.test.ITest;
import com.example.xiangmu.model.test.TestBean;
import com.example.xiangmu.model.test.TestModel;

public class TestPresenter extends BasePresenter <ITest.View> implements ITest.Presenter {

    ITest.Model model;

    public TestPresenter(ITest.View mView){
        model = new TestModel();
    }

    @Override
    public void getList() {
        if(mView != null){
            model.getList(new Callback <TestBean>() {
                @Override
                public void success(TestBean data) {
                    mView.getListReturn(data);
                }

                @Override
                public void fail(String err) {
                    mView.showToast(err);
                }
            });
        }
    }
}
