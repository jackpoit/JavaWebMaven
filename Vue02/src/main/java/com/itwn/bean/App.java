package com.itwn.bean;

import java.io.Serializable;

public class App implements Serializable {
    private Integer id;
    private String appName;
    private Integer appSize;
    private String appType;
    private String appPlatform;
    private Integer appCount;
    private String appImg;
    public App() {
    }
    public App(Integer id, String appName, Integer appSize, String appType, String appPlatform, Integer appCount, String appImg) {
        this.id = id;
        this.appName = appName;
        this.appSize = appSize;
        this.appType = appType;
        this.appPlatform = appPlatform;
        this.appCount = appCount;
        this.appImg = appImg;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Integer getAppSize() {
        return appSize;
    }

    public void setAppSize(Integer appSize) {
        this.appSize = appSize;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public String getAppPlatform() {
        return appPlatform;
    }

    public void setAppPlatform(String appPlatform) {
        this.appPlatform = appPlatform;
    }

    public Integer getAppCount() {
        return appCount;
    }

    public void setAppCount(Integer appCount) {
        this.appCount = appCount;
    }

    public String getAppImg() {
        return appImg;
    }

    public void setAppImg(String appImg) {
        this.appImg = appImg;
    }

    @Override
    public String toString() {
        return "App{" +
                "id=" + id +
                ", appName='" + appName + '\'' +
                ", appSize=" + appSize +
                ", appType='" + appType + '\'' +
                ", appPlatform='" + appPlatform + '\'' +
                ", appCount=" + appCount +
                ", appImg='" + appImg + '\'' +
                '}';
    }
}
