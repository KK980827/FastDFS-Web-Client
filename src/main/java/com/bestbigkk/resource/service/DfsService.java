package com.bestbigkk.resource.service;

import com.bestbigkk.resource.entity.RespBO;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
*  @author: xugongkai
*  @data: 2019-12-19 11:51:30
*  @describe: 接口
**/
public interface DfsService {

    String FILE_FORMAT = "^.+\\..+$";
    String CAN_NOT_FOUND_FILE_TO_UPLOAD = "未发现要上传的文件！";
    String INVALID_FILE_FORMAT = "无效的文件格式！";
    String ERROR = "操作错误！";
    String SUCCESS = "操作成功！";

    /**
     * 上传文件
     * @param multipartFile 上传对象
     * @param token 令牌
     * @return 结果
     */
    RespBO upload(MultipartFile multipartFile, String token);

}
