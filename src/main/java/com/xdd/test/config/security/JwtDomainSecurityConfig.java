package com.xdd.test.config.security;

import com.xdd.test.config.filter.JwtDomainAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@Configuration
public class JwtDomainSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    public JwtDomainSecurityConfig() {
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(this.jwtDomainAuthenticationFilter(http.getSharedObject(AuthenticationManager.class)), UsernamePasswordAuthenticationFilter.class);
    }




    @Bean
    public JwtDomainAuthenticationFilter jwtDomainAuthenticationFilter(AuthenticationManager authenticationManager){
        JwtDomainAuthenticationFilter jwtDomainAuthenticationFilter=new JwtDomainAuthenticationFilter();
        jwtDomainAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        jwtDomainAuthenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler);
        jwtDomainAuthenticationFilter.setAuthenticationManager(authenticationManager);
        jwtDomainAuthenticationFilter.setFilterProcessesUrl("/login");
        return jwtDomainAuthenticationFilter;
    }

}
