package com.example.administrator.p2pmoney.common;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by Administrator on 2016/2/24.
 * AppManager设计为单例模式
 * 添加，删除，清空，删除指定，求栈的大小
 */
public class AppManager {
    private Stack<Activity> activityStack = new Stack<>();
    //三步曲；构造私有，声明空参对象，提供一个静态方法
    private static AppManager appManager = null;

    private AppManager() {

    }

    public static AppManager getInstance() {
        if (appManager == null) {
            appManager = new AppManager();
        }
        return appManager;
    }

    /**
     * 添加一个activity
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        activityStack.add(activity);
    }

    /**
     * 删除指定的actiity
     *
     * @param activity
     */
    public void removeActivity(Activity activity) {
        for (int i = activityStack.size() - 1; i >= 0; i--) {
            if (activityStack.get(i).equals(activity.getClass())) {
                activity.finish();
                activityStack.remove(activityStack.get(i));
            }
        }
    }

    /**
     * 删除当前的activity,就是栈中最后的一个activity
     */
    public void removeCurrent() {
        Activity activity = activityStack.lastElement();
        activity.finish();
        activityStack.remove(activity);
    }

    /**
     * 清空栈
     */
    public void clear() {
        for (int i = activityStack.size() - 1; i >= 0; i--) {
            Activity activity = activityStack.get(i);
            activity.finish();
        }
        activityStack.clear();
    }

    /**
     * 获取栈中activity 的数量
     * @return
     */
    public int getStackSize(){
        return activityStack.size();
    }

}
