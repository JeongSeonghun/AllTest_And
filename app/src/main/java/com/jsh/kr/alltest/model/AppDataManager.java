package com.jsh.kr.alltest.model;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import com.jsh.kr.alltest.BuildConfig;

public class AppDataManager {

    public String getMetaDataString(Context context, String key) {
        ApplicationInfo appInfo = null;
        String aValue = null;
        try {
            appInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);

            Bundle aBundle = appInfo.metaData;
            if (aBundle != null) {
                aValue = aBundle.getString(key);
            }

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return aValue;
    }

    public String getAppBuildType() {
        return BuildConfig.BUILD_TYPE;
    }

    public int getAppBuildVersion() {
        return Build.VERSION.SDK_INT;
    }

    public Boolean getCustomFieldLogDebug() {
        return BuildConfig.LOG_DEBUG;
    }

    public String getCustomFieldExplain() {
        return  BuildConfig.EXPLAIN;
    }
}

