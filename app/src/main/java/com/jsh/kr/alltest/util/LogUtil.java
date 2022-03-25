package com.jsh.kr.alltest.util;

import android.util.Log;

import com.jsh.kr.alltest.BuildConfig;

public class LogUtil {
//    private static final boolean isDebug = true; // BuildConfig는 빌드를 한번이라도 해야 자동 생성됨
    private static final boolean isDebug = BuildConfig.DEBUG;

    public static void d(String tag, String message) {
        if(isDebug && tag != null && message != null) {
            Log.d(tag, message);
        }
    }

    public static void e(String tag, String message) {
        if(isDebug && tag != null && message != null) {
            Log.e(tag, message);
        }
    }

    public static void e(String tag, String message, Throwable e) {
        if(isDebug && tag != null && message != null) {
            Log.e(tag, message, e);
        }
    }
}
