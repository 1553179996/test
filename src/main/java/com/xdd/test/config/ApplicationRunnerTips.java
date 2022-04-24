package com.xdd.test.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class ApplicationRunnerTips implements ApplicationRunner {
    @Autowired
    private Environment environment;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("App already started with profile: "+ Arrays.toString(environment.getActiveProfiles()));
    }
}
