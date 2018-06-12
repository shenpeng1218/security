package com.moss.social.qq.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.io.IOException;

public class QQApiImpl extends AbstractOAuth2ApiBinding implements QQApi {

    private static final String URL_GET_OPENID =
            "https://graph.qq.com/oauth2.0/me?access_token=YOUR_ACCESS_TOKEN=%s";

    private static final String URL_GET_USERINFO =
            "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";

    private String appId;

    private String openId;

    private ObjectMapper objectMapper = new ObjectMapper();

    public QQApiImpl(String accessToken, String appId){
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.appId = appId;

        String url = String.format(URL_GET_OPENID, accessToken);
        String result = getRestTemplate().getForObject(url, String.class);

        this.openId = StringUtils.substringBetween(result,"\"openId\":", "}");
    }

    @Override
    public QQUserInfo getUserInfo(){
        String url = String.format(URL_GET_USERINFO, appId, openId);
        String result = getRestTemplate().getForObject(url, String.class);
        try{
            return objectMapper.readValue(result, QQUserInfo.class);
        }catch (Exception e){
            throw new RuntimeException("获取QQ用户信息失败！");
        }
    }
}
