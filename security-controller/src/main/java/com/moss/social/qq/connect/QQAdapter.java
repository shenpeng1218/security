package com.moss.social.qq.connect;

import com.moss.social.qq.api.QQApi;
import com.moss.social.qq.api.QQUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

import java.io.IOException;

public class QQAdapter implements ApiAdapter<QQApi>{

    @Override
    public boolean test(QQApi qqApi) {
        return true;
    }

    @Override
    public void setConnectionValues(QQApi qqApi, ConnectionValues connectionValues) {
        try {
            QQUserInfo qqUserInfo = qqApi.getUserInfo();

            connectionValues.setDisplayName(qqUserInfo.getNickname());
            connectionValues.setImageUrl(qqUserInfo.getFigureurl_qq_1());
            connectionValues.setProfileUrl(null);
            connectionValues.setProviderUserId(qqUserInfo.getOpenId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public UserProfile fetchUserProfile(QQApi qqApi) {
        return null;
    }

    @Override
    public void updateStatus(QQApi qqApi, String s) {
        //QQ do nothing
    }
}
