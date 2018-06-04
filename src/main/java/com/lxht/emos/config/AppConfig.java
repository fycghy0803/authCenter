package com.lxht.emos.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.PostConstruct;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fanyuli on 2018/5/8.
 */
@Configuration
@EnableCaching
@MapperScan("com.lxht.emos.mapper")
public class AppConfig extends WebMvcConfigurationSupport{
    @Value("${spring.jackson.date-format}")
    private String dateFormat;

    public HttpMessageConverter getJsonConverter() {
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setCharset(Charset.forName("UTF-8"));
        fastJsonConfig.setDateFormat(dateFormat);
        fastJsonConfig.setSerializerFeatures(SerializerFeature.WriteDateUseDateFormat,SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.WriteNullNumberAsZero);

        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new ResponseConverter();
        List<MediaType> mediaTypeList = new ArrayList<>();
        mediaTypeList.add(MediaType.APPLICATION_JSON);
        fastJsonHttpMessageConverter.setSupportedMediaTypes(mediaTypeList);
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        return fastJsonHttpMessageConverter;
    }

    @Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(getJsonConverter()); //增加支持json格式的转换converter
        converters.add(new MappingJackson2HttpMessageConverter()); //增加支持application/*+json格式的转换converter
        converters.add(new ResourceHttpMessageConverter()); //增加*/*格式转换的converter
        super.configureMessageConverters(converters);
    }
}

