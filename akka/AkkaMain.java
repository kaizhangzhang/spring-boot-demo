package com.example.springbootdemo.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedAbstractActor;

/**
 * @author kai.zhang
 * @since 2018/10/17
 */
public class AkkaMain {
    public static void main(String... args) {
        ActorSystem system = ActorSystem.create("actor-demo");
        ActorRef hello = system.actorOf(Props.create(Hello.class));
        hello.tell("first akka", ActorRef.noSender());
        try {
            Thread.sleep(1000);
        } catch (Exception e){
            e.printStackTrace();
        }
        system.terminate();
    }

    private static class Hello extends UntypedAbstractActor{

        @Override
        public void onReceive(Object message) throws Throwable {
            if (message instanceof String) {
                System.out.println("Hello " + message);
            }
        }
    }
}
