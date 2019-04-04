package com.demo.androidtranslateapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.base.baseClass.BaseActivity;
import com.base.model.Base;
import com.base.util.Tool;
import com.demo.androidtranslateapp.R;
import com.demo.androidtranslateapp.net.API;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity {
    @BindView(R.id.et_username_registerActivity)
    EditText etUsernameRegisterActivity;
    @BindView(R.id.et_psw1_registerActivity)
    EditText etPsw1RegisterActivity;
    @BindView(R.id.et_psw2_registerActivity)
    EditText etPsw2RegisterActivity;
    @BindView(R.id.bt_next_registerActivity)
    Button btNextRegisterActivity;
    @BindView(R.id.bt_gologin_registerActivity)
    Button btGologinRegisterActivity;
    String username;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {

    }
    private void register() {
         username = Tool.getTextViewContent(etUsernameRegisterActivity);
        String password = Tool.getTextViewContent(etPsw1RegisterActivity);
        String password2 = Tool.getTextViewContent(etPsw2RegisterActivity);

        if (password == null) {
            initReturnBack("密码不能为空");
            return;
        }
        if (password2 == null || !password2.equals(password)) {
            initReturnBack("两次密码不一致");
            return;
        }
        new API(this, Base.getClassType()).register(username, password);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_next_registerActivity, R.id.bt_gologin_registerActivity})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_next_registerActivity:
                register();
                break;
            case R.id.bt_gologin_registerActivity:
                goActivity(LoginActivity.class);
                break;
        }
    }


    @Override
    public void onCompleteData(Base base, int whichAPI) {
        super.onCompleteData(base, whichAPI);
        closeLoadingDialog();
        switch (whichAPI) {
            case API.whichAPI.register:
                if (base.getCode().equals("true")) {
                    Intent intent = new Intent(this,LoginActivity.class);
                    intent.putExtra("username",username);
                    startActivity(intent);
                } else {
                    initReturnBack("用户名不可用请重新输入");
                }
        }

    }
}
