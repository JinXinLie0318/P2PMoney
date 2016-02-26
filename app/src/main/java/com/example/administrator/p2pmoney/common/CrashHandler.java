package com.example.administrator.p2pmoney.common;

import android.content.Context;
import android.os.Build;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/2/24.
 * 程序报错捕获器，设计为单例模式
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {
    //构造私有
    private static CrashHandler crashHandler = null;
    private Context mContext;
    private Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler;

    //空参构造
    private CrashHandler() {
    }

    //提供静态方法
    public static CrashHandler getInstance() {
        if (crashHandler == null) {
            crashHandler = new CrashHandler();
        }
        return crashHandler;
    }

    /**
     * 初始化的时候让这个方法称为获取异常的首选项
     *
     * @param context
     */
    public void init(Context context) {
        this.mContext = context;
        //系统抓取错误的對象，把它列出來，當做備胎
        defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        //把我们自己制作的接口设定为優先抓捕的对象
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 當出現異常的時候，彈出提示，，收集異常信息
     * @param thread
     * @param ex
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
//        Log.e("test","來新的抓捕器"+ex.getMessage());
        if (isHandle(ex)){
            //自己处理异常
            handleException(ex);
        }else{
            defaultUncaughtExceptionHandler.uncaughtException(thread,ex);
        }
    }

    private void handleException(Throwable ex) {
        new Thread(){
            @Override
            public void run() {
//                Toast.makeText(mContext,"系统异常，即将退出",Toast.LENGTH_SHORT).show();
                //消息处理机制，这个不是主线程，不像主线程默认绑定了looper，所以不能直接处理消息
                //开启Looper
                Looper.prepare();
                Toast.makeText(mContext,"系统异常，即将退出",Toast.LENGTH_SHORT).show();
                //让looper开始工作
                Looper.loop();
            }
        }.start();
        collectInfo(ex);
        try {
            Thread.sleep(3000);
            AppManager.getInstance().clear();//清空任务栈
            android.os.Process.killProcess(android.os.Process.myPid());//杀进程
            System.exit(0);//关闭虚拟机
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 收集异常信息的方法,方便查看
     * 设备环境，异常的信息
     Build.BOARD // 主板
     Build.BRAND // android系统定制商
     Build.CPU_ABI // cpu指令集
     Build.DEVICE // 设备参数
     Build.DISPLAY // 显示屏参数
     Build.FINGERPRINT // 硬件名称
     Build.HOST
     Build.ID // 修订版本列表
     Build.MANUFACTURER // 硬件制造商
     Build.MODEL // 版本
     Build.PRODUCT // 手机制造商
     Build.TAGS // 描述build的标签
     Build.TIME
     Build.TYPE // builder类型
     Build.USER
     * @param ex
     */
    private void collectInfo(Throwable ex) {
        final String deviceInfo = Build.BRAND+Build.DEVICE+Build.PRODUCT+Build.MODEL+Build.VERSION.SDK_INT;
        final String errorInfo = ex.getMessage();
        new Thread(){
            @Override
            public void run() {
                Log.e("test","deviceInfo:"+deviceInfo+"...errorInfo:"+errorInfo);
            }
        }.start();
    }

    /**
     * 做一个灵活调节，选择做不做
     * @param ex
     * @return
     */
    private boolean isHandle(Throwable ex) {
        if (ex==null){
            return false;
        }
        return true;
    }
}
