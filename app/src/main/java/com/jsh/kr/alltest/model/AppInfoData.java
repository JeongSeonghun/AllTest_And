package com.jsh.kr.alltest.model;

public class AppInfoData {
    private boolean isLoginMode;
    private boolean isMaintainTestMode;
    private int maintainTestMenu;
    private String maintainTestAct;

    public boolean isLoginMode() {
        return isLoginMode;
    }

    public void setLoginMode(boolean loginMode) {
        isLoginMode = loginMode;
    }

    public boolean isMaintainTestMode() {
        return isMaintainTestMode;
    }

    public void setMaintainTestMode(boolean maintainTestMode) {
        isMaintainTestMode = maintainTestMode;
    }

    public int getMaintainTestMenu() {
        return maintainTestMenu;
    }

    public void setMaintainTestMenu(int maintainTestMenu) {
        this.maintainTestMenu = maintainTestMenu;
    }

    public String getMaintainTestAct() {
        return maintainTestAct;
    }

    public void setMaintainTestAct(String maintainTestAct) {
        this.maintainTestAct = maintainTestAct;
    }
}

