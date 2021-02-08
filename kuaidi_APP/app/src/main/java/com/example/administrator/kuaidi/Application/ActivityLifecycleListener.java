package com.example.administrator.kuaidi.Application;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

public class ActivityLifecycleListener implements Application.ActivityLifecycleCallbacks {
    private static int refCount = 0;
    public static int getRefCount(){
        return refCount;
    }
    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {
        refCount++;
    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {
        refCount--;
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }
}
