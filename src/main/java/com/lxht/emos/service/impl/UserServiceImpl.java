package com.lxht.emos.service.impl;

import com.lxht.emos.bean.UserBean;
import com.lxht.emos.bean.UserRolesBean;
import com.lxht.emos.mapper.MenuMapper;
import com.lxht.emos.mapper.UserMapper;
import com.lxht.emos.mapper.UserRolesMapper;
import com.lxht.emos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fanyuli on 2018/5/14.
 */
@Service
@CacheConfig(cacheNames = "authCache")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRolesMapper userRolesMapper;

    @Cacheable(key = "#root.target.getUserInfoKey(#userBean)")
    public UserBean getUserInfo(UserBean userBean) {
        UserBean userInfo = userMapper.getUserInfo(userBean);
        return userInfo;
    }

    public String getUserInfoKey(UserBean userBean) {
        String key = "";
        if(userBean.getUserId() != null) {
            key = userBean.getUserId().toString();
        } else {
            key = userBean.getLoginName();
        }
        return key;
    }

    @Cacheable(value = "role",key="#userRolesBean.userId")
    public List<UserRolesBean> getRolesBeanById(UserRolesBean userRolesBean){
        List<UserRolesBean> userRolesBeanList = userRolesMapper.getRolesBeanById(userRolesBean);
        return userRolesBeanList;
    }
}
