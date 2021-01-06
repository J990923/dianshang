package com.example.xiangmu.api;

import com.example.xiangmu.model.home.bean.BrandBean;
import com.example.xiangmu.model.home.bean.BrandGoodsBean;
import com.example.xiangmu.model.home.bean.BrandListDetailsBean;
import com.example.xiangmu.model.home.bean.GoodsBean;
import com.example.xiangmu.model.home.bean.HotGoodListBean;
import com.example.xiangmu.model.home.bean.NewGoodsBean;

import java.util.HashMap;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface BrandApi {
    String BASE_URL = "http://cdplay.cn/";
    //https://cdplay.cn/api/brand/detail?id=1001000
    @GET("api/brand/detail")
    Flowable <BrandListDetailsBean> getBrand(@Query("id") int id);
    //https://cdplay.cn/api/goods/list?brandId=1001000&page=1&size=1000
    @GET("api/goods/list")
    Flowable<BrandGoodsBean> getGoos(@Query("brandId") int brandId, @Query("page")int page, @Query("size")int size);

    //新品发布的条件筛选数据接口
    @GET("api/goods/list")
    Flowable<HotGoodListBean> getHotGoodList(@QueryMap HashMap <String,String> map);
    //新品首发
    @GET("api/goods/hot")
    Flowable<NewGoodsBean> getNewGoods();

}
