package com.moss.social;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;

@Component
public class MossConnectionSignUp implements ConnectionSignUp{

    @Override
    public String execute(Connection<?> connection) {
        //根据用户社交信息，返回用户唯一标识
        return connection.getDisplayName() + connection.getImageUrl();
    }
}
