package com.xdd.test.config.filter;

import com.xdd.test.config.security.JwtDomainAuthenticationToken;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtDomainAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    private String usernameParameter ="username";
    private String passwordParameter ="password";
    private boolean postOnly = true;

    public JwtDomainAuthenticationFilter() {
        super(new AntPathRequestMatcher("/login","POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        if (postOnly&&!"POST".equals(request.getMethod())){
            throw new AuthenticationServiceException("请求方式错误！");
        }else {
            String username = request.getParameter(usernameParameter);
            String password = request.getParameter(passwordParameter);
            if (username==null){
                username="";
            }
            if (password==null){
                password="";
            }
            username = username.trim();
            JwtDomainAuthenticationToken  authRequest = new JwtDomainAuthenticationToken(username,password);
            setContinueChainBeforeSuccessfulAuthentication(true);
            return authRequest;
        }
    }
}
