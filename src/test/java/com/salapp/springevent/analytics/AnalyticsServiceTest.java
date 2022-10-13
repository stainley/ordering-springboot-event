package com.salapp.springevent.analytics;

import com.salapp.springevent.customer.Customer;
import com.salapp.springevent.customer.CustomerRepository;
import com.salapp.springevent.customer.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.timeout;

@SpringBootTest
class AnalyticsServiceTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    @SpyBean
    private AnalyticsService analyticsService;

    @Test
    void registerCustomer_forNewCustomer_callAnalytics() {

        // given
        Customer customer = new Customer("john@email.com");
        customerRepository.save(customer);

        // when
        customerService.register(customer);

        // then
        then(analyticsService).should(timeout(100)).registerNewCustomers(customer);
    }
}
