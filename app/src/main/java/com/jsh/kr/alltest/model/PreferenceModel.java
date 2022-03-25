package com.jsh.kr.alltest.model;

import android.content.SharedPreferences;

import com.jsh.kr.alltest.AllTestApplication;
import com.jsh.kr.alltest.C;
import com.jsh.kr.alltestlib.manager.PreferenceManager;

import java.util.Map;

public class PreferenceModel {

    private PreferenceManager appDataManager;
    private PreferenceManager prefTestManager;

    public PreferenceModel() {
        initAppData();
    }

    public void initAppData() {
        appDataManager = new PreferenceManager(C.Preference.NAME_APP_DATA);
    }

    public void initPrefTest() {
        prefTestManager = new PreferenceManager(C.Preference.NAME_APP_DATA);
    }

    public AppInfoData getAppData() {
        if (appDataManager == null) return null;

        SharedPreferences pref = appDataManager.getPreference(AllTestApplication.getContext());
        AppInfoData appInfoData = new AppInfoData();

        /**
         * when run app, show login activity
         */
        boolean isLoginMode = pref.getBoolean(C.Preference.KEY_MODE_LOGIN, false);
        /**
         * move before test mode
         */
        boolean isMaintainTestMode = pref.getBoolean(C.Preference.KEY_MODE_MAINTAIN_TEST, true);
        int maintainTestMenu = pref.getInt(C.Preference.KEY_MAINTAIN_TEST_MENU, C.Maintain.Menu_UI);
        String maintainTestAct = pref.getString(C.Preference.KEY_MAINTAIN_TEST_ACT, null);

        appInfoData.setLoginMode(isLoginMode);
        appInfoData.setMaintainTestMode(isMaintainTestMode);
        appInfoData.setMaintainTestMenu(maintainTestMenu);
        appInfoData.setMaintainTestAct(maintainTestAct);

        return appInfoData;
    }

    public void setLoginMode(boolean isLoginMode) {
        if (appDataManager == null) return;
        appDataManager.setBoolean(AllTestApplication.getContext(), C.Preference.KEY_MODE_LOGIN, isLoginMode);
    }

    public void setMaintainTestMode(boolean isMaintainTestMode) {
        if (appDataManager == null) return;
        appDataManager.setBoolean(AllTestApplication.getContext(), C.Preference.KEY_MODE_MAINTAIN_TEST, isMaintainTestMode);
    }

    public void setMaintainTestMenu(int maintainTestMenu) {
        if (appDataManager == null) return;
        appDataManager.setInt(AllTestApplication.getContext(), C.Preference.KEY_MAINTAIN_TEST_MENU, maintainTestMenu);
    }

    public void setMaintainTestAct(String maintainTestAct) {
        if (appDataManager == null) return;
        appDataManager.setString(AllTestApplication.getContext(), C.Preference.KEY_MAINTAIN_TEST_ACT, maintainTestAct);
    }

    public String getPreferenceTestAllDataString() {
        if (prefTestManager == null) return "";
        Map<String, ?> data = prefTestManager.getAllData(AllTestApplication.getContext());

        StringBuilder sb = new StringBuilder();
        if(data != null) {
            for(String key : data.keySet()) {
                sb.append(key)
                        .append(":")
                        .append(data.get(key))
                        .append("\n");
            }
        }

        return sb.toString();
    }

    public void setTestStringData(String key, String data) {
        if (prefTestManager == null) return;
        prefTestManager.setString(AllTestApplication.getContext(), key, data);
    }

    public void setTestIntData(String key, int data) {
        if (prefTestManager == null) return;
        prefTestManager.setInt(AllTestApplication.getContext(), key, data);
    }

}

