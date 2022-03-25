package com.jsh.kr.alltestlib.manager;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

public class PreferenceManager {

    private String prefName;

    public PreferenceManager(String prefName) {
        this.prefName = prefName;
    }

    public SharedPreferences getPreference(Context context) {
        return context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
    }

    public void setBoolean(Context context, String key, boolean data) {
        SharedPreferences pref = getPreference(context);
        SharedPreferences.Editor editor = pref.edit();

        editor.putBoolean(key, data);

        editor.apply();
    }

    public void setInt(Context context, String key, int data) {
        SharedPreferences pref = getPreference(context);
        SharedPreferences.Editor editor = pref.edit();

        editor.putInt(key, data);

        editor.apply();
    }


    public void setString(Context context, String key, String data) {
        SharedPreferences pref = getPreference(context);
        SharedPreferences.Editor editor = pref.edit();

        editor.putString(key, data);

        editor.apply();
    }

    public Map<String, ?> getAllData(Context context) {
        SharedPreferences pref = getPreference(context);
        return pref.getAll();
    }

    public void removeAll(Context context) {
        SharedPreferences pref = getPreference(context);
        SharedPreferences.Editor editor = pref.edit();

        editor.clear();
        editor.apply();
    }

    public void remove(Context context, String key) {
        SharedPreferences pref = getPreference(context);
        SharedPreferences.Editor editor = pref.edit();

        editor.remove(key);
        editor.apply();
    }
}
