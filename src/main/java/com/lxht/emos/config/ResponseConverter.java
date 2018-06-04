package com.lxht.emos.config;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.lxht.emos.bean.ReturnBean;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.util.List;

/**
 * Created by fanyuli on 2018/5/14.
 */
public class ResponseConverter extends FastJsonHttpMessageConverter {
    @Override
    protected void writeInternal(Object object, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        ReturnBean returnBean = new ReturnBean();
        if(!(object instanceof ReturnBean)) {
            returnBean.setRetCode("1");

            if(object instanceof List) {
                returnBean.setRetBeans((List<Object>) object);
            } else {
                returnBean.setRetBean(object);
            }
            super.writeInternal(returnBean, outputMessage);
        }else {
            super.writeInternal(object, outputMessage);
        }
    }
}
