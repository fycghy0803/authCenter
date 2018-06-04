package com.lxht.emos.config;

import com.lxht.emos.bean.MenuBean;
import com.lxht.emos.security.MyUserDetailsService;
import com.lxht.emos.security.MyValidCodeProcessingFilter;
import com.lxht.emos.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;

import java.util.List;

/**
 * Created by fanyuli on 2018/5/28.
 */

@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
    private String loginPage = "/userLogin";
    private String failureUrl = "/userLogin?error=T";
    private String successUrl = "/userLogin?error=F";
    private String applyCheckCode = "/applyCheckCode";

    @Autowired
    MenuService menuService;

    public MySecurityConfig() {
        super(true);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.securityContext().securityContextRepository(securityContextRepository());
        List<MenuBean>  menuBeanList =  menuService.getMenus();
        for(MenuBean tmpBean : menuBeanList) {
            http.authorizeRequests().antMatchers(tmpBean.getMenuUrl()).hasAnyRole(tmpBean.getRoleIds().split(","));
        }

        http.authorizeRequests()
                .and()
                .addFilterBefore(validCodeProcessingFilter(),UsernamePasswordAuthenticationFilter.class)
                .formLogin().loginPage(getLoginPage()).failureUrl(getFailureUrl())
                           .failureForwardUrl(getFailureUrl()).
                           successForwardUrl(getSuccessUrl()).permitAll();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
        web.ignoring().antMatchers(applyCheckCode);
    }

    @SuppressWarnings("deprecation")
    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        MyUserDetailsService myUserDetailsService = new MyUserDetailsService();
        return myUserDetailsService;
    }

    @Bean
    public SecurityContextRepository securityContextRepository() {
        HttpSessionSecurityContextRepository httpSessionSecurityContextRepository = new HttpSessionSecurityContextRepository();
        return httpSessionSecurityContextRepository;
    }

    @Bean
    public  MyValidCodeProcessingFilter validCodeProcessingFilter() throws Exception{
        MyValidCodeProcessingFilter myValidCodeProcessingFilter = new MyValidCodeProcessingFilter(getLoginPage());
        myValidCodeProcessingFilter.getFailureHandler().setDefaultFailureUrl(getFailureUrl());
        myValidCodeProcessingFilter.setAuthenticationManager(this.authenticationManager());

        return myValidCodeProcessingFilter;
    }

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public String getFailureUrl() {
        return failureUrl;
    }

    public void setFailureUrl(String failureUrl) {
        this.failureUrl = failureUrl;
    }

    public String getSuccessUrl() {
        return successUrl;
    }

    public void setSuccessUrl(String successUrl) {
        this.successUrl = successUrl;
    }

    public String getApplyCheckCode() {
        return applyCheckCode;
    }

    public void setApplyCheckCode(String applyCheckCode) {
        this.applyCheckCode = applyCheckCode;
    }
}
