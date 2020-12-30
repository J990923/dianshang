package com.example.xiangmu.presenter.home;


import com.example.xiangmu.base.BasePresenter;
import com.example.xiangmu.interfaces.Callback;
import com.example.xiangmu.interfaces.home.IHome;
import com.example.xiangmu.model.home.bean.BrandBean;
import com.example.xiangmu.model.home.bean.CategoryBean;
import com.example.xiangmu.model.home.bean.GoodsBean;
import com.example.xiangmu.model.home.bean.HomeBean;
import com.example.xiangmu.model.home.HomeModel;

public class HomePresenter extends BasePresenter <IHome.View> implements IHome.Presenter {

    IHome.Model model;
    public HomePresenter(IHome.View view){
        model = new HomeModel();
    }

    @Override
    public void getHome() {
        model.getHome(new Callback <HomeBean>() {
            @Override
            public void success(HomeBean data) {
                if(mView != null){
                    mView.getHomeReturn(data);
                }
            }

            @Override
            public void fail(String err) {

            }
        });
    }

    @Override
    public void getCategory(String url) {
        model.getCategory(url, new Callback<CategoryBean>() {

            @Override
            public void success(CategoryBean data) {
                mView.getCategoryReturn(data);
            }

            @Override
            public void fail(String err) {

            }
        });
    }

    @Override
    public void getGoods(int categoryId, int size, int page) {
        model.getGoods(categoryId, size, page, new Callback<GoodsBean>() {
            @Override
            public void success(GoodsBean data) {
                if (data!=null){

                    mView.getGoodsReturn(data);
                }
            }

            @Override
            public void fail(String err) {

            }
        });
    }

    @Override
    public void getBrands() {
        model.getBrands(new Callback <BrandBean>() {
            @Override
            public void success(BrandBean data) {
                mView.getBrandsReturn(data);
            }

            @Override
            public void fail(String err) {

            }

        });

    }

}
