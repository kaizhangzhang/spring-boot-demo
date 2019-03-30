package com.example.springbootdemo.akka;

import java.io.Serializable;

/**
 * @author kai.zhang
 * @description 招呼体
 * @since 2018/10/23
 */
public class Greeting implements Serializable {
    public final String message;
    public Greeting(String msg) {
        this.message = msg;
    }
}
