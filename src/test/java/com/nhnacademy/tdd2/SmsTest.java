package com.nhnacademy.tdd2;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SmsTest {

  SMS sms;
  // SUT
  PaymentService service;
  // DOC
  CustomerRepository repository;
  Customer customer;

  @BeforeEach
  void setUp() {
    repository = mock(CustomerRepository.class);
    service = new PaymentService(repository);


//    sms = new fakeSMS(customerId);
  }

  @Test
  void sendMessageFailure() {
    SMS fakeSms = new FakeSms();
    long amount = 1_000L;
    Long customerId = 3423432L;
    String password = "validPw";

    customer = new Customer(customerId, password);
    customer.setCash(1_500L);

    when(repository.findById(customerId)).thenReturn(customer);
//    when(service.pay(amount, customerId)).thenReturn(null);
    Receipt receipt = service.pay(amount, customerId);

    assertThatThrownBy(() -> fakeSms.sendMessage(receipt))
        .isInstanceOf(NullReceiptException.class)
        .hasMessageContaining("Null Receipt Exception");
  }

//  @Test
//  void sendMessageSuccess() {
//    SMS fakeSms = new FakeSms();
//
//    customer.setPhoneNumber("010-1234-1234");
//    assertThat(fakeSms.sendMessage(customer)).hasMessageContaining("Payment Finished");
//
//  }
}

class FakeSms implements SMS {

  @Override
  public void sendMessage(Receipt receipt) {
    if (Objects.isNull(receipt)) {
      throw new NullReceiptException();
    }
    System.out.println("Payment Finished");
  }
  }

