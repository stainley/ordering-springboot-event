package com.salapp.springevent.promotion;

import com.salapp.springevent.customer.CustomerRegisterEvent;
import com.salapp.springevent.order.OrderCompletedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PromotionListeners {

    private final PromotionService promotionService;

    @EventListener(condition = "#event.customer.newsletter")
    public void onRegistrationEvent(CustomerRegisterEvent event) {
        this.promotionService.applyPromotion(event.getCustomer());
    }

    @EventListener
    public void onOrderCompleted(OrderCompletedEvent event) {
        this.promotionService.calculateRewardPoints(event.getOrder());
    }
}
