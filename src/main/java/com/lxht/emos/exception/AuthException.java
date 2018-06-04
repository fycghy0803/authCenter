package com.lxht.emos.exception;

import com.lxht.emos.bean.BaseBean;
import com.lxht.emos.bean.ReturnBean;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by fanyuli on 2018/5/14.
 */
public class AuthException extends Exception{
    private String msg;
    public AuthException(String msg) {
        super(msg);
        this.msg = msg;
    }
}
