package com.demo.androidtranslateapp.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.baseClass.BaseActivity;
import com.demo.androidtranslateapp.R;
import com.demo.androidtranslateapp.fragment.ChatFragment;
import com.demo.androidtranslateapp.fragment.HomeFragment;
import com.demo.androidtranslateapp.fragment.MyFragment;
import com.demo.androidtranslateapp.fragment.WordsbookFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    ChatFragment chatFragment;
    HomeFragment homeFragment;
    MyFragment myFragment;
    WordsbookFragment wordsbookFragment;
    @BindView(R.id.main_layout)
    FrameLayout mainLayout;
    @BindView(R.id.img_home_mainActivity)
    ImageView imgHomeMainActivity;
    @BindView(R.id.tv_home_mainActivity)
    TextView tvHomeMainActivity;
    @BindView(R.id.linear_home_mainActivity)
    LinearLayout linearHomeMainActivity;
    @BindView(R.id.img_school_mainActivity)
    ImageView imgSchoolMainActivity;
    @BindView(R.id.tv_school_mainActivity)
    TextView tvSchoolMainActivity;
    @BindView(R.id.linear_school_mainActivity)
    LinearLayout linearSchoolMainActivity;
    @BindView(R.id.img_share_mainActivity)
    ImageView imgShareMainActivity;
    @BindView(R.id.tv_share_mainActivity)
    TextView tvShareMainActivity;
    @BindView(R.id.linear_share_mainActivity)
    LinearLayout linearShareMainActivity;
    @BindView(R.id.img_my_mainActivity)
    ImageView imgMyMainActivity;
    @BindView(R.id.tv_my_mainActivity)
    TextView tvMyMainActivity;
    @BindView(R.id.linear_my_mainActivity)
    LinearLayout linearMyMainActivity;
    private Fragment[] fgtArr;
    private int prePosition = -1;

    Context context;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        context=this;
        fgtArr = new Fragment[4];
        homeFragment=new HomeFragment();
        chatFragment=new ChatFragment();
        wordsbookFragment=new WordsbookFragment();
        myFragment=new MyFragment();
        fgtArr[0]=homeFragment;
        fgtArr[1]=wordsbookFragment;
        fgtArr[2]=chatFragment;
        fgtArr[3]=myFragment;
        changeFgt(0);

    }
    private void setView() {
        tvHomeMainActivity.setTextColor(ContextCompat.getColor(context, R.color.text));
        tvSchoolMainActivity.setTextColor(ContextCompat.getColor(context, R.color.text));
        tvShareMainActivity.setTextColor(ContextCompat.getColor(context, R.color.text));
        tvMyMainActivity.setTextColor(ContextCompat.getColor(context, R.color.text));
        imgHomeMainActivity.setImageResource(R.mipmap.home_h);
        imgSchoolMainActivity.setImageResource(R.mipmap.school_h);
        imgShareMainActivity.setImageResource(R.mipmap.share_h);
        imgMyMainActivity.setImageResource(R.mipmap.my_h);

    }
    public void changeFgt(int curPosition) {
        if (curPosition == prePosition) {
            return;
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();


        if (prePosition != -1)
            transaction.hide(fgtArr[prePosition]);

        if (fgtArr[curPosition].isAdded()) {
            transaction.show(fgtArr[curPosition]);
        } else {
            transaction.add(R.id.main_layout, fgtArr[curPosition]).show(fgtArr[curPosition]);
        }
        transaction.commit();
        prePosition = curPosition;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
    public void setNavigationChange(ImageView img1, int icon_home_pre, TextView tv1, int themeColor) {
        setView();
        img1.setImageResource(icon_home_pre);
        tv1.setTextColor(ContextCompat.getColor(context, themeColor));
    }

    @OnClick({R.id.linear_home_mainActivity, R.id.linear_school_mainActivity, R.id.linear_share_mainActivity, R.id.linear_my_mainActivity})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.linear_home_mainActivity:
                setNavigationChange(imgHomeMainActivity, R.mipmap.home, tvHomeMainActivity, R.color.appThemeColor);
                changeFgt(0);
                break;
            case R.id.linear_school_mainActivity:
                setNavigationChange(imgSchoolMainActivity, R.mipmap.school, tvSchoolMainActivity, R.color.appThemeColor);
                changeFgt(1);
                break;
            case R.id.linear_share_mainActivity:
                setNavigationChange(imgShareMainActivity, R.mipmap.share, tvShareMainActivity, R.color.appThemeColor);
                changeFgt(2);
                break;
            case R.id.linear_my_mainActivity:
                setNavigationChange(imgMyMainActivity, R.mipmap.my, tvMyMainActivity, R.color.appThemeColor);
                changeFgt(3);
                break;
        }
    }
}
