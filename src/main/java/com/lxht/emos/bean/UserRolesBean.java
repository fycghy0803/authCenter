package com.lxht.emos.bean;

/**
 * Created by fanyuli on 2018/5/21.
 */
public class UserRolesBean extends BaseBean{
    private Integer userId;
    private Integer roleId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
