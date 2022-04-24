package com.xdd.test.controller;

import com.xdd.test.entity.condition.LoginCondition;
import com.xdd.test.model.BasicOutputParam;
import com.xdd.test.model.ResultConverter;
import com.xdd.test.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public BasicOutputParam login(@RequestBody LoginCondition condition, HttpServletRequest request){
        return loginService.login(condition,request);
    }

    @PostMapping("/logout")
    public BasicOutputParam logout(){
        return ResultConverter.doResult(true);
    }
}
