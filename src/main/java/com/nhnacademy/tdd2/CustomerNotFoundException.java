package com.nhnacademy.tdd2;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(Long customerId) {
        super("Not found customer: " + customerId);
    }
}
