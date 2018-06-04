package com.lxht.emos.event;

import com.lxht.emos.bean.UserBean;
import org.springframework.context.ApplicationContext;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Created by fanyuli on 2018/5/8.
 */
@Component
public class UserModifiedPublisher implements ApplicationContextAware {
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(org.springframework.context.ApplicationContext applicationContext) throws BeansException {
       this.applicationContext = applicationContext;
    }

    public void publish(UserBean userBean) {
        applicationContext.publishEvent(new UserModifiedEvent(this,userBean));
    }
}
