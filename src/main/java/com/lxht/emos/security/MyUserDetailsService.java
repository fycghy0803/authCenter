package com.lxht.emos.security;

import com.lxht.emos.bean.UserBean;
import com.lxht.emos.bean.UserRolesBean;
import com.lxht.emos.bean.UserSessionBean;
import com.lxht.emos.service.UserService;
import com.lxht.emos.utils.ConstantVal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.AutoPopulatingList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by fanyuli on 2018/5/28.
 */
//@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        ArrayList<SimpleGrantedAuthority> authorities = new ArrayList();
        UserBean userBean = new UserBean();
        userBean.setLoginName(s);
        UserBean userInfo = userService.getUserInfo(userBean);
        if(userInfo == null) {
            throw new UsernameNotFoundException("username is not exist.");
        }

        UserRolesBean userRolesBean = new UserRolesBean();
        userRolesBean.setUserId(userInfo.getUserId());
        List<UserRolesBean> rolesBeanList =  userService.getRolesBeanById(userRolesBean);
        for(UserRolesBean tmpRoleBean : rolesBeanList) {
            authorities.add(new SimpleGrantedAuthority(ConstantVal.ROLE_PREFIX + tmpRoleBean.getRoleId().toString()));
        }

        User userDetails = new User(userInfo.getLoginName(),userInfo.getUserPwd(),authorities);
        return userDetails;
    }
}

