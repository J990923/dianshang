package com.example.xiangmu.presenter.tpoic;

import com.example.xiangmu.base.BasePresenter;
import com.example.xiangmu.interfaces.Callback;
import com.example.xiangmu.interfaces.topic.ISpecialDetails;
import com.example.xiangmu.model.topic.SpecialModel;
import com.example.xiangmu.ui.topic.bean.SpecialDetailsBean;
import com.example.xiangmu.ui.topic.bean.SpecialDetailsButtomBean;
import com.example.xiangmu.ui.topic.bean.SpecialDetailsCommentBean;

import java.util.Map;


public class SpecialDetails extends BasePresenter <ISpecialDetails.View> implements ISpecialDetails.Presenter {
    ISpecialDetails.Model model;
    ISpecialDetails.View view;

    public SpecialDetails(ISpecialDetails.View view) {
        this.view = view;
        model=new SpecialModel();
    }

    @Override
    public void getSpecialDetailsComment(Map <String, String> map) {
        model.getSpecialDetailsComment(map, new Callback<SpecialDetailsCommentBean>() {
            @Override
            public void success(SpecialDetailsCommentBean data) {
                if (data!=null){

                    view.getSpecialDetailsCommentReturn(data);
                }
            }

            @Override
            public void fail(String err) {

            }
        });
    }

    @Override
    public void getSpecialDetails(int id) {
        model.getSpecialDetails(id, new Callback<SpecialDetailsBean>() {
            @Override
            public void success(SpecialDetailsBean data) {
                if (data!=null){
                    view.getSpecialDetailsReturn(data);
                }

            }

            @Override
            public void fail(String err) {

            }
        });
    }

    @Override
    public void getSpecialDetailsButtom(int id) {
        model.getSpecialDetailsButtom(id, new Callback<SpecialDetailsButtomBean>() {
            @Override
            public void success(SpecialDetailsButtomBean data) {
                if (data!=null){
                    view.getSpecialDetailsButtomReturn(data);
                }

            }

            @Override
            public void fail(String err) {

            }
        });
    }
}
