package com.example.springbootdemo.akka;

import akka.actor.UntypedAbstractActor;

/**
 * @author kai.zhang
 * @description 打招呼的actor
 * @since 2018/10/23
 */
public class Greeter extends UntypedAbstractActor {
    String greeting = "";
    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof WhoToGreet) {
            greeting = "hello " + ((WhoToGreet)message).who;
        } else if (message instanceof Greet) {
            //发送招呼消息给这个actor的actor
            getSender().tell(new Greeting(greeting), getSelf());
        } else {
            unhandled(message);
        }

    }
}
