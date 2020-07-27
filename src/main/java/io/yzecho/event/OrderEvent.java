package io.yzecho.event;

import io.yzecho.constant.OrderEventType;

/**
 * @author yzecho
 * @desc
 * @date 27/07/2020 15:47
 */
public interface OrderEvent {
    Long getOrderId();

    OrderEventType getEventType();
}
