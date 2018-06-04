package com.lxht.emos.event;

import com.lxht.emos.bean.UserBean;
import org.springframework.context.ApplicationEvent;

/**
 * Created by fanyuli on 2018/5/8.
 */
public class UserModifiedEvent extends ApplicationEvent {
    private UserBean userBean;

    public UserModifiedEvent(Object source, UserBean userBean) {
        super(source);
        this.userBean = userBean;
    }

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }
}
