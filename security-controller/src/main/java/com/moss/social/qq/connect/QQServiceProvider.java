package com.moss.social.qq.connect;

import com.moss.social.qq.api.QQApi;
import com.moss.social.qq.api.QQApiImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Template;

public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQApi>{

    private String appId;

    private static final String URL_AUTHORIZE =
            "https://graph.qq.com/oauth2.0/authorize";

    private static final String URL_ACEESE_TOKEN =
            "https://graph.qq.com/oauth2.0/token";

    public QQServiceProvider(String appId, String appSecret) {
        super(new QQOauth2Template(appId, appSecret, URL_AUTHORIZE, URL_ACEESE_TOKEN));
        this.appId = appId;
    }

    @Override
    public QQApi getApi(String accessToken) {
        return new QQApiImpl(accessToken, appId);
    }
}
