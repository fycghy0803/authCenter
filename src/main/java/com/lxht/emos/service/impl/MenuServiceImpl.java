package com.lxht.emos.service.impl;

import com.lxht.emos.bean.MenuBean;
import com.lxht.emos.mapper.MenuMapper;
import com.lxht.emos.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;


/**
 * Created by fanyuli on 2018/5/21.
 */
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuMapper menuMapper;

    @Cacheable(value = "menu",key="#menuBean.menuUrl")
    public MenuBean getMenuBeanByUrl(MenuBean menuBean) {
        MenuBean ret = menuMapper.getMenuBeanByUrl(menuBean);
        return ret;
    }

    @Override
    public List<MenuBean> getMenus() {
        return menuMapper.getMenus();
    }

    @Override
    public void flushMenus() throws Exception {

    }
}
