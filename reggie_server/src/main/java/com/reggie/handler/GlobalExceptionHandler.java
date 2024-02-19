package com.reggie.handler;

import com.reggie.exception.BaseException;
import com.reggie.result.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author: ChenXW
 * @Date:2024/2/19 14:53
 * @Description: 全局异常处理器
 **/

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    // 捕获业务异常
    @ExceptionHandler
    public R exceptionHandler(BaseException ex) {
        log.error("异常信息:{}", ex.getMessage());
        return R.error(ex.getMessage());
    }

}
