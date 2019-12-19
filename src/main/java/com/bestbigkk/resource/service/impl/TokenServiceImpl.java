package com.bestbigkk.resource.service.impl;

import com.bestbigkk.resource.config.Config;
import com.bestbigkk.resource.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
*  @author: xugongkai
*  @data: 2019-12-19 13:49:53
*  @describe: 令牌服务，防止恶意的向DFS上传文件，作为一个简单的身份验证。
**/
@Service
public class TokenServiceImpl implements TokenService {

    private final Config config;

    public TokenServiceImpl(Config config) {
        this.config = config;
    }

    @Override
    public boolean useToken(String token) {
        ConcurrentHashMap<String, Integer> tokens = config.getToken();
        boolean allowToken = token != null && tokens != null && tokens.containsKey(token) && tokens.get(token) != 0;
        if (allowToken) {
            tokens.put(token, tokens.get(token)-1);
        }
        return allowToken;
    }
}
