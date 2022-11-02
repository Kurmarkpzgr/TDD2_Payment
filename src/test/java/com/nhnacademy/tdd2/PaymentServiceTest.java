package com.nhnacademy.tdd2;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PaymentServiceTest {

  // SUT
  PaymentService service;
  // DOC
  CustomerRepository repository;

  @BeforeEach
  void setUp() {
    repository = mock(CustomerRepository.class);

    service = new PaymentService(repository);
  }

  //계정 존재 여부 체크
  @Test
  void pay_notFoundCustomer_thenThrowCustomerNotFoundException() {
    long amount = 10_000L;
    Long customerId = 3423432L;

    when(repository.findById(customerId)).thenReturn(null);

    assertThatThrownBy(() -> service.pay(amount, customerId))
        .isInstanceOf(CustomerNotFoundException.class)
        .hasMessageContaining("Not found customer", customerId.toString());
  }

  //계정 존재 테스트
  @Test
  void pay_foundCustomer() {
    long amount = 10_000L;
    Long customerId = 3423432L;
    String password = "validPw";

    Customer customer = new Customer(customerId, password);

    when(repository.findById(customerId)).thenReturn(customer);
  }

  //결제 금액 음수 테스트
  @Test
  void pay_invalidAmount_thenThrowInvalidAmountException() {
    long amount = -1L;
    Long customerId = 3423432L;

    //when(service.pay(amount, customerId)).thenReturn(null);

    assertThatThrownBy(() -> service.pay(amount, customerId)).isInstanceOf(
        InvalidAmountException.class).hasMessageContaining("Invalid amount request", amount);
  }

  @Test
  void pay_notEnoughCash_thenThrowNotEnoughCashException(){
    long amount = 1000L;
    Long customerId = 3423432L;
    String password = "validPw";

    Customer customer = new Customer(customerId, password);
    customer.setCash(999L);
    when(repository.findById(customerId)).thenReturn(customer);

    assertThatThrownBy(()-> service.pay(amount,customerId)).isInstanceOf(NotEnoughCashException.class)
        .hasMessageContaining("Not Enough Cash");
  }

  @Test
  void pay_CheckMileage(){
    long amount = 1000L;
    Long customerId = 3423432L;
    String password = "validPw";

    Customer customer = new Customer(customerId, password);
    customer.setCash(2000L);
    when(repository.findById(customerId)).thenReturn(customer);

    Receipt receipt = service.pay(amount, customerId);

    assertThat(receipt).isNotNull();
    assertThat(receipt.getMileage()).isEqualTo(1L);
  }

  @Test
  void pay_Use_Mileage(){
    long amount = 1000L;
    Long customerId = 3423432L;
    String password = "validPw";

    Customer customer = new Customer(customerId, password);
    customer.setCash(2000L);
    when(repository.findById(customerId)).thenReturn(customer);

    service.pay(amount,customerId);


  }

//  결제 확인
//  @Test
//  void paySuccess(){
//    long amount = 10_000L;
//    Long customerId = 3423432L;
//    String password = "validPw";
//
//    Customer customer = new Customer(customerId, password);
//
//    when(repository.findById(customerId)).thenReturn(customer);
//    service.pay(amount,customerId))thenReturn(new Receipt(1L)
//
//  }
}