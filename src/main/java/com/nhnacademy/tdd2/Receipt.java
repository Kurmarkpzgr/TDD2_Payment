package com.nhnacademy.tdd2;

import java.time.LocalDateTime;

//userId, time, totalPrice, getPrice, change, mileage
public class Receipt {
  private final long customerId;
  private final LocalDateTime purchaseTime;
  private final long totalPrice;
  private final long customerCash;
  private final long exchange;
  private final long mileage;

  public Receipt(long customerId, LocalDateTime purchaseTime, long totalPrice, long customerCash,
      long exchange, long mileage) {
    this.customerId = customerId;
    this.purchaseTime = purchaseTime;
    this.totalPrice = totalPrice;
    this.customerCash = customerCash;
    this.exchange = exchange;
    this.mileage = mileage;
  }

  public long getCustomerId() {
    return customerId;
  }

  public long getMileage() {
    return mileage;
  }

  public String toString() {
    return "Receipt = {" +
        "customerId=" + this.customerId + ", " +
        "purchaseTime=" + this.purchaseTime + ", " +
        "totalPrice=" + this.totalPrice + ", " +
        "customerCash=" + this.customerCash + ", " +
        "exchange=" + this.exchange+ ", " +
        "mileage=" + this.mileage + "}";
  }
}
