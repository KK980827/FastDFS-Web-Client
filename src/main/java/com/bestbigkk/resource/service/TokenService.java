package com.bestbigkk.resource.service;

/**
*  @author: xugongkai
*  @data: 2019-12-19 13:48:34
*  @describe: 令牌
**/
public interface TokenService {

    /**
     * token合法性
     * @param token
     * @return
     */
    boolean useToken(String token);

}
