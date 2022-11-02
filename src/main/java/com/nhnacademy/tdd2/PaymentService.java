package com.nhnacademy.tdd2;

public class PaymentService {

  private final CustomerRepository customerRepository;

  public PaymentService(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  /**
   * 결제처리
   *
   * @param amount     결재 금액
   * @param customerId 고객 아이디
   * @return 영수증
   */
  public Receipt pay(long amount, Long customerId) {
    if (amount < 0) {
      throw new InvalidAmountException(amount);
    }

    Customer customer = customerRepository.findById(customerId);
    if (customer == null) {
      throw new CustomerNotFoundException(customerId);
    }
    if(customer.getCash() < amount){
      throw new NotEnoughCashException();
    }

    Receipt receipt = new Receipt(1L);
    return receipt;
  }
}
