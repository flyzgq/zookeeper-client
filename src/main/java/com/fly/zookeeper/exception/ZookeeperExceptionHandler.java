package com.fly.zookeeper.exception;

import org.apache.zookeeper.KeeperException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ZookeeperExceptionHandler {

    @ExceptionHandler(value = {InterruptedException.class, KeeperException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public GloablErrorResponse zookeeperException(Exception exception) {
        return new GloablErrorResponse("zookeeper服务出错", "zookeeper抛出" + exception.getMessage());
    }
}
