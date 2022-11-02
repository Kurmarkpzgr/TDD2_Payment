package com.nhnacademy.tdd2;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CustomerTest {

  // SUT
  PaymentService service;
  // DOC
  CustomerRepository repository;
  Customer customer;

  @BeforeEach
  void setUp() {
    repository = mock(CustomerRepository.class);

    service = new PaymentService(repository);

    Long customerId = 3423432L;
    String password = "validPw";

    customer = new Customer(customerId, password);
  }

  @Test
  void checkRenewMileage() {
    long mileage = 500L;
    long originalMileage = customer.getMileage();

    customer.renewMileage(mileage);

    assertThat(customer.getMileage()).isEqualTo(originalMileage + mileage);
  }


  @Test
  void pay_checkReceipt() {
    when(repository.findById(customer.getCustomerId())).thenReturn(customer);

    Customer result = repository.findById(customer.getCustomerId());

    assertThat(result).isNotNull();
    assertThat(result.getMileage()).isNotNull();
    assertThat(result.getCash()).isNotNull();
    assertThat(result.getCustomerId()).isNotNull();
  }

  @Test
  void SMS_Check_SendMessage() {
    long mileage = 500L;
  }

}
