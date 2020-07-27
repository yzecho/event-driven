package io.yzecho.sender;

import io.yzecho.EventCenter;
import io.yzecho.event.OrderEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author yzecho
 * @desc
 * @date 27/07/2020 16:44
 */
@Slf4j
@Component
public class EventSender {
    @Autowired
    private EventCenter eventCenter;

    public void post(OrderEvent orderEvent) {
        eventCenter.post(orderEvent);
    }
}
