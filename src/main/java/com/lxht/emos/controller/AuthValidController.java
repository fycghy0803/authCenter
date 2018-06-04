package com.lxht.emos.controller;
import com.lxht.emos.bean.ReturnBean;
import com.lxht.emos.bean.UserBean;
import com.lxht.emos.bean.UserRolesBean;
import com.lxht.emos.bean.UserSessionBean;
import com.lxht.emos.event.UserModifiedPublisher;
import com.lxht.emos.exception.AuthException;
import com.lxht.emos.service.MenuService;
import com.lxht.emos.service.UserService;
import com.lxht.emos.utils.ConstantVal;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.WebAttributes;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fanyuli on 2018/5/11.
 */
@RestController
public class AuthValidController {
    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public UserBean authCenterHome() {
        User userSessionBean = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserBean userBean = new UserBean();
        userBean.setLoginName(userSessionBean.getUsername());
        UserBean userInfo = userService.getUserInfo(userBean);
        return userInfo;
    }

    @RequestMapping("/userLogin")
    public UserBean userLogin(HttpServletRequest request) throws Exception{

        UserBean userInfo = new UserBean();
        String username = "";

        String lb = request.getParameter("error");

        if("T".equals(lb)) {
            Exception exception = (Exception)request.getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
            if(exception != null) {
                throw new AuthException(exception.getMessage());
            } else {
                String errMsg = "用户名或密码错误!";
                throw new AuthException(errMsg);
            }
        } else {
            User userSessionBean = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            UserBean userBean = new UserBean();
            userBean.setLoginName(userSessionBean.getUsername());
        }

        userInfo.setLoginName(username);
        return userInfo;
    }

    @RequestMapping("/userAuth")
    public UserBean userAuth() {
        User userSessionBean = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserBean userBean = new UserBean();
        userBean.setLoginName(userSessionBean.getUsername());
        UserBean userInfo = userService.getUserInfo(userBean);
        return userInfo;
    }

    @RequestMapping("/applyCheckCode")
    public ReturnBean applyCheckCode(HttpServletRequest request) {
        ReturnBean returnBean = new ReturnBean();
        request.getSession().setAttribute("CHECK_CODE","1111");
        returnBean.setRetCode("1");
        return returnBean;
    }
}
