package com.salapp.springevent.ticket;

import com.salapp.springevent.order.OrderCompletedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class TickeOrderFailedListening {

    private final TicketService ticketService;

    @EventListener
    @TransactionalEventListener(phase = TransactionPhase.AFTER_ROLLBACK)
    public void onCompletedEvent(OrderCompletedEvent event) {
        this.ticketService.createTicket(event.getOrder());
    }
}
