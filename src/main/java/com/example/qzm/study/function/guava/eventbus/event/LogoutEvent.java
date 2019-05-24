package com.example.qzm.study.function.guava.eventbus.event;

import lombok.Data;

/**
 * @ClassName LoginEvent
 * @Description TODO
 * @Version 1.0
 **/
@Data
public class LogoutEvent {
    private int id;
    private String name;

    public LogoutEvent(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
