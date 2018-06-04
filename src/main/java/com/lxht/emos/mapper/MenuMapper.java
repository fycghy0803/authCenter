package com.lxht.emos.mapper;

import com.lxht.emos.bean.MenuBean;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by fanyuli on 2018/5/21.
 */
@Mapper
public interface MenuMapper {
    @Select("select menu_id,menu_name,menu_url,role_ids from sys_menus where menu_url = #{menuUrl}")
    @Results(id = "menu",
            value = {
                    @Result(column = "menu_id",property = "menuId"),
                    @Result(column = "menu_name",property = "menuName"),
                    @Result(column = "menu_url",property = "menuUrl"),
                    @Result(column = "role_ids",property = "roleIds")
            }
    )
    public MenuBean getMenuBeanByUrl(MenuBean menuBean);

    @Select("select menu_id,menu_name,menu_url,role_ids from sys_menus")
    @ResultMap("menu")
    public List<MenuBean> getMenus();

}
