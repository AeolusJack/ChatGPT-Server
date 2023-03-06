package com.erget.chatgpt.user.constant;

public class UserConstant {
    /*
     * 微信获取code base url
     */
    public final static String WECHAT_CODE_BASE_URL = "https://open.weixin.qq.com/connect/qrconnect";

    /*
     * 微信获取access_token base url
     */
    public final static String WECHAT_ACCESS_TOKEN_BASE_URL = "https://api.weixin.qq.com/sns/oauth2/access_token";

    /**
     * 获取userinfo base url
     */
    public final static String WECHAT_USERINFO_BASE_URL = "https://api.weixin.qq.com/sns/userinfo";

    /**
     * 微信扫码 重定向地址
     */
    public final static String WECHAT_REDIRECT_URL = "/tmy3-usercenter/scanLogin/WeChat";

    /**
     * 微信扫码 重定向地址
     */
    public final static String DINGTALK_REDIRECT_URL = "/tmy3-usercenter/scanLogin/DingTalk";



    public final static String DINGTALK_CODE_BASE_URL = "https://login.dingtalk.com/oauth2/auth";

    public final static String DEFAULT_APP_KEY_ERGET = "ERGET";


}
