package com.example.xiangmu.interfaces.topic;

import com.example.xiangmu.interfaces.Callback;
import com.example.xiangmu.interfaces.IBaseModel;
import com.example.xiangmu.interfaces.IBasePresenter;
import com.example.xiangmu.interfaces.IBaseView;
import com.example.xiangmu.ui.topic.bean.SpecialDetailsBean;
import com.example.xiangmu.ui.topic.bean.SpecialDetailsButtomBean;
import com.example.xiangmu.ui.topic.bean.SpecialDetailsCommentBean;

import java.util.Map;

public interface ISpecialDetails {
    interface View extends IBaseView {
        //专题评论
        void getSpecialDetailsCommentReturn(SpecialDetailsCommentBean specialDetailsCommentBean);
        //专题详情数据
        void getSpecialDetailsReturn(SpecialDetailsBean specialDetailsBean);
        //专题底部列表
        void getSpecialDetailsButtomReturn(SpecialDetailsButtomBean specialDetailsButtomBean);
    }

    interface Presenter extends IBasePresenter <View> {
        void getSpecialDetailsComment(Map <String,String> map);
        void getSpecialDetails(int  id);
        void getSpecialDetailsButtom(int id);
    }

    interface Model extends IBaseModel {
        void getSpecialDetailsComment(Map <String,String> map,Callback callback);
        void getSpecialDetails(int id,Callback callback);
        void getSpecialDetailsButtom(int id,Callback callback);

    }
}
