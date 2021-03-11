package luorijun.learn.springdataredis.events;

import org.springframework.context.ApplicationEvent;

public class OrderEvent extends ApplicationEvent {
    public OrderEvent(int source) {
        super(source);
    }
}
