package com.xdd.test.service;

import com.xdd.test.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2022-04-21
 */
public interface IUserService extends IService<User> {

    public User getUserByUsername(String username);

}
