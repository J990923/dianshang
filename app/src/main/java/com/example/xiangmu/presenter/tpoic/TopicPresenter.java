package com.example.xiangmu.presenter.tpoic;


import com.example.xiangmu.base.BasePresenter;
import com.example.xiangmu.interfaces.Callback;
import com.example.xiangmu.interfaces.topic.ITopic;
import com.example.xiangmu.model.home.topic.TopicModel;
import com.example.xiangmu.model.topic.TwoBean;

public class TopicPresenter extends BasePresenter <ITopic.View> implements ITopic.Presenter{
    ITopic.Model model;
    ITopic.View view;

    public TopicPresenter(ITopic.View view) {
        this.view = view;
        model=new TopicModel();
    }


    @Override
    public void getTwo() {
        if (view!=null){
            model.getTwo(new Callback() {
                @Override
                public void success(Object data) {
                    view.getTwoReturn((TwoBean) data);
                }

                @Override
                public void fail(String err) {
                    view.showToast(err);
                }
            });
        }
    }

    @Override
    public void getTwoo() {
        if (view!=null){
            model.getTwoo(new Callback() {
                @Override
                public void success(Object data) {
                    view.getTwooReturn((TwoBean) data);
                }

                @Override
                public void fail(String err) {
                    view.showToast(err);
                }
            });
        }
    }
}
