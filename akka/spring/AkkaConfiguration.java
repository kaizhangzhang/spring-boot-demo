package com.example.springbootdemo.akka.spring;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.annotation.Resource;

/**
 * @author 代码千万行，注释第一行，编码不规范，同事两行泪 --kai.zhang
 * @description actor配置
 * @since 2019/3/30
 */
@Configuration
public class AkkaConfiguration {
    public static final String ACTOR_SYSTEM = "ACTOR_SYSTEM";
    public static final String LOGIN_ACTOR = "LOGIN_ACTOR";
    @Resource
    private ApplicationContext applicationContext;


    @Bean(name = ACTOR_SYSTEM)
    public ActorSystem actorSystem() {
        ActorSystem actorSystem = ActorSystem.create(ACTOR_SYSTEM);
        SpringExtension.SPRING_EXTENSION_PROVIDER.get(actorSystem).initialize(applicationContext);
        return actorSystem;
    }

    @Bean(name = LOGIN_ACTOR)
    public ActorRef loginActor(ActorSystem actorSystem) {
        ActorRef actorRef = actorSystem
                .actorOf(SpringExtension.SPRING_EXTENSION_PROVIDER.get(actorSystem).props("loginActor"), LOGIN_ACTOR);
        System.out.println(actorRef.getClass());
        return actorRef;
    }
}
