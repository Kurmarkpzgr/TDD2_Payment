package com.nhnacademy.tdd2;

import java.time.LocalDateTime;

public class PaymentService {

  private final CustomerRepository customerRepository;

  //마일리지 미사용
  public PaymentService(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  public Receipt pay(long amount, Long customerId) {
    if (amount < 0) {
      throw new InvalidAmountException(amount);
    }
    Customer customer = customerRepository.findById(customerId);

    if (customer == null) {
      throw new CustomerNotFoundException(customerId);
    }

    long cash = customer.getCash();

    if (cash < amount){
      throw new NotEnoughCashException();
    }

    long exchange = exchangeCalculator(cash, amount);

    long mileage = mileageCalculator(amount);

    LocalDateTime purchaseTime = LocalDateTime.now();

    customer.setCash(exchange);
    return new Receipt(customerId, purchaseTime, cash, amount, exchange, mileage);
  }


  /**
   * 결제처리
   *
   * @param amount     결재 금액
   * @param customerId 고객 아이디
   * @return 영수증
   */

  //마일리지 사용
  public Receipt pay(long amount, Long customerId, long usingMileage) {
    if(usingMileage < 0 || amount < usingMileage ) {
      throw new InvalidUsingMileageException(usingMileage);
    }

    if (amount < 0) {
      throw new InvalidAmountException(amount);
    }
    Customer customer = customerRepository.findById(customerId);

    if (customer == null) {
      throw new CustomerNotFoundException(customerId);
    }

    if(customer.getMileage() <usingMileage){
      throw new NotEnoughMileageException();
    }
    long cash = customer.getCash();

    amount = useMileage(amount,usingMileage);

    if (cash < amount){
      throw new NotEnoughCashException();
    }

    long exchange = exchangeCalculator(cash, amount);

    long mileage = mileageCalculator(amount);

    LocalDateTime purchaseTime = LocalDateTime.now();

    customer.renewMileage(mileage-usingMileage);
    customer.setCash(exchange);
    return new Receipt(customerId, purchaseTime, cash, amount, exchange, mileage);
  }




  public long mileageCalculator(long amount) {
    double mileagePercent = 0.01;

    return (long) (amount * mileagePercent);
  }

  public long exchangeCalculator(long cash, long amount){
    return cash - amount;
  }

  public void sendMessage(String phoneNumber){
    System.out.println(phoneNumber + "결재 성공했습니다.");
  }

  public long useMileage(long amount, long usingMileage) {
    return amount - usingMileage;
  }
}
