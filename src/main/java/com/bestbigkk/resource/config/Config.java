package com.bestbigkk.resource.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.concurrent.ConcurrentHashMap;

/**
*  @author: xugongkai
*  @data: 2019-12-19 11:43:59
*  @describe: 配置类
**/
@Data
@Configuration
@ConfigurationProperties(prefix = "bestbigkk", ignoreInvalidFields = true)
public class Config {

    /** 是否在错误响应对象里面包含堆栈信息*/
    public static Boolean includeStackTrace;
    public void setIncludeStackTrace(Boolean includeStackTrace) {
        Config.includeStackTrace = includeStackTrace;
    }

    /** 可用令牌 */
    private ConcurrentHashMap<String, Integer> token;

    /** 域名*/
    private String domain;

}
