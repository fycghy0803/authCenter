package com.lxht.emos.mapper;

import com.lxht.emos.bean.UserRolesBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by fanyuli on 2018/5/21.
 */
@Mapper
public interface UserRolesMapper {
    @Select("select user_id,role_id from user_roles where user_id = #{userId}")
    @Results(value = {@Result(property = "userId",column = "user_id"),
                      @Result(property = "roleId",column = "role_id"),
                      })
    public List<UserRolesBean> getRolesBeanById(UserRolesBean userRolesBean);
}
