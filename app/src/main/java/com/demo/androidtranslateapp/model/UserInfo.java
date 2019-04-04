package com.demo.androidtranslateapp.model;

import com.base.model.Base;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

public class UserInfo implements Serializable {
    public static Type getListClassType(){
        return new TypeToken<Base<List<UserInfo>>>() {
        }.getType();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private int id;
    private String username;
    private String password;
}
