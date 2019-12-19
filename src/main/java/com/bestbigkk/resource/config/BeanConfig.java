package com.bestbigkk.resource.config;

import com.github.tobato.fastdfs.domain.upload.ThumbImage;
import com.github.tobato.fastdfs.service.DefaultFastFileStorageClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.HashMap;

/**
*  @author: xugongkai
*  @data: 2019-12-19 11:55:22
*  @describe: 配置
**/

@Configuration
public class BeanConfig {
    @Bean
    @Primary
    public HashMap<String, String> fun0() {
        return new HashMap<>(16);
    }

    @Bean
    @Primary
    public ThumbImage fun1() {
        return new ThumbImage();
    }

    @Bean
    @Primary
    public DefaultFastFileStorageClient fun2() {
        return new DefaultFastFileStorageClient();
    }

}
