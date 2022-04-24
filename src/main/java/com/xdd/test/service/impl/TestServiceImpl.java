package com.xdd.test.service.impl;

import com.xdd.test.entity.Test;
import com.xdd.test.mapper.TestMapper;
import com.xdd.test.service.ITestService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2022-04-13
 */
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements ITestService {
    @Autowired
    private TestMapper testMapper;
    public List<Test> tests(){
        return testMapper.tests();
    }
}
