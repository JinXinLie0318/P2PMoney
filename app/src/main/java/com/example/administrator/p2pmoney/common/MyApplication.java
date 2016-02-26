package com.example.administrator.p2pmoney.common;

import android.app.Application;
import android.content.Context;
import android.os.Handler;


/**
 * Created by Administrator on 2016/2/24.
 */
public class MyApplication extends Application {
    public static Context Context = null;
    public static Handler handler = null;
    public static Thread mainThread = null;
    public static int mainThreadId = 0;

    @Override
    public void onCreate() {
        Context = getApplicationContext();
        handler = new Handler();
        mainThread = Thread.currentThread();
        mainThreadId = android.os.Process.myTid();
        CrashHandler.getInstance().init(Context);//在这里注册一下
    }
}
