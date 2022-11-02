package com.nhnacademy.tdd2;

public interface CustomerRepository {
    Customer findById(Long customerId);
}
