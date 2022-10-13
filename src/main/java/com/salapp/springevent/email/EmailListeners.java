package com.salapp.springevent.email;

import com.salapp.springevent.customer.CustomerRegisterEvent;
import com.salapp.springevent.customer.CustomerRemovedEvent;
import com.salapp.springevent.order.OrderCompletedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class EmailListeners {

    private final EmailService emailService;

    @EventListener
    public void onRegisterEvent(CustomerRegisterEvent event) {
        this.emailService.sendRegisterEmail(event.getCustomer());
    }

    @EventListener
    public void onRemoveEvent(CustomerRemovedEvent event) {
        this.emailService.sendCustomerRemovedEmail(event.getCustomer());
    }

    @EventListener
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onOrderCompletedEvent(OrderCompletedEvent event) {
        this.emailService.sendOrderEmail(event.getOrder());
    }
}
