package com.xdd.test.service.impl;

import com.xdd.test.entity.User;
import com.xdd.test.model.AppUser;
import com.xdd.test.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserService implements UserDetailsService {
    private static final Logger log= LoggerFactory.getLogger(AppUserService.class);
    @Autowired
    private IUserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (log.isDebugEnabled()){
            log.debug("正在登录的账号是："+username);
        }
        User user= userService.getUserByUsername(username);
        if (user==null){
            throw new UsernameNotFoundException("用户不存在!");
        }else {
            AppUser appUser = new AppUser(user);
            appUser.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ANONYMOUS"));
            return appUser;
        }
    }
}
