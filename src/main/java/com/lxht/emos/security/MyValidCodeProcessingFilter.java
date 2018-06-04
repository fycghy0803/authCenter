package com.lxht.emos.security;

import com.lxht.emos.utils.ConstantVal;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by fanyuli on 2018/5/31.
 */
public class MyValidCodeProcessingFilter extends AbstractAuthenticationProcessingFilter {
    public MyValidCodeProcessingFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
        setContinueChainBeforeSuccessfulAuthentication(true);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        String username = request.getParameter(ConstantVal.PAR_USER_NAME);
        String password = request.getParameter(ConstantVal.PAR_PASSWORD);
        String validCode = request.getParameter(ConstantVal.PAR_CHECK_CODE);
        valid(validCode, request.getSession());
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        return token;
    }

    public void valid(String validCode, HttpSession session) {
        if (validCode == null) {
            throw new BadCredentialsException("验证码为空!");
        }

        String checkCode = (String)session.getAttribute(ConstantVal.CHECK_CODE);
        if (!validCode.equals(checkCode)) {
            session.removeAttribute(ConstantVal.CHECK_CODE);
            throw new BadCredentialsException("验证码错误!");
        } else {
            session.removeAttribute(ConstantVal.CHECK_CODE);
        }
    }

    public SimpleUrlAuthenticationFailureHandler getFailureHandler() {
        SimpleUrlAuthenticationFailureHandler failureHandler = (SimpleUrlAuthenticationFailureHandler)super.getFailureHandler();
        failureHandler.setUseForward(true);
        return failureHandler;
    }
}