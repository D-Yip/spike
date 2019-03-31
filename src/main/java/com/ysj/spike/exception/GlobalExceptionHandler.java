package com.ysj.spike.exception;

import com.ysj.spike.base.CodeMsg;
import com.ysj.spike.base.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.net.BindException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Result<String> exceptionHandler(HttpServletRequest request, Exception e) {
        if (e instanceof GlobalException) {
            GlobalException ex = (GlobalException) e;
            return Result.error(ex.getCodeMsg());
        } else if (e instanceof BindException) {
            BindException ex = (BindException) e;
            String msg = ex.getMessage();
            return Result.error(CodeMsg.BIND_ERROR.fillArgs(msg));
        } else {
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }
}
