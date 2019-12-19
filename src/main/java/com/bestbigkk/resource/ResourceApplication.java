package com.bestbigkk.resource;

import com.bestbigkk.resource.config.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


/**
*  @author: xugongkai
*  @data: 2019-12-19 11:44:44
*  @describe:
 *  为了方便自己快速向DFS上传资源，简单写了个网页。
 *  自己以后可以很方便的上传并获取到URL。主要还是有道云笔记
 *  非会员无法上传图片，有需求才有创造。以后QQ空间免费背景音乐也可以直接使用这个DFS返回的地址。挺好。
**/

@EnableAspectJAutoProxy
@SpringBootApplication
@EnableConfigurationProperties({Config.class})
public class ResourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResourceApplication.class, args);
	}

}
