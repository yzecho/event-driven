package io.yzecho.listener;

import io.yzecho.event.OrderEvent;

/**
 * @author yzecho
 * @desc 监听器接口
 * @date 27/07/2020 15:39
 */
public interface EventListener {

    /**
     * 事件触发时调用
     *
     * @param orderEvent
     */
    void trigger(OrderEvent orderEvent);
}
