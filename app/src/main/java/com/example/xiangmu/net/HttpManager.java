package com.example.xiangmu.net;

import android.util.Log;


import com.example.xiangmu.api.BrandApi;
import com.example.xiangmu.api.GoodsApi;
import com.example.xiangmu.api.ServiceApi;
import com.example.xiangmu.api.ShopApi;
import com.example.xiangmu.utils.SpUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpManager {
    private static HttpManager instance;

    public  static HttpManager getInstance(){
        if(instance == null){
            synchronized(HttpManager.class){
                if(instance == null){
                    instance = new HttpManager();
                }
            }
        }
        return instance;
    }

    private ServiceApi serviceApi;

    private ShopApi shopApi;

    private BrandApi brandApi;

    private GoodsApi goodsApi;
    private Retrofit getRetrofit(String url){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url)
                .client(getOkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    private OkHttpClient getOkHttpClient(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60,TimeUnit.SECONDS)
                .addInterceptor(new LoggingInterceptor())
                .addInterceptor(new HeaderInterceptor())
                .build();
        return okHttpClient;
    }

    static class LoggingInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Response response = chain.proceed(request);
            ResponseBody responseBody = response.peekBody(Integer.MAX_VALUE);
            Log.i("responseBody",responseBody.string());
            return chain.proceed(request);
        }
    }

    /**
     * 拦截的头处理
     */
    static class HeaderInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request().newBuilder()
                    .addHeader("Authorization","APPCODE 964e16aa1ae944e9828e87b8b9fbd30a")
                    .addHeader("X-Nideshop-Token", SpUtils.getInstance().getString("token"))
                    //注册
                    .addHeader("Client-Type",SpUtils.getInstance().getString("token"))
                    .build();
            return chain.proceed(request);
        }
    }

    /**
     * ServiceApi
     * @return
     */
    public ServiceApi getService(){
        if(serviceApi == null){
            serviceApi = getRetrofit(ServiceApi.BASE_URL).create(ServiceApi.class);
        }
        return serviceApi;
    }

    /**
     * 商城的api
     * @return
     */
    public ShopApi getShopApi(){
        if(shopApi == null){
            shopApi = getRetrofit(ShopApi.BASE_URL).create(ShopApi.class);
        }
        return shopApi;
    }
    /*
    * 品牌制造商直供
    * */
    public BrandApi getBrandApi(){
        if(brandApi == null){
            brandApi = getRetrofit(BrandApi.BASE_URL).create(BrandApi.class);
        }
        return brandApi;
    }

    /*
     * 专题
     * */
    public GoodsApi  getGoodsApi(){
        if(goodsApi == null){
            goodsApi = getRetrofit(GoodsApi.BASE_URL).create(GoodsApi.class);
        }
        return goodsApi;
    }

}
