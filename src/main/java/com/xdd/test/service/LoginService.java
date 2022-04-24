package com.xdd.test.service;

import com.xdd.test.entity.condition.LoginCondition;
import com.xdd.test.model.BasicOutputParam;

import javax.servlet.http.HttpServletRequest;

public interface LoginService {
    public BasicOutputParam login(LoginCondition condition, HttpServletRequest request);
}
