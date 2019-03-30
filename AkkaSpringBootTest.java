package com.example.springbootdemo;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.pattern.Patterns;
import akka.util.Timeout;
import com.example.springbootdemo.akka.Greet;
import com.example.springbootdemo.akka.spring.SpringExtension;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.FiniteDuration;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author 代码千万行，注释第一行，编码不规范，同事两行泪 --kai.zhang
 * @description TODO
 * @since 2019/3/30
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AkkaSpringBootTest {
    @Resource
    private ActorSystem actorSystem;
    @Test
    public void akkaSpringTest() throws Exception {
        ActorRef greeter = actorSystem.actorOf(SpringExtension.SPRING_EXTENSION_PROVIDER.get(actorSystem)
                .props("greetingActor"), "greeter");

        FiniteDuration duration = FiniteDuration.create(60, TimeUnit.SECONDS);
        Timeout timeout = Timeout.durationToTimeout(duration);

        Future<Object> result = Patterns.ask(greeter, new Greet("zhangkai"), timeout);

        Assert.assertEquals("Hello, zhangkai", Await.result(result, duration));
    }
}
