package com.lxht.emos.bean;

import java.util.List;

/**
 * Created by fanyuli on 2018/5/14.
 */
public class ReturnBean extends BaseBean{
    private String retCode;
    private String retMsg;
    private Object retBean;
    private List<Object> retBeans;

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public Object getRetBean() {
        return retBean;
    }

    public void setRetBean(Object retBean) {
        this.retBean = retBean;
    }

    public List<Object> getRetBeans() {
        return retBeans;
    }

    public void setRetBeans(List<Object> retBeans) {
        this.retBeans = retBeans;
    }
}
