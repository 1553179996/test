package com.xdd.test.service.impl;

import com.xdd.test.entity.User;
import com.xdd.test.mapper.UserMapper;
import com.xdd.test.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2022-04-21
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }
}
