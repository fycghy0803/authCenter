package com.lxht.emos.service;

import com.lxht.emos.bean.MenuBean;

import java.util.List;


/**
 * Created by fanyuli on 2018/5/21.
 */
public interface MenuService {
    MenuBean getMenuBeanByUrl(MenuBean menuBean);
    List<MenuBean> getMenus();
    void flushMenus() throws Exception;
}
