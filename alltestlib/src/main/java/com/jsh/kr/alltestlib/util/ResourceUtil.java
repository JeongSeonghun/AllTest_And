package com.jsh.kr.alltestlib.util;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;

import androidx.annotation.ArrayRes;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.StyleRes;
import androidx.appcompat.content.res.AppCompatResources;

public class ResourceUtil {

    public static int getColor(Context context, @ColorRes int colorResId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return context.getResources().getColor(colorResId, null);
        } else {
            return context.getResources().getColor(colorResId);
        }
    }

    /**
     * to test, theme operate above api 23
     * theme be used by color StatesList xml default value
     * if use ?attr in color, occur exception
     *
     * if use theme, when min version is above 23 use method
     */
    public static int getColorWithTheme(Context context, @ColorRes int colorResId) {
        return getColorWithTheme(context, colorResId, context.getTheme());
    }
    public static int getColorWithTheme(Context context, @ColorRes int colorResId, @StyleRes int styleResId) {
        Resources.Theme theme = context.getTheme();
        theme.applyStyle(styleResId, true);
        return getColorWithTheme(context, colorResId, theme);
    }
    private static int getColorWithTheme(Context context, @ColorRes int colorResId, Resources.Theme theme) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return context.getResources().getColor(colorResId, theme);
        } else {
            return context.getResources().getColor(colorResId);
        }
    }

    public static ColorStateList getColorStatesList(Context context, @ColorRes int colorResId) {
        return AppCompatResources.getColorStateList(context, colorResId);
    }

    public static Drawable getDrawable(Context context, @DrawableRes int drawableResId) {
        return AppCompatResources.getDrawable(context, drawableResId);
    }

    public static String[] getStringArray(Context context, @ArrayRes int stringArrayId) {
        return context.getResources().getStringArray(stringArrayId);
    }
}
