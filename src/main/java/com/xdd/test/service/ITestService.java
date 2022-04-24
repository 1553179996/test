package com.xdd.test.service;

import com.xdd.test.entity.Test;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2022-04-13
 */
public interface ITestService extends IService<Test> {
     List<Test> tests();
}
