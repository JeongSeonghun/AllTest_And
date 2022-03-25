package com.jsh.kr.alltest.model;

import com.jsh.kr.alltest.AllTestApplication;
import com.jsh.kr.alltest.C;
import com.jsh.kr.alltestlib.util.RandomString;

public class AppDataUtil {

    private PreferenceModel preferenceModel;

    /**
     * SDMAppDataUtil create new Instance.
     */
    private static final AppDataUtil ourInstance = new AppDataUtil();

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static AppDataUtil getInstance() {
        return ourInstance;
    }

    /**
     * demo mode.
     */
    private String DEMO_MODE = "DemoMode";

    /**
     * log in response.
     */
    private String LOGINRESPONSE = "loginResponse";

    private boolean isDemoMode = false;
    private String secKey = null;
    private int isWatch = 0;

    private boolean isLoginMode = false;
    private boolean isMaintainTestMode = true;
    private int maintainTestMenu = C.Maintain.Menu_UI;
    private String maintainTestAct = null;

    /**
     * Instantiates a new Sdm app data util.
     */
    private AppDataUtil() {
        testInit();
    }

    private void testInit() {
        preferenceModel = new PreferenceModel();
        isDemoMode = false;
        secKey = null;
        isWatch = 0;
        loadData();
    }

    public void setLoginMode(boolean isLoginMode) {
        this.isLoginMode  = isLoginMode;

        preferenceModel.setLoginMode(isLoginMode);
    }

    public boolean isLoginMode() {
        return isLoginMode;
    }

    public void setMainTainTestMode(boolean isMaintainTestMode) {
        this.isMaintainTestMode = isMaintainTestMode;

        preferenceModel.setMaintainTestMode(isMaintainTestMode);
    }

    public boolean isMaintainTestMode() {
        return isMaintainTestMode;
    }

    public void setMaintainTestMenu(int maintainTestMenu) {
        this.maintainTestMenu = maintainTestMenu;

        preferenceModel.setMaintainTestMenu(maintainTestMenu);
    }

    public int getMaintainTestMenu() {
        return maintainTestMenu;
    }

    public void setMaintainTestAct(String maintainTestAct) {
        this.maintainTestAct = maintainTestAct;

        preferenceModel.setMaintainTestAct(maintainTestAct);
    }

    public String getMaintainTestAct() {
        return maintainTestAct;
    }

    private void loadData() {
        AppInfoData appInfoData = preferenceModel.getAppData();

        isLoginMode = appInfoData.isLoginMode();
        isMaintainTestMode = appInfoData.isMaintainTestMode();
        maintainTestMenu = appInfoData.getMaintainTestMenu();
        maintainTestAct = appInfoData.getMaintainTestAct();
    }

    /**
     * Sets demo mode.
     *
     * @param isDemo the is demo
     */
    public void setDemoMode(final boolean isDemo) {
//        Hawk.put(DEMO_MODE, isDemo);
        isDemoMode = isDemo;
    }

    /**
     * Is demo mode boolean.
     *
     * @return the boolean
     */
    public boolean isDemoMode() {
//        if (Hawk.isBuilt()) {
//            return Hawk.get(DEMO_MODE, false);
//        } else {
//            return false;
//        }
        return isDemoMode;
    }

    public void deleteAll(){
//        if(Hawk.isBuilt()){
//            Hawk.deleteAll();
//        }
    }

    public int getWatch() {
//        if (Hawk.isBuilt()) {
//            isWatch = Hawk.get(WATCHSTATUS + getAccountInfo().getMtsNo(), 2);
//        } else {
//            isWatch = 2;
//        }

        return isWatch;
    }

    public void setWatch(int isWatch) {
        this.isWatch = isWatch;
//        if (Hawk.isBuilt()) {
//            Hawk.put(WATCHSTATUS + getAccountInfo().getMtsNo(), isWatch);
//        }
    }

    public String getSecKey(){
        String key = null;
//        if (Hawk.isBuilt()) {
//            key = Hawk.get(SECKEY, null);
//
//            if(key == null) {
//                if(secKey == null) {
//                    secKey = createAppKey();
//                }
//                Hawk.put(SECKEY, secKey);
//            }else {
//                secKey = key;
//            }
//        } else {
//            if(secKey == null) {
//                secKey = createAppKey();
//            }
//
//        }
        if(secKey == null) {
            secKey = createAppKey();
        }
        return secKey;
    }

    private String createAppKey() {
        // AES256에 사용될 암호 키 생성

        RandomString randomString = new RandomString(32);
        String seccKey = randomString.nextString();

        return seccKey;
    }

    public void notifyWatchLoginChecking() {
//        try {
//            if (AllTestApplication.getContext() != null) {
//                CellResponse message = new CellResponse();
//                message.setPayLoad(WConstants.LOG);
//                message.setLogOutput(WConstants.MOBILETYPE, WConstants.LOG_IN_CHECKING, AppDataUtil.getInstance().getWatch());
//                WMessageSender messageSender = new WMessageSender(AllTestApplication.getContext());
//                messageSender.sendMessage(message.getOutput().toString().getBytes("UTF-8"));
//            }
//        } catch (JSONException e) {
//            LogUtil.e("SDMAppDataUtil", "JsonException", e);
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            LogUtil.e("SDMAppDataUtil", "notifyWatchLoginChecking", e);
//        }
    }

    /**
     * Logout.
     */
    public void notifyWatchNotLogin() {
//        try {
//            CellResponse message = new CellResponse();
//            message.setPayLoad(WConstants.LOG);
//            message.setLogOutput(WConstants.MOBILETYPE, WConstants.NOT_LOG_IN, AppDataUtil.getInstance().getWatch());
//            WMessageSender messageSender = new WMessageSender(AllTestApplication.getContext());
//            messageSender.sendMessage(message.getOutput().toString().getBytes("UTF-8"));
//        } catch (JSONException e) {
//            LogUtil.e("SDMAppDataUtil", "JsonException", e);
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
    }

    /**
     * Logout.
     */
    public void logout() {
//        AllTestApplication.setLoginSuccess(false);
//        try {
//            CellResponse message = new CellResponse();
//            message.setPayLoad(WConstants.LOG_OFF);
//            message.setOutput(WConstants.MOBILETYPE);
//            WMessageSender messageSender = new WMessageSender(AllTestApplication.getContext());
//            messageSender.sendMessage(message.getOutput().toString().getBytes("UTF-8"));
//        } catch (JSONException e) {
//            LogUtil.e("SDMAppDataUtil", "JsonException", e);
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
    }

    /**
     * logout without close auto login.
     */
    public void difLogOut() {
//        AllTestApplication.setLoginSuccess(false);
//        try {
//            CellResponse message = new CellResponse();
//            message.setPayLoad(WConstants.LOG_OFF);
//            message.setOutput(WConstants.MOBILETYPE);
//            WMessageSender messageSender = new WMessageSender(AllTestApplication.getContext());
//            messageSender.sendMessage(message.getOutput().toString().getBytes("UTF-8"));
//        } catch (JSONException e) {
//            LogUtil.e("SDMAppDataUtil", "JsonException", e);
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
    }

    /**
     * Reset.
     */
    public void reset() {
        if (!isDemoMode()) {
            logout();
            AllTestApplication.setLoginSuccess(false);
            deleteAll();
        }
    }
}
