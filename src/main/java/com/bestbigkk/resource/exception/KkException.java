package com.bestbigkk.resource.exception;

/**
*  @author: xugongkai
*  @data: 2019-12-19 11:57:10
*  @describe: 异常
**/
public class KkException extends RuntimeException {

    public KkException(String message) {
        super(message);
    }

    public KkException(String message, Throwable cause) {
        super(message, cause);
    }
}
