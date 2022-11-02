package com.nhnacademy.tdd2;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
  void check_mileage(){
    long mileage = 500L;
    long originalMileage =  customer.getMileage();

    customer.addMileage(mileage);

    assertThat(customer.getMileage()).isEqualTo(originalMileage + mileage);
  }

  @Test
  void use_Mileage(){

    long amount = 2000L;
    long usingMileage = 1000L;
    long mileage = 500L;

    customer.addMileage(mileage);

    assertThatThrownBy(() -> service.useMileage(amount, usingMileage)).isInstanceOf(
            NotEnoughMileageException.class).hasMessageContaining("Not enouch mileage");

    verify(customer,times(1)).useMileage(any());

  }
  @Test
  void SMS_Check_SendMessage(){
    long mileage = 500L;
  }

}
