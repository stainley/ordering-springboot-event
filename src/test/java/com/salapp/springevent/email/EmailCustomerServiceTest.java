package com.salapp.springevent.email;

import com.salapp.springevent.customer.Customer;
import com.salapp.springevent.customer.CustomerRepository;
import com.salapp.springevent.customer.CustomerService;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class EmailCustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;

    @SpyBean
    private EmailService emailService;

    @Test
    void registerCustomer_forNewCustomer_sendsAnEmail() {
        // given
        Customer customer = new Customer("john@email.com");

        // when
        customerService.register(customer);

        // then
        BDDMockito.then(emailService).should(Mockito.times(1)).sendRegisterEmail(customer);
    }

    @Test
    void removeCustomer_forExistingCustomer_sendsAnEmail() {
        // given
        Customer customer = new Customer("john@email.com");

        // when
        customerService.remove(customer);

        // then
        BDDMockito.then(emailService).should(Mockito.times(1)).sendCustomerRemovedEmail(customer);
    }
}
