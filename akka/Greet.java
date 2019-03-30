package com.example.springbootdemo.akka;

import java.io.Serializable;

/**
 * @author kai.zhang
 * @description TODO
 * @since 2018/10/23
 */
public class Greet implements Serializable {
    private String name;
    public Greet(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
