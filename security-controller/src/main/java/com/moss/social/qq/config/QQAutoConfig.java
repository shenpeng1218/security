package com.moss.social.qq.config;

import com.moss.social.qq.connect.QQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

@Configuration
@ConditionalOnProperty( prefix = "spring.social.qq", name = "app-id")
public class QQAutoConfig extends SocialAutoConfigurerAdapter{

    @Value("${spring.social.qq.app-id}")
    private String appId;

    @Value("${spring.social.qq.app-secret}")
    private String appSecret;

    @Value("${spring.social.qq.provider-id}")
    private String prociderId;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        return new QQConnectionFactory(prociderId, appId, appSecret);
    }
}
