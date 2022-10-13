package com.salapp.springevent.order;

import lombok.Data;
import org.springframework.context.ApplicationEvent;

@Data
public class OrderCompletedEvent {

    private final Order order;

}
