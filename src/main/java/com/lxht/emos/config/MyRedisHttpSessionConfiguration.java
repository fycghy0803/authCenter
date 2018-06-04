
package com.lxht.emos.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.session.data.redis.RedisFlushMode;
import org.springframework.session.data.redis.RedisOperationsSessionRepository;
import org.springframework.session.data.redis.config.ConfigureNotifyKeyspaceEventsAction;
import org.springframework.session.data.redis.config.ConfigureRedisAction;
import org.springframework.session.data.redis.config.annotation.web.http.RedisHttpSessionConfiguration;
import org.springframework.util.StringUtils;

/**
 * Created by fanyuli on 2018/5/28.
 */
@Configuration
public class MyRedisHttpSessionConfiguration extends RedisHttpSessionConfiguration{
    private RedisSerializer<Object> defaultRedisSerializer;
    private String redisNamespace = "spring:session";
    private ApplicationEventPublisher applicationEventPublisher;
    private Integer maxInactiveIntervalInSeconds = Integer.valueOf(1800);
    private RedisFlushMode redisFlushMode;
    private String cleanupCron;
    private ConfigureRedisAction configureRedisAction;
    private ClassLoader classLoader;

    @Autowired
    RedisProperties redisProperties;
    @Value("${spring.session.redis.database}")
    private int sessionRedisDatabase;

    public MyRedisHttpSessionConfiguration() {
        this.redisFlushMode = RedisFlushMode.ON_SAVE;
        this.cleanupCron = "0 * * * * *";
        this.configureRedisAction = new ConfigureNotifyKeyspaceEventsAction();
    }

    @Bean
    public RedisOperationsSessionRepository sessionRepository() {
        RedisTemplate<Object, Object> redisTemplate = this.createRedisTemplate();
        RedisOperationsSessionRepository sessionRepository = new RedisOperationsSessionRepository(redisTemplate);
        sessionRepository.setApplicationEventPublisher(this.applicationEventPublisher);

        if(this.defaultRedisSerializer != null) {
            sessionRepository.setDefaultSerializer(this.defaultRedisSerializer);
        }

        sessionRepository.setDefaultMaxInactiveInterval(this.maxInactiveIntervalInSeconds.intValue());
        if(StringUtils.hasText(this.redisNamespace)) {
            sessionRepository.setRedisKeyNamespace(this.redisNamespace);
        }

        sessionRepository.setRedisFlushMode(this.redisFlushMode);
        return sessionRepository;
    }

    public RedisTemplate<Object, Object> createRedisTemplate() {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        if(this.defaultRedisSerializer != null) {
            redisTemplate.setDefaultSerializer(this.defaultRedisSerializer);
        }

        RedisConnectionFactory lettuceConnectionFactory = redisConnectionFactory();
        redisTemplate.setConnectionFactory(lettuceConnectionFactory);
        redisTemplate.setBeanClassLoader(this.classLoader);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    public RedisConnectionFactory redisConnectionFactory() {
        String host = redisProperties.getHost();
        int port = redisProperties.getPort();
        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(host,port);
        lettuceConnectionFactory.setDatabase(sessionRedisDatabase);
        lettuceConnectionFactory.afterPropertiesSet();
        return lettuceConnectionFactory;
    }

    @Autowired
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
}
