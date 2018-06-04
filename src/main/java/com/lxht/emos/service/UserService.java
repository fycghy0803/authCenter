package com.lxht.emos.service;

import com.lxht.emos.bean.UserBean;
import com.lxht.emos.bean.UserRolesBean;

import java.util.List;

/**
 * Created by fanyuli on 2018/5/15.
 */
public interface UserService  {
     UserBean getUserInfo(UserBean userBean);
     List<UserRolesBean> getRolesBeanById(UserRolesBean userRolesBean);
}
