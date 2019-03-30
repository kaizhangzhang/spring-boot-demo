package com.example.springbootdemo.akka;

import java.io.Serializable;

/**
 * @author kai.zhang
 * @description 打招呼的人
 * @since 2018/10/23
 */
public class WhoToGreet implements Serializable {
    public final String who;
    public WhoToGreet(String who) {
        this.who = who;
    }
}
