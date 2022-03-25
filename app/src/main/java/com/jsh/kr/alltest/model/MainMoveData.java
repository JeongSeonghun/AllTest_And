package com.jsh.kr.alltest.model;

public class MainMoveData {
    private Class actClass;
    private int btnNameId;
    private String tag;
    private int state;

    public MainMoveData(Class actClass, int btnNameId) {
        this.actClass = actClass;
        this.btnNameId = btnNameId;
    }

    public void setActClass(Class actClass) {
        this.actClass = actClass;
    }

    public Class getActClass() {
        return actClass;
    }

    public void setBtnNameId(int btnNameId) {
        this.btnNameId = btnNameId;
    }

    public int getBtnNameId() {
        return btnNameId;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }
}
