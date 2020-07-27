package io.yzecho.listener;

import io.yzecho.EventCenter;
import io.yzecho.event.OrderCancelEvent;
import io.yzecho.event.OrderEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author yzecho
 * @desc
 * @date 27/07/2020 15:54
 */
@Slf4j
@Component
public class OrderCancelListener implements EventListener {

    @Autowired
    private EventCenter eventCenter;

    @PostConstruct
    private void registry() {
        eventCenter.registry(this, OrderCancelEvent.class);
    }

    @Override
    public void trigger(OrderEvent orderEvent) {
        log.info("取消订单，订单id:{}", orderEvent.getOrderId());
    }
}
