package com.example.qzm.study.function.guava.eventbus.handler;

import com.example.qzm.study.function.guava.eventbus.event.LoginEvent;
import com.example.qzm.study.function.guava.eventbus.event.LogoutEvent;
import com.google.common.eventbus.Subscribe;
import org.springframework.stereotype.Component;

/**
 * @ClassName LoginEventHandler
 * @Version 1.0
 **/
@Component
public class CommonEventHandler implements AbsEventHandler{

    @Subscribe
    public void loginEventListener(LoginEvent loginEvent) {
        int id = loginEvent.getId();
        String name = loginEvent.getName();
        System.out.println(id + "----" + name);
    }

    @Subscribe
    public void loginEventListener(LogoutEvent loginEvent) {
        int id = loginEvent.getId();
        String name = loginEvent.getName();
        System.out.println(id + "----" + name);
    }
}
