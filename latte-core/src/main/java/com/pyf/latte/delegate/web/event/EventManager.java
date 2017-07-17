package com.pyf.latte.delegate.web.event;

import java.util.HashMap;

/**
 * <br/>
 * 作者：裴云飞
 * <br/>
 * 时间：2017/7/17
 */
public class EventManager {

    private HashMap<String, Event> EVENTS = new HashMap<>();

    private EventManager() {
    }

    private static final class Holder {
        private static final EventManager INSTANCE = new EventManager();
    }

    public static EventManager getInstance() {
        return Holder.INSTANCE;
    }

    public EventManager addEvent(String action, Event event) {
        EVENTS.put(action, event);
        return this;
    }

    public Event getEvent(String action) {
        return EVENTS.get(action);
    }
}
