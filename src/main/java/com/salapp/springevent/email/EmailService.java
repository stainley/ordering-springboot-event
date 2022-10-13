package com.salapp.springevent.email;

import com.salapp.springevent.customer.Customer;
import com.salapp.springevent.order.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EmailService {

    public void sendRegisterEmail(Customer customer) {
        log.info("Sending registration email to customer {}", customer);
    }

    public void sendCustomerRemovedEmail(Customer customer) {
        log.info("Sending removed email for customer {}", customer);
    }

    public void sendOrderEmail(Order order) {
        log.info("Sending email for order {}", order);
    }
}
