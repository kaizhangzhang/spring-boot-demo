package com.example.springbootdemo.akka.spring;

import akka.actor.AbstractExtensionId;
import akka.actor.ExtendedActorSystem;
import akka.actor.Extension;
import akka.actor.Props;
import org.springframework.context.ApplicationContext;

/**
 * @author 代码千万行，注释第一行，编码不规范，同事两行泪 --kai.zhang
 * @description akka扩展点
 * @since 2019/3/30
 */
public class SpringExtension extends AbstractExtensionId<SpringExtension.SpringExt> {
    public static final SpringExtension SPRING_EXTENSION_PROVIDER = new SpringExtension();

    @Override
    public SpringExt createExtension(ExtendedActorSystem system) {
        return new SpringExt();
    }

    public static class SpringExt implements Extension {
        private volatile ApplicationContext applicationContext;

        public void initialize(ApplicationContext applicationContext) {
            this.applicationContext = applicationContext;
        }

        public Props props(String actorBeanName) {
            return Props.create(
                    SpringActorProducer.class, applicationContext, actorBeanName);
        }
    }
}
