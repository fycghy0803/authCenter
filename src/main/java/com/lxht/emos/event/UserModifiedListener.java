package com.lxht.emos.event;

import com.lxht.emos.bean.UserBean;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Created by fanyuli on 2018/5/8.
 */
@Component
public class UserModifiedListener implements ApplicationListener<UserModifiedEvent> {
    @Override
    public void onApplicationEvent(UserModifiedEvent userModifiedEvent) {
        UserBean userBean = userModifiedEvent.getUserBean();
        String userName = userBean.getUserName();
        System.out.println("userName = " + userName);
        System.out.println("operType= " + userBean.getOperType());
    }
}
