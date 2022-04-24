package com.xdd.test.config.security;

import com.xdd.test.config.filter.JwtAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtAuthenticationSuccessHandler jwtAuthenticationSuccessHandler;
    @Autowired
    private GlobalAuthenticationFailureHandler globalAuthenticationFailureHandler;
    @Autowired
    private JwtDomainSecurityConfig jwtDomainSecurityConfig;
    @Autowired
    private RestAuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    private RestAccessDeniedHandle accessDeniedHandle;
    //白名单
    private String[] permittedUris;

    public SecurityConfig() {
        List<String> whiteList =new ArrayList<>();
        whiteList.add("/login");
        whiteList.add("/logout");
        whiteList.add("/doc.html");
        whiteList.add("/webjars/**");
        whiteList.add("/webjars/**");
        whiteList.add("/swagger-resources/**");
        whiteList.add("/v2/api-docs/**");
        whiteList.add("/swagger-ui.html");
        permittedUris= whiteList.toArray(new String[whiteList.size()]);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            //基于token，不需要session
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            //校验白名单外的所有请求
            .authorizeRequests().antMatchers(permittedUris).permitAll().anyRequest().authenticated()
            .and()
//            .formLogin().loginPage("/login").loginProcessingUrl("/login").successHandler(jwtAuthenticationSuccessHandler).failureHandler(globalAuthenticationFailureHandler)
//            .and()
            .csrf().disable().apply(jwtDomainSecurityConfig)
            .and()
            .addFilterAfter(jwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
        //添加自定义未授权和未登录结果返回
        http
            .exceptionHandling()
            .accessDeniedHandler(accessDeniedHandle)
            .authenticationEntryPoint(authenticationEntryPoint);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthorizationFilter jwtAuthorizationFilter() {
        return new JwtAuthorizationFilter(userDetailsService, jwtTokenUtil);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
