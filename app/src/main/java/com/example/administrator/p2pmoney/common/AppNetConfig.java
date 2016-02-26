package com.example.administrator.p2pmoney.common;

/**
 * Created by Administrator on 2016/2/24.
 */
public class AppNetConfig {
    //请求主机的地址
    public static final String HOST = "192.168.56.1";
    public static final String BASEURL = "http://" + HOST + ":8080/P2PInvest/";
    //接口url地址
    //首页
    public static final String INDEX = BASEURL + "index";
    //登录
    public static final String LOGIN = BASEURL + "login";
    //产品列表
    public static final String PRODUCT = BASEURL + "product";

}
