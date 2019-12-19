package com.bestbigkk.resource.service.impl;

import com.bestbigkk.resource.annotation.CheckToken;
import com.bestbigkk.resource.config.Config;
import com.bestbigkk.resource.entity.RespBO;
import com.bestbigkk.resource.exception.KkException;
import com.bestbigkk.resource.service.DfsService;
import com.bestbigkk.resource.service.TokenService;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.upload.ThumbImage;
import com.github.tobato.fastdfs.service.DefaultFastFileStorageClient;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Objects;

/**
 * @author: xugongkai
 * @data: 2019-12-19 11:40:27
 * @describe: 上传实现。
 **/
@Service
public class DfsServiceImpl implements DfsService {

    private final DefaultFastFileStorageClient storageClient;
    private final ThumbImage thumbImage;
    private final TokenService tokenService;
    private final Config config;

    public DfsServiceImpl(DefaultFastFileStorageClient storageClient, ThumbImage thumbImage, TokenService tokenService, Config config) {
        this.storageClient = storageClient;
        this.thumbImage = thumbImage;
        this.tokenService = tokenService;
        this.config = config;
    }

    @Override
    @CheckToken
    public RespBO upload(MultipartFile multipartFile, String token) {
        if (Objects.isNull(multipartFile)) {
            throw new KkException(CAN_NOT_FOUND_FILE_TO_UPLOAD);
        }

        InputStream inputStream;
        try {
            inputStream = multipartFile.getInputStream();
        } catch (IOException e) {
            throw new KkException(CAN_NOT_FOUND_FILE_TO_UPLOAD, e);
        }

        String originalFilename = multipartFile.getOriginalFilename();
        if (Objects.isNull(originalFilename) || !originalFilename.matches(FILE_FORMAT)) {
            throw new KkException(INVALID_FILE_FORMAT);
        }
        String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        StorePath storePath;
        try {
             storePath = storageClient.uploadFile(inputStream, multipartFile.getSize(), suffix, null);
        } catch (Exception e) {
            throw new KkException(ERROR,e);
        }

        String path = config.getDomain() + storePath.getFullPath();

        RespBO bo = new RespBO();
        bo.setStatus(true);
        bo.setMsg(SUCCESS);
        bo.setData(new HashMap<String, String>(1){{
            put("url", path);
        }});
        return bo;
    }
}
