package com.demo.androidtranslateapp.net;

import com.base.interfaces.SNRequestDataListener;
import com.base.net.MCBaseAPI;
import com.base.net.UrlParameters;
import com.demo.androidtranslateapp.ATApplication;
import com.demo.androidtranslateapp.model.UserInfo;

import java.lang.reflect.Type;

public class API extends MCBaseAPI {
    private UserInfo userInfo;

    public API(SNRequestDataListener listener, Type type) {
        super(listener, type);
        userInfo = ATApplication.getUserInfo();
    }

    public void register(String username, String password) {
        UrlParameters params = new UrlParameters();
        params.setUrl("register");
        params.add("username", username);
        params.add("password", password);
        post(params, whichAPI.register);
    }

    public void Login(String username, String password) {
        UrlParameters params = new UrlParameters();
        params.setUrl("login");
        params.add("username", username);
        params.add("password", password);
        post(params, whichAPI.login);
    }


    public class whichAPI {
        private static final int ONE = 10000;
        //注册
        public static final int register = ONE + 1;
        //登陆
        public static final int login = ONE + 2;
    }
}
