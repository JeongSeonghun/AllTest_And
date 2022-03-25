package com.jsh.kr.alltestlib.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

import androidx.annotation.StringRes;

public class Util {
    /**
     * use ex)
     * urlPath = Demo
     * String jsonStr = Util.getJsonFromAssets(TestApplication.getContext(), "demojson/" + urlPath + ".json");
     * @param context app context
     * @param fileName asset file name
     * @return json string
     */
    public static String getJsonFromAssets(Context context, String fileName) {
        String line = "";
        StringBuilder result = new StringBuilder();
        InputStreamReader inputReader;
        InputStream inputStream = null;
        BufferedReader bufReader = null;
        try {
            inputStream = context.getResources().getAssets().open(fileName);
            inputReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            bufReader = new BufferedReader(inputReader);
            while ((line = bufReader.readLine()) != null) {
                result.append(line);
            }
            bufReader.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    public static String getStringResourceByName(Context context, String aString, @StringRes int defaultId) {
        String packageName = context.getPackageName();
        int resId = context.getResources().getIdentifier(aString, "string", packageName);
        if (resId == 0) {
            return context.getResources().getString(defaultId);
        } else {
            return context.getString(resId);
        }
    }

    public static int getStringResourceIdByName(Context context, String aString, @StringRes int defaultId) {
        String packageName = context.getPackageName();
        int resId = context.getResources().getIdentifier(aString, "string", packageName);
        if (resId == 0) {
            return defaultId;
        } else {
            return resId;
        }
    }


    public static boolean isBackground(Context context, String packageName) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (am != null) {
            List<ActivityManager.RunningAppProcessInfo> proceses = am.getRunningAppProcesses();
            //프로세서 전체를 반복
            for(ActivityManager.RunningAppProcessInfo process : proceses)
            {
                if(process.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_BACKGROUND)
                {

                    if(process.processName.equals(packageName)) {
                        return true;
                    }

                }
            }
        }

        return false;
    }

    public static void checkDisplay(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int getDeviceHeight_Pixel = displayMetrics.heightPixels;
        int getDeviceWidth_Pixel = displayMetrics.widthPixels;

        int getDeviceDpi = displayMetrics.densityDpi;
        float density = displayMetrics.density;

        int dipWidth = (int) (getDeviceWidth_Pixel / displayMetrics.density);
        int dipHeight = (int) (getDeviceHeight_Pixel / displayMetrics.density);

        Log.d("display check", "density : "+density);
        Log.d("display check", "density dpi : "+getDeviceDpi);
        Log.d("display check", "pixel width/height : "+getDeviceWidth_Pixel+"/"+getDeviceHeight_Pixel);
        Log.d("display check", "dp width/height: "+dipWidth+"/"+dipHeight);
    }

    public static int dpToPx(int dp)
    {
        float density = Resources.getSystem().getDisplayMetrics().density;
        return Math.round((float) dp * density); // 일부 단말 dp 오차로 round 사용
//        return (int) (dp * density);
    }

    public static int pxToDp(int px)
    {
        float density = Resources.getSystem().getDisplayMetrics().density;
        return (int) (px / density);
    }
}
