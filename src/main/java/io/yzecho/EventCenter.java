package io.yzecho;

import io.yzecho.event.OrderEvent;
import io.yzecho.listener.EventListener;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;

/**
 * @author yzecho
 * @desc 事件中心
 * @date 27/07/2020 16:14
 */
@Slf4j
public class EventCenter {

    /**
     * 事件类型和监听器的绑定映射
     */
    private final ConcurrentHashMap<Class<?>, List<EventListener>> subscribers = new ConcurrentHashMap<>();

    private final Executor executor;

    public EventCenter(Executor executor) {
        this.executor = executor;
    }

    /**
     * 绑定 监听器与事件类型
     *
     * @param eventListener
     * @param clazz
     */
    public void registry(EventListener eventListener, Class<?> clazz) {
        List<EventListener> listeners = subscribers.get(clazz);
        if (listeners == null) {
            listeners = new ArrayList<>();
        }
        listeners.add(eventListener);
        subscribers.put(clazz, listeners);
    }

    /**
     * 向事件中心发送消息
     *
     * @param orderEvent
     */
    public void post(OrderEvent orderEvent) {
        List<EventListener> listeners = subscribers.get(orderEvent.getClass());
        if (listeners == null || listeners.size() == 0) {
            log.error("找不到该事件的监听器");
            return;
        }
        listeners.forEach(listener -> executor.execute(() -> listener.trigger(orderEvent)));
    }

}
