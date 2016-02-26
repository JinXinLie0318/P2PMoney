package com.example.administrator.p2pmoney.util;

import android.content.Context;
import android.os.Handler;
import android.view.View;

import com.example.administrator.p2pmoney.common.MyApplication;


/**
 * Created by Administrator on 2016/2/24.
 * 这个类是处理UI相关的工具包
 */
public class UiUtils {
    public static int getColor(int colorId) {
        return getContext().getResources().getColor(colorId);
    }

    ;


    public static View getXmlView(int layoutId) {
        View view = View.inflate(getContext(), layoutId, null);
        return view;
    }

    public static String[] getStringArr(int strArrId) {
        return getContext().getResources().getStringArray(strArrId);
    }

    /**
     * @param dp
     * @return
     */
    public static int dp2px(int dp) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (dp * density + 0.5);
    }

    public static int px2dp(int px) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (px / density + 0.5);
    }

    ;

    /**
     * 保证runnable对象运行在主线程里面
     *
     * @param runnable
     */
    public static void runOnUiThread(Runnable runnable) {
        if (isInMainThread()) {
            runnable.run();
        } else {
            getHandler().post(runnable);
        }
    }

    private static Handler getHandler() {
        return MyApplication.handler;
    }


    private static boolean isInMainThread() {
        int curThreadId = android.os.Process.myTid();
        if (curThreadId == MyApplication.mainThreadId) {
            return true;
        }
        return false;
    }

    public static Context getContext() {
        return MyApplication.Context;
    }


}
