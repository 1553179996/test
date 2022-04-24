package com.xdd.test.controller;


import com.xdd.test.entity.Test;
import com.xdd.test.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 
 * @since 2022-04-13
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    ITestService testService;

    @GetMapping(value = "tests")
    public List<Test> tests(){
        return testService.tests();
    }
}
