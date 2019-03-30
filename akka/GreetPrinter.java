package com.example.springbootdemo.akka;

import akka.actor.UntypedAbstractActor;

/**
 * @author kai.zhang
 * @description
 * @since 2018/10/23
 */
public class GreetPrinter extends UntypedAbstractActor {
    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof  Greeting) {
            System.out.println(((Greeting)message).message);
        }
    }
}
