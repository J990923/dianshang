package com.example.xiangmu.interfaces.home;

import com.example.xiangmu.interfaces.Callback;
import com.example.xiangmu.interfaces.IBaseModel;
import com.example.xiangmu.interfaces.IBasePresenter;
import com.example.xiangmu.interfaces.IBaseView;
import com.example.xiangmu.model.home.bean.SPGoodsDetail;
import com.example.xiangmu.model.home.bean.SPGoodsRelated;
import com.example.xiangmu.model.home.bean.SortBean;
import com.example.xiangmu.model.home.bean.SortDataBean;

public interface ISort {
    interface View extends IBaseView {
        void getSortReturn(SortBean result);
        void getSortDataReturn(SortDataBean result);

    }

    interface Presenter extends IBasePresenter <View> {
        void getSort();
        void getSortData(int  id);

    }

    interface Model extends IBaseModel {
        void getSort(Callback callback);
        void getSortData(int  id,Callback callback);

    }
}
