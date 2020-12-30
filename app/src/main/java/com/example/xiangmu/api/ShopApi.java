package com.example.xiangmu.api;



import com.example.xiangmu.model.Login.MeRegisterBean;
import com.example.xiangmu.model.home.bean.BrandBean;
import com.example.xiangmu.model.home.bean.CategoryBean;
import com.example.xiangmu.model.home.bean.GoodsBean;
import com.example.xiangmu.model.home.bean.HomeBean;
import com.example.xiangmu.model.home.bean.LoginBean;
import com.example.xiangmu.model.home.bean.SPGoodsDetail;
import com.example.xiangmu.model.home.bean.SPGoodsRelated;
import com.example.xiangmu.model.home.bean.Shop_AddBean;
import com.example.xiangmu.model.home.bean.SortBean;
import com.example.xiangmu.model.home.bean.SortDataBean;
import com.example.xiangmu.model.shap.AddCarBean;
import com.example.xiangmu.model.shap.CarBean;
import com.example.xiangmu.model.shap.DeleteCarBean;
import com.example.xiangmu.model.shap.GoodDetailBean;
import com.example.xiangmu.model.shap.UpdateCarBean;
import com.example.xiangmu.ui.topic.bean.SpecialDetailsBean;
import com.example.xiangmu.ui.topic.bean.SpecialDetailsButtomBean;
import com.example.xiangmu.ui.topic.bean.SpecialDetailsCommentBean;

import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ShopApi {

    String BASE_URL = "http://cdplay.cn/";

    @GET("api/index")
    Flowable <HomeBean> getHome();
    //https://cdplay.cn/api/catalog/index/pages/category/category?id=1005000
    ///pages/category/category?id=1005000
    @GET("api/catalog/index")
    Flowable <CategoryBean> getCategory(@Query("url") String url);
    //https://cdplay.cn/api/goods/list?categoryId=1005000&page=1&size=200x
    @GET("api/goods/list")
    Flowable<GoodsBean> getGoos(@Query("categoryId") int categoryId, @Query("page")int page, @Query("size")int size);
    //https://cdplay.cn/
    @GET("api/brand/list?page=1&size=1000")
    Flowable <BrandBean> getBrand();
    //商品 详情购买页 api/goods/detail?id=1155000
    @GET("api/goods/detail")
    Flowable<SPGoodsDetail> getGoodsDetailData(@Query("id")int id);
    //商品 详情购买页 底部数据列表 api/goods/related?id=1155000
    @GET("api/goods/related")
    Flowable<SPGoodsRelated> getGoodsRelatedData(@Query("id")int id);
    //分类
    @GET("api/catalog/index")
    Flowable<SortBean> getSort();
    // 分类右边数据cdplay.cn/api/catalog/current?id=1005001  https://cdwan.cn/api/catalog/current?id=1005001
    @GET("api/catalog/current")
    Flowable<SortDataBean> getSortData(@Query("id")int id);
    @POST("api/auth/login")
    @FormUrlEncoded
    Flowable<LoginBean> login(@Field("username") String username, @Field("password") String pw);
    //添加到购物车
    @POST("cart/add")       //  goodsId=1035006 number=1   productId=47     // 1116033   1   171
    @FormUrlEncoded
    Flowable<Shop_AddBean> ShopAddCar(@Field("goodsId")int goodsId, @Field("number")String number, @Field("productId")int productId);
    //商品详情购买页
    @GET("api/goods/detail")
    Flowable<GoodDetailBean> getGoodDetail(@Query("id") int id);
    //添加到购物车
    @POST("api/cart/add")
    @FormUrlEncoded
    Flowable<AddCarBean> addCar(@FieldMap Map <String,String> map);

    //购物车列表
    @GET("api/cart/index")
    Flowable<CarBean> getCarList();

    //更新购物车的数据
    @POST("api/cart/update")
    @FormUrlEncoded
    Flowable<UpdateCarBean> updateCar(@FieldMap Map <String,String> map);


    //删除购物车数据
    @POST("api/cart/delete")
    @FormUrlEncoded
    Flowable<DeleteCarBean> deleteCar(@Field("productIds") String productIds);

    //注册接口
    @POST("auth/register")
    @FormUrlEncoded
    Flowable<MeRegisterBean> getMeRegist(@Field("username") String username, @Field("password") String password);

    //专题详情页评论评论数据    valueId=314&typeId=1&size=5
    @GET("comment/list")
    Flowable<SpecialDetailsCommentBean> getSpecialDetailsComment(@QueryMap Map<String,String> map);

    //专题详情数据
    @GET("api/topic/detail")
    Flowable<SpecialDetailsBean> getSpecialDetails(@Query("id") int id);

    //https://cdplay.cn/api/topic/related?id=314

    //专题底部列表
    @GET("api/topic/related")
    Flowable<SpecialDetailsButtomBean> getSpecialDetailsButtom(@Query("id") int id);
}
