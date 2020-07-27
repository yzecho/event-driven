package io.yzecho.listener;

import io.yzecho.EventCenter;
import io.yzecho.event.OrderCreateEvent;
import io.yzecho.event.OrderEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author yzecho
 * @desc
 * @date 27/07/2020 17:09
 */
@Slf4j
@Component
public class OrderCreateListener implements EventListener {
    @Autowired
    private EventCenter eventCenter;

    @PostConstruct
    private void registry() {
        eventCenter.registry(this, OrderCreateEvent.class);
    }

    @Override
    public void trigger(OrderEvent orderEvent) {
        log.info("创建订单，订单id:{}", orderEvent.getOrderId());
    }
}
