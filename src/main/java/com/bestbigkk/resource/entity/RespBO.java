package com.bestbigkk.resource.entity;

import lombok.Builder;
import lombok.Data;

import java.util.HashMap;

/**
*  @author: xugongkai
*  @data: 2019-12-19 11:49:39
*  @describe: 响应对象
**/
@Data
public class RespBO {
    private Boolean status;
    private String msg;
    private HashMap<String, String> data;

}
