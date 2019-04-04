package com.demo.androidtranslateapp;

import android.annotation.SuppressLint;
import android.content.Context;

import com.base.BPApplication;
import com.base.util.LoginManager;
import com.demo.androidtranslateapp.model.UserInfo;

public class ATApplication extends BPApplication {
    private static int type = 1;
    private static String NetPath;
    private static String NetFilePath;
    private static Context context;
    private static UserInfo userInfo;

    static {

        NetPath = "http://47.105.161.233:8090/translateApp/";
        NetFilePath = "http://47.105.161.233:8090/";
    }


    @SuppressLint("ResourceType")
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        setCACHE_PATH("AndroidTranslateApp");
        setNET_PATH(NetPath);
        setNET_FILE_PATH(NetFilePath);
        setAppThemeColor(getResources().getString(R.color.appThemeColor), getResources().getColor(R.color.appThemeColor));

    }

    public static UserInfo getUserInfo() {
        return userInfo;
    }

    public static void setUserInfo(UserInfo info) {
        userInfo = info;
    }

    public static void onRefreshLogin() {
        LoginManager.saveObject(context, userInfo);
    }

    public static void onRefreshLogin(UserInfo userInfo) {
        LoginManager.saveObject(context, userInfo);
    }
}
