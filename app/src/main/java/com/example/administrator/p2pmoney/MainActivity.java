package com.example.administrator.p2pmoney;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.p2pmoney.common.AppManager;
import com.example.administrator.p2pmoney.fragment.HomeFragment;
import com.example.administrator.p2pmoney.fragment.MeFragment;
import com.example.administrator.p2pmoney.fragment.MoreFragment;
import com.example.administrator.p2pmoney.fragment.TouziFragment;
import com.example.administrator.p2pmoney.util.UiUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends FragmentActivity {

    @InjectView(R.id.content)
    FrameLayout content;
    @InjectView(R.id.iv_home)
    ImageView ivHome;
    @InjectView(R.id.tv_home)
    TextView tvHome;
    @InjectView(R.id.ll_home)
    LinearLayout llHome;
    @InjectView(R.id.iv_touzi)
    ImageView ivTouzi;
    @InjectView(R.id.tv_touzi)
    TextView tvTouzi;
    @InjectView(R.id.ll_touzi)
    LinearLayout llTouzi;
    @InjectView(R.id.iv_me)
    ImageView ivMe;
    @InjectView(R.id.tv_me)
    TextView tvMe;
    @InjectView(R.id.ll_me)
    LinearLayout llMe;
    @InjectView(R.id.iv_more)
    ImageView ivMore;
    @InjectView(R.id.tv_more)
    TextView tvMore;
    @InjectView(R.id.ll_more)
    LinearLayout llMore;
    private FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        //开启AppManager管理
        AppManager.getInstance().addActivity(this);
        initData();
    }

    private void initData() {//初始化默认进入进入第一个界面
//        String str = null;
//        if (str.equals("")){
//
//        }
        setSelect(0);
    }

    @OnClick({R.id.ll_home, R.id.ll_me, R.id.ll_more, R.id.ll_touzi})
    public void changeTab(View view) {
        switch (view.getId()) {
            case R.id.ll_home:
                setSelect(0);
                ivHome.setImageResource(R.mipmap.bid01);
                tvHome.setTextColor(getResources().getColor(R.color.home_back_selected));
                break;
            case R.id.ll_touzi:
                setSelect(1);
                ivTouzi.setImageResource(R.mipmap.bid03);
                tvTouzi.setTextColor(getResources().getColor(R.color.home_back_selected));
                break;
            case R.id.ll_me:
                setSelect(2);
                ivMe.setImageResource(R.mipmap.bid05);
                tvMe.setTextColor(getResources().getColor(R.color.home_back_selected));
                break;
            case R.id.ll_more:
                setSelect(3);
                ivMore.setImageResource(R.mipmap.bid07);
                tvMore.setTextColor(getResources().getColor(R.color.home_back_selected));
                break;
        }
    }

    Fragment homeFragment = null;
    Fragment touziFragment = null;
    Fragment meFragment = null;
    Fragment moreFragment = null;


    private void setSelect(int i) {
        FragmentManager fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        hideFragment();//隐藏fragment
        resetTab();//改变选中的颜色和图片
        switch (i) {
            case 0://首页
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    ft.add(R.id.content, homeFragment);
                }
                ivHome.setImageResource(R.mipmap.bid01);
                tvHome.setTextColor(UiUtils.getColor(R.color.home_back_selected));
                //       tvHome.setTextColor(getResources().getColor(R.color.home_back_selected));
                ft.show(homeFragment);
                break;
            case 1://我要投资
                if (touziFragment == null) {
                    touziFragment = new TouziFragment();
                    ft.add(R.id.content, touziFragment);
                }
                ivTouzi.setImageResource(R.mipmap.bid03);
                tvTouzi.setTextColor(UiUtils.getColor(R.color.home_back_selected));
                //     tvTouzi.setTextColor(getResources().getColor(R.color.home_back_selected));
                ft.show(touziFragment);
                break;
            case 2://我的资产
                if (meFragment == null) {
                    meFragment = new MeFragment();
                    ft.add(R.id.content, meFragment);

                }
                ivMe.setImageResource(R.mipmap.bid05);
                tvMe.setTextColor(UiUtils.getColor(R.color.home_back_selected));
                //           tvMe.setTextColor(getResources().getColor(R.color.home_back_selected));
                ft.show(meFragment);
                break;
            case 3://更多
                if (moreFragment == null) {
                    moreFragment = new MoreFragment();
                    ft.add(R.id.content, moreFragment);
                }
                ivMore.setImageResource(R.mipmap.bid07);
                tvMore.setTextColor(UiUtils.getColor(R.color.home_back_selected));
//                tvMore.setTextColor(getResources().getColor(R.color.home_back_selected));
                ft.show(moreFragment);
                break;

        }
        ft.commit();
    }


    private void hideFragment() {//把已經有的都隱藏掉
        if (homeFragment != null) {
            ft.hide(homeFragment);
        }
        if (touziFragment != null) {
            ft.hide(touziFragment);
        }
        if (meFragment != null) {
            ft.hide(meFragment);
        }
        if (moreFragment != null) {
            ft.hide(moreFragment);
        }
    }

    private void resetTab() {
        ivHome.setImageResource(R.mipmap.bid02);
        ivTouzi.setImageResource(R.mipmap.bid04);
        ivMe.setImageResource(R.mipmap.bid06);
        ivMore.setImageResource(R.mipmap.bid08);
        /*tvHome.setTextColor(getResources().getColor(R.color.home_back_unselected));
        tvTouzi.setTextColor(getResources().getColor(R.color.home_back_unselected));
        tvMe.setTextColor(getResources().getColor(R.color.home_back_unselected));
        tvMore.setTextColor(getResources().getColor(R.color.home_back_unselected));*/
        tvHome.setTextColor(UiUtils.getColor(R.color.home_back_unselected));
        tvMe.setTextColor(UiUtils.getColor(R.color.home_back_unselected));
        tvMore.setTextColor(UiUtils.getColor(R.color.home_back_unselected));
        tvTouzi.setTextColor(UiUtils.getColor(R.color.home_back_unselected));

    }


}
