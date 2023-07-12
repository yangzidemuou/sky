package com.sky.handler;

import com.sky.constant.MessageConstant;
import com.sky.exception.BusinessException;
import com.sky.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.config.MessageConstraints;
import org.springframework.boot.autoconfigure.sql.init.SqlInitializationAutoConfiguration;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    //要处理的异常
    @ExceptionHandler(BusinessException.class)
    public Result handlerBusinessException(BusinessException e){
        //1. 记录日志-开发看
        log.error("业务出现自定义异常：{}",e.getMessage());
        //2. 返回友好提示-用户看
        return Result.error(e.getCode(),e.getMessage());
    }

    @ExceptionHandler
    public Result exceptionHandler(SQLIntegrityConstraintViolationException ex){
        String message = ex.getMessage();
        if(message.contains("Duplicate entry")){
            String[] s=message.split(" ");
            String username=s[2];
            String msg=username+ MessageConstant.ALREADY_EXISTS;
            return Result.error(msg);
        }else {
            return Result.error(MessageConstant.UNKNOWN_ERROR);
        }
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public Result handlerDuplicateKeyException(DuplicateKeyException e){
        //1. 记录日志-开发看
        log.error("业务出现自定义异常：{}",e.getMessage());
        //2. 返回友好提示-用户看
        return Result.error(500,"名称重复，请修改");
    }

    //兜底异常：其他未处理，走这里处理
    @ExceptionHandler(Exception.class)
    public Result handlerException(Exception e){
        e.printStackTrace();
        //1. 记录日志-开发看
        log.error("程序出现未知异常：{}",e);
        //2. 返回友好提示-用户看
        return Result.error(500,"服务器异常，请联系管理员");
    }
}
