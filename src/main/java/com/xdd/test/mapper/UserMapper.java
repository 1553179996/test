package com.xdd.test.mapper;

import com.xdd.test.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 
 * @since 2022-04-21
 */
public interface UserMapper extends BaseMapper<User> {
    @Select("select * from t_user where username=#{username} and enabled = 1 ")
    public User getUserByUsername(String username);
}
