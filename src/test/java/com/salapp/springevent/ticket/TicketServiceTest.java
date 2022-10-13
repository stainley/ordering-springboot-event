package com.salapp.springevent.ticket;

import com.salapp.springevent.customer.Customer;
import com.salapp.springevent.customer.CustomerRepository;
import com.salapp.springevent.order.Order;
import com.salapp.springevent.order.OrderRepository;
import com.salapp.springevent.order.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.annotation.DirtiesContext;

import java.math.BigDecimal;

import static com.salapp.springevent.order.Order.OrderStatus.SAVED;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@SpringBootTest
@Slf4j
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class TicketServiceTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;

    @SpyBean
    private TicketService ticketService;

    @Test
    void forPlaceOrder_whenRolledBacked_createTicket() {
        // given
        Customer customer = givenCustomer(90);
        Order order = givenOrder(customer);

        // when
        try {
            orderService.placeOrder(order);
        } catch (Exception e) {
            log.error("Exception while placing an order", e);
        }

        // then
        then(ticketService).should(times(1)).createTicket(order);
    }

    @Test
    void forPlaceOrder_whenCommitSuccessful_dontCreateTicket() {
        // given
        Customer customer = givenCustomer(50);
        Order order = givenOrder(customer);

        // when
        orderService.placeOrder(order);

        // then
        then(ticketService).shouldHaveNoInteractions();
    }

    private Order givenOrder(Customer customer) {
        Order order = new Order(SAVED);
        order.setCustomer(customer);
        return orderRepository.save(order);
    }

    private Customer givenCustomer(int rewardPoints) {
        Customer customer = new Customer("john@email.com");
        customer.setRewardPoints(BigDecimal.valueOf(rewardPoints));
        return customerRepository.save(customer);
    }

}
