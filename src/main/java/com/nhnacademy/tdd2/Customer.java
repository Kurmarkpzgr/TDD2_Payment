package com.nhnacademy.tdd2;

public class Customer {

  long customerId;
  String password;
  long mileage;
  long cash;

  public Customer(long customerId, String password) {
    this.customerId = customerId;
    this.password = password;
  }


  public long getCash() {
    return this.cash;
  }

  public void setCash(long cash) {
    this.cash = cash;
  }

  public long getMileage() {
    return mileage;
  }

  public void renewMileage(long mileage) {
    this.mileage += mileage;
  }

  public long getCustomerId() {
    return this.customerId;
  }
}
