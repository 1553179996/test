package com.xdd.test.mapper;

import com.xdd.test.entity.Test;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 
 * @since 2022-04-13
 */
public interface TestMapper extends BaseMapper<Test> {

    @Select("select * from TEST")
    public List<Test> tests();

}
