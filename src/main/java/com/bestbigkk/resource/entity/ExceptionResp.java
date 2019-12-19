package com.bestbigkk.resource.entity;

import com.bestbigkk.resource.config.Config;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashMap;

/**
*  @author: xugongkai
*  @data: 2019-12-19 13:40:14
*  @describe: 异常返回对象
**/

@EqualsAndHashCode(callSuper = true)
@Data
public class ExceptionResp extends RespBO {
    private String error;
    private StackTraceElement[] detail;

    public void setDetail(StackTraceElement[] detail) {
        if (Config.includeStackTrace) {
            this.detail = detail;
        }
    }
}
