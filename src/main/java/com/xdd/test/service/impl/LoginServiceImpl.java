package com.xdd.test.service.impl;

import com.xdd.test.config.security.JwtTokenUtil;
import com.xdd.test.entity.condition.LoginCondition;
import com.xdd.test.model.BasicOutputParam;
import com.xdd.test.model.ResultConverter;
import com.xdd.test.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private AppUserService appUserService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    /**
     * 登录返回token
     * @param condition
     * @param request
     * @return
     */
    @Override
    public BasicOutputParam login(LoginCondition condition, HttpServletRequest request) {
        //登录
        UserDetails userDetails = appUserService.loadUserByUsername(condition.getUsername());
        if (userDetails==null||!passwordEncoder.matches(condition.getPassword(),userDetails.getPassword())){
            return ResultConverter.doResult("用户名或密码不正确");
        }
        //更新security登录用户
        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //获取token
        String token=jwtTokenUtil.generateToken(userDetails);
        Map map=new HashMap<>();
        map.put("access_token",token);
        return ResultConverter.doResult(map);
    }
}
