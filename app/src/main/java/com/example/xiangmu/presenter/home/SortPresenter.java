package com.example.xiangmu.presenter.home;

import com.example.xiangmu.base.BasePresenter;

import com.example.xiangmu.interfaces.Callback;
import com.example.xiangmu.interfaces.home.IHome;
import com.example.xiangmu.interfaces.home.ISort;
import com.example.xiangmu.model.home.HomeModel;
import com.example.xiangmu.model.home.SortModel;
import com.example.xiangmu.model.home.bean.SortBean;
import com.example.xiangmu.model.home.bean.SortDataBean;

public class SortPresenter extends BasePresenter <ISort.View> implements ISort.Presenter  {
    ISort.Model model;
    public SortPresenter(ISort.View view){
        model = new SortModel();
    }
    @Override
    public void getSort() {
        model.getSort(new Callback<SortBean>() {
            @Override
            public void success(SortBean data) {
                mView.getSortReturn(data);
            }

            @Override
            public void fail(String err) {

            }
        });
    }

    @Override
    public void getSortData(int id) {
        model.getSortData(id, new Callback<SortDataBean>() {
            @Override
            public void success(SortDataBean data) {
                    mView.getSortDataReturn(data);
            }

            @Override
            public void fail(String err) {

            }
        });
    }
}
