package com.lxht.emos.mapper;

import com.lxht.emos.bean.UserBean;
import com.lxht.emos.mapper.sqlprovider.UserSqlProvider;
import org.apache.ibatis.annotations.*;

/**
 * Created by fanyuli on 2018/5/14.
 */
@Mapper
public interface UserMapper {

    @SelectProvider(type = UserSqlProvider.class,method = "getUserInfoSql")
    @Results(id="userInfoMap",value={
        @Result(property = "loginName",column = "login_name"),
            @Result(property = "userId",column = "user_id"),
            @Result(property = "userPwd",column = "login_pwd")
    })
    public UserBean getUserInfo(UserBean userBean);
}
