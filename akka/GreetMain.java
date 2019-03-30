package com.example.springbootdemo.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Inbox;
import akka.actor.Props;

import java.time.Duration;

/**
 * @author kai.zhang
 * @description TODO
 * @since 2018/10/23
 */
public class GreetMain {
    public static void main(String[] args) throws Exception {
        final ActorSystem system = ActorSystem.create("greet-demo");
        //创建一个到greeter actor的管道
        final ActorRef greeter = system.actorOf(Props.create(Greeter.class), "greeter");
        //创建邮箱
        final Inbox inbox = Inbox.create(system);

        //先发第一个消息，消息类型是WhoToGreet
        greeter.tell(new WhoToGreet("kzAkka"), ActorRef.noSender());
        //真正的发消息，消息体为Greet
        inbox.send(greeter, new Greet("kai"));

        //等待一会，尝试接受greeter返回消息
        Greeting  greeting1 = (Greeting) inbox.receive(Duration.ofSeconds(1));
        System.out.println("Greeting:" + greeting1.message);


        // 发送第三个消息,修改名字
        greeter.tell(new WhoToGreet("typesafe"), ActorRef.noSender());
        // 发送第四个消息
        inbox.send(greeter, new Greet("kai"));

        // 等待5秒尝试接收Greeter返回的消息
        Greeting greeting2 = (Greeting) inbox.receive(Duration.ofSeconds(5));
        System.out.println("Greeting: " + greeting2.message);
        // 新创建一个Actor的管道
        ActorRef greetPrinter = system.actorOf(Props.create(GreetPrinter.class));

        //使用schedule 每一秒发送一个Greet消息给 greeterActor,然后把greeterActor的消息返回给greetPrinterActor
        system.scheduler().schedule(Duration.ZERO, Duration.ofSeconds(1), greeter, new Greet("kai"), system.dispatcher(), greetPrinter);
        system.terminate();
    }
}
