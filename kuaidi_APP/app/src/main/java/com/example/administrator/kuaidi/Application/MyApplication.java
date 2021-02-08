package com.example.administrator.kuaidi.Application;

import android.app.Application;
import android.content.Context;

import com.blankj.utilcode.util.Utils;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;;import org.litepal.LitePal;

import cn.jpush.android.api.JPushInterface;

public class MyApplication extends Application {
    private static Context context;
    private ActivityLifecycleCallbacks activityLifecycleCallbacks;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        Utils.init(this);
        LitePal.initialize(this);
        ZXingLibrary.initDisplayOpinion(this);
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);

        registerActivityLifecycleCallbacks(new ActivityLifecycleListener());
    }

    public static Context getContext(){
        return context;
    }

    public static int getAppCount(){
        return ActivityLifecycleListener.getRefCount();
    }

    public static boolean isForeground(){
        return getAppCount() == 0 ? true : false;
    }
}
