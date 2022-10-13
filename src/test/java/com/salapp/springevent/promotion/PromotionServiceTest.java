package com.salapp.springevent.promotion;

import com.salapp.springevent.customer.Customer;
import com.salapp.springevent.customer.CustomerRepository;
import com.salapp.springevent.customer.CustomerService;
import com.salapp.springevent.order.Order;
import com.salapp.springevent.order.OrderRepository;
import com.salapp.springevent.order.OrderService;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.annotation.DirtiesContext;

import java.math.BigDecimal;

import static com.salapp.springevent.order.Order.OrderStatus.SAVED;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class PromotionServiceTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;

    @SpyBean
    private PromotionService promotionService;

    @Test
    void registerCustomer_forCustomerWithoutNewsletter_promotionNoCalled() {
        // given
        Customer customer = givenCustomer("john@email.com", 50, false);

        // when
        customerService.register(customer);

        // then
        BDDMockito.then(promotionService).shouldHaveNoInteractions();
    }

    @Test
    void registerCustomer_forCustomerAppliedForNewsletter_callsPromotion() {
        // given
        Customer customer = givenCustomer("john@email.com", 50, true);

        // when
        customerService.register(customer);

        // then
        BDDMockito.then(promotionService).should(Mockito.times(1)).applyPromotion(customer);
    }

    @Test
    void placeOrder_forCustomer_addsRewardPoints() {
        // given
        Customer customer = givenCustomer("john@email.com", 50, false);

        Order order = new Order(SAVED);
        order.setCustomer(customer);
        orderRepository.save(order);

        // when
        orderService.placeOrder(order);

        // then
        BigDecimal newRewardStatusPoints = customerRepository.findById(customer.getId())
                .map(Customer::getRewardPoints)
                .orElseThrow();


        BDDAssertions.then(newRewardStatusPoints).isEqualTo(BigDecimal.valueOf(60));
    }

    private Customer givenCustomer(String email, int rewardPoints, boolean newsletter) {
        Customer customer = new Customer(email);
        customer.setRewardPoints(BigDecimal.valueOf(rewardPoints));
        customer.setNewsletter(newsletter);
        return customerRepository.save(customer);
    }
}
