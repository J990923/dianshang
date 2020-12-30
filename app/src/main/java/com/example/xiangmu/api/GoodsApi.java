package com.example.xiangmu.api;

import com.example.xiangmu.model.topic.TwoBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface GoodsApi {
    String BASE_URL = "http://cdplay.cn/";
    //专题页面
    @GET("api/topic/list")
    Flowable<TwoBean> getTwo();

    //专题第二页
    @GET("api/topic/list?page=2")
    Flowable<TwoBean> getTwoo();


}
