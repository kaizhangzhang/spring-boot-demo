package com.example.springbootdemo.akka.spring;

import akka.actor.Actor;
import akka.actor.IndirectActorProducer;
import org.springframework.context.ApplicationContext;

/**
 * @author 代码千万行，注释第一行，编码不规范，同事两行泪 --kai.zhang
 * @description 从 ApplicationContext 中以 Spring Bean 的形式通过名称来创建 actors。
 * @since 2019/3/30
 */
public class SpringActorProducer implements IndirectActorProducer {
    private ApplicationContext applicationContext;
    private String beanActorName;

    public SpringActorProducer(ApplicationContext applicationContext, String beanActorName) {
        this.applicationContext = applicationContext;
        this.beanActorName = beanActorName;
    }

    @Override
    public Actor produce() {
        return (Actor) applicationContext.getBean(beanActorName);
    }

    @Override
    public Class<? extends Actor> actorClass() {
        return (Class<? extends Actor>) applicationContext.getType(beanActorName);
    }
}
