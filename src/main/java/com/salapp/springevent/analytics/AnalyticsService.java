package com.salapp.springevent.analytics;

import com.salapp.springevent.customer.Customer;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static java.lang.Thread.sleep;

@Slf4j
@Component
public class AnalyticsService {

    @SneakyThrows
    public void registerNewCustomers(Customer customer) {
        log.info("calling analytics service for customer {}", customer);
        sleep(5_000);
    }
}
