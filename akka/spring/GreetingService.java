package com.example.springbootdemo.akka.spring;

import org.springframework.stereotype.Service;

/**
 * @author 代码千万行，注释第一行，编码不规范，同事两行泪 --kai.zhang
 * @since 2019/3/30
 */
@Service
public class GreetingService {
    public String greet(String name) {
        return "Hello, " + name;
    }
}
