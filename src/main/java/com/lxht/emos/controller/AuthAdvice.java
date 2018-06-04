package com.lxht.emos.controller;

import com.lxht.emos.bean.ReturnBean;
import com.lxht.emos.exception.AuthException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by fanyuli on 2018/5/14.
 */
@ControllerAdvice
public class AuthAdvice {
    @ExceptionHandler(AuthException.class)
    @ResponseBody
    public ReturnBean handleAuthException(final AuthException ex) {
        ReturnBean rt = new ReturnBean();
        rt.setRetCode("-1");
        rt.setRetMsg(ex.getMessage());
        return rt;
    }
}
