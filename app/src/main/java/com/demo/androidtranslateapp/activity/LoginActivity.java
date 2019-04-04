package com.demo.androidtranslateapp.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.base.baseClass.BaseActivity;
import com.base.model.Base;
import com.base.util.Tool;
import com.demo.androidtranslateapp.R;
import com.demo.androidtranslateapp.model.UserInfo;
import com.demo.androidtranslateapp.net.API;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {
    @BindView(R.id.user_et)
    EditText userEt;
    @BindView(R.id.password_et)
    EditText passwordEt;
    @BindView(R.id.login_bt)
    Button loginBt;
    private String username;
    private String password;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {

    }

    private void login() {
        username = Tool.getTextViewContent(userEt);
        password = Tool.getTextViewContent(passwordEt);
        new API(this, UserInfo.getListClassType()).Login(username, password);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        Intent intent = getIntent();
        username = intent.getStringExtra("username");

        userEt.setText(username);

    }

    @OnClick(R.id.login_bt)
    public void onViewClicked() {
        login();
    }

    @Override
    public void onCompleteData(Base base, int whichAPI) {
        super.onCompleteData(base, whichAPI);
        closeLoadingDialog();
        switch (whichAPI) {
            case API.whichAPI.login:
                if (base.getCode().equals("true")) {
                    List<UserInfo> list = (List<UserInfo>) base.getData();
                    SharedPreferences.Editor sharedata = getSharedPreferences("userinfo", 0).edit();
                    sharedata.putInt("id", list.get(0).getId());
                    sharedata.putString("password", list.get(0).getPassword());
                    sharedata.putString("username", list.get(0).getUsername());
                    sharedata.apply();
                    goActivity(MainActivity.class);
                } else {
                    initReturnBack(base.getMsg());
                }
        }
    }

}
