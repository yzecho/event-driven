package io.yzecho.event;

import io.yzecho.constant.OrderEventType;

/**
 * @author yzecho
 * @desc
 * @date 27/07/2020 15:50
 */
public class OrderCreateEvent implements OrderEvent {

    private final Long orderId;

    public OrderCreateEvent(Long orderId) {
        this.orderId = orderId;
    }

    @Override
    public Long getOrderId() {
        return this.orderId;
    }

    @Override
    public OrderEventType getEventType() {
        return OrderEventType.CREATE;
    }
}
