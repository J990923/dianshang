package com.example.xiangmu.app;

import android.app.Application;
import android.content.Context;


import com.live.MyApplication;

import java.util.HashMap;
import java.util.Map;

public class MyApp extends Application {

    private static final String[] MODULESLIST =
            {"com.example.xiangmu.app.MyApp",
                    "com.live.app.MyApplication"};
    public static MyApp app;
    public static HashMap<String,Object> map;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        map=new HashMap<>();
    }

    public static MyApp getInstance() {
        return app;
    }

    private static Context mAppContext = null;

    public static HashMap<String, Object> getMap() {
        return map;
    }
    private void initMoudles() {
        for (String moduleImpl : MODULESLIST){
            try {
                Class<?> clazz = Class.forName(moduleImpl);
                Object obj = clazz.newInstance();
                if (obj instanceof MyApplication){
                    ((MyApplication) obj).initApp(app);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

}
