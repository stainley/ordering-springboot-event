package com.salapp.springevent.analytics;

import com.salapp.springevent.customer.CustomerRegisterEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AnalyticsCustomerRegisterListener {

    private final AnalyticsService analyticsService;

    @EventListener
    public void onRegisterEvent(CustomerRegisterEvent event) {
        this.analyticsService.registerNewCustomers(event.getCustomer());
    }
}
