package com.nhnacademy.tdd2;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;

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

    Long customerId = 3423432L;
    String password = "validPw";

    customer = new Customer(customerId, password);

//    sms = new fakeSMS(customerId);
  }

  @Test
  void sendMessageFailure() {
    SMS fakeSms = new FakeSms();

    customer.setPhoneNumber(null);
    assertThatThrownBy(() -> fakeSms.sendMessage(customer)).isInstanceOf(
        NoSuchPhoneNumberException.class).hasMessageContaining("No Such PhoneNumber");


  }

  @Test
  void sendMessageSuccess() {
    SMS fakeSms = new FakeSms();

    customer.setPhoneNumber("010-1234-1234");
    assertThat(fakeSms.sendMessage(customer)).hasMessageContaining("Payment Finished");

  }
}

class FakeSms implements SMS {

  @Override
  public void sendMessage(Customer customer) {
    if (customer.getPhoneNumber() == null) {
      throw new NoSuchPhoneNumberException();
    }
    System.out.println(customer.getCustomerId() + "Payment Finished");
  }
}


