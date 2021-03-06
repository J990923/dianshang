package com.example.xiangmu.utils;

import com.example.xiangmu.app.MyApp;

import java.io.File;

public class Constants {

    //网络缓存的地址
    public static final String PATH_DATA = MyApp.app.getCacheDir().getAbsolutePath() + File.separator + "data";

    public static final String PATH_IMGS = PATH_DATA + "/client/imgs";


    public static final String ACTION_UPDATEHEADER = "updateheader"; //头像更新的动作
    public static final String ACTION_UPDATENICKNAME = "upatenickname";  //修改昵称

    public static final int HEAD_WIDTH = 160;//头像宽
    public static final int HEAD_HEIGHT = 160;//头像高
}
