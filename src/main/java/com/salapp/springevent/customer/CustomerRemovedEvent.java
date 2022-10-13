package com.salapp.springevent.customer;

import lombok.Data;

@Data
public class CustomerRemovedEvent {

    private final Customer customer;
}
