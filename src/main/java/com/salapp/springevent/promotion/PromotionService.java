package com.salapp.springevent.promotion;

import com.salapp.springevent.customer.Customer;
import com.salapp.springevent.customer.CustomerRepository;
import com.salapp.springevent.order.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Slf4j
@RequiredArgsConstructor
@Component
public class PromotionService {

    private final CustomerRepository customerRepository;

    public void applyPromotion(Customer customer) {
        log.info("Apply free gift for a customer {}", customer);
    }

    public void calculateRewardPoints(Order order) {
        Customer customer = order.getCustomer();

        BigDecimal newRewardPoints = customer.getRewardPoints().add(BigDecimal.TEN);
        customer.setRewardPoints(newRewardPoints);
        customerRepository.save(customer);

        log.info("Customer {}, reward points old: {}, new: {}", customer.getId(), customer.getRewardPoints(), newRewardPoints);
    }
}
