package com.example.springbootdemo.akka.spring;

import akka.actor.UntypedAbstractActor;
import com.example.springbootdemo.akka.Greet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


/**
 * @author 代码千万行，注释第一行，编码不规范，同事两行泪 --kai.zhang
 * @description TODO
 * @since 2019/3/30
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class LoginActor extends UntypedAbstractActor {
    private static Logger logger = LoggerFactory.getLogger(LoginActor.class);

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof Greet) {
            Greet greet = (Greet) message;
            String name = greet.getName();
            getSender().tell("haha zhangkai", getSelf());
        } else {
            logger.error("can not handle message, msg class = {}", message.getClass());
        }
    }
}
