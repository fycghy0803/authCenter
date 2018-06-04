package com.lxht.emos.mapper.sqlprovider;

import com.lxht.emos.bean.UserBean;

/**
 * Created by fanyuli on 2018/5/14.
 */
public class UserSqlProvider {
    public String getUserInfoSql(UserBean userBean) {
       StringBuilder sql = new StringBuilder("select * from users where ");
       if(userBean.getUserId() != null) {
           sql.append("user_id = #{userId}");
       } else {
           sql.append("login_name = #{loginName}");
       }
       return sql.toString();
    }
}
