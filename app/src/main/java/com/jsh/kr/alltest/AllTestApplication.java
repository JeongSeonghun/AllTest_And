package com.jsh.kr.alltest;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import com.jsh.kr.alltest.model.AllTestDirectory;
import com.jsh.kr.alltest.model.AppData;
import com.jsh.kr.alltest.util.LogUtil;

import java.util.List;

public class AllTestApplication extends Application {

    private static AllTestApplication application = null;
    private static boolean loginSuccess = false;
    private static ActivityLifecycleCallbacks lifecycleCallbacks;

    private AppData appData = new AppData();

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.d(this.getClass().getSimpleName(), "onCreate()");
        leakTest();
        appConfig();
        registerActivityLifecycleCallbacks(new ActivityLifecycleHandler());
    }

    private void leakTest() {
//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            // This process is dedicated to LeakCanary for heap analysis.
//            // You should not init your app in this process.
//            return;
//        }
//        LeakCanary.install(this);
    }

    private void appConfig() {
        application = this;
        AllTestDirectory.init(this);
    }

    public static Context getContext() {
        return application;
    }

    public static void setLoginSuccess(boolean loginSuccess) {
        AllTestApplication.loginSuccess = loginSuccess;
    }

    public static boolean isLoginSuccess() {
        return loginSuccess;
    }

    public static AllTestApplication getApplication() {
        return application;
    }

    public AppData getAppData() {
        return appData;
    }

    // App 종료 시 Tizen service 종료를 위해 추가(foreground service 사용 시 보안 상 notification 표시로 인하여 추가됨)
    public class ActivityLifecycleHandler implements ActivityLifecycleCallbacks {

        @Override
        public void onActivityCreated(Activity activity, Bundle bundle) {
            LogUtil.d(this.getClass().getSimpleName(), "onActivityCreated()");
        }

        @Override
        public void onActivityStarted(Activity activity) {
            LogUtil.d(this.getClass().getSimpleName(), "onActivityStarted()");
        }

        @Override
        public void onActivityResumed(Activity activity) {
            LogUtil.d(this.getClass().getSimpleName(), "onActivityResumed()");
        }

        @Override
        public void onActivityPaused(Activity activity) {
            LogUtil.d(this.getClass().getSimpleName(), "onActivityPaused()");
        }

        @Override
        public void onActivityStopped(Activity activity) {
            LogUtil.d(this.getClass().getSimpleName(), "onActivityStopped()");
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            LogUtil.d(this.getClass().getSimpleName(), "onActivitySaveInstanceState()");
        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            LogUtil.d(this.getClass().getSimpleName(), "onActivityDestroyed()");
//            if(!checkAppRunning()) {
//                WMessageSender.release();
//            }
        }
    }

    private Boolean checkAppRunning() {
        ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        if(am == null) {
            return false;
        }
        List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(100);
        if (list.size() <= 0) {
            return false;
        }

//        for (ActivityManager.RunningTaskInfo info : list) {
//            if (info.baseActivity.getPackageName().equals(WActivities.BASE_ACTIVITY_PACKAGE)) {
//                return true;
//            }
//        }
        return false;
    }
}
