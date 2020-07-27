package io.yzecho;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.yzecho.event.OrderCancelEvent;
import io.yzecho.event.OrderCreateEvent;
import io.yzecho.sender.EventSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.*;

/**
 * @author yzecho
 * @desc
 * @date 27/07/2020 16:14
 */
@Slf4j
@SpringBootApplication
public class EventDrivenApplication {
    public static void main(String[] args) throws InterruptedException {
        ApplicationContext applicationContext = SpringApplication.run(EventDrivenApplication.class, args);
        EventSender eventSender = applicationContext.getBean(EventSender.class);
        while (true) {
            long orderId = ThreadLocalRandom.current().nextLong();
            eventSender.post(new OrderCreateEvent(orderId));
            log.info("有一个新订单，订单id:{}", orderId);

            orderId = ThreadLocalRandom.current().nextLong();
            eventSender.post(new OrderCancelEvent(orderId));
            log.info("有一个订单取消，订单id:{}", orderId);

            Thread.sleep(ThreadLocalRandom.current().nextLong(1000, 10000));
        }
    }

    @Bean
    public EventCenter eventCenter() {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("event-bus-%d").build();
        int corePoolSize = Runtime.getRuntime().availableProcessors();
        int maxPoolSize = corePoolSize * 2;

        Executor pool = new ThreadPoolExecutor(corePoolSize, maxPoolSize, 10L,
                TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(1024),
                namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

        return new EventCenter(pool);
    }
}
