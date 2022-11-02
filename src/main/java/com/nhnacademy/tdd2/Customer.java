package com.nhnacademy.tdd2;

public class Customer {
  long customerId;
  String password;
  long mileage;
  long cash;
  String phoneNumber;

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getPhoneNumber() {
    return this.phoneNumber;
  }

  public long getCustomerId() {
    return customerId;
  }

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

  public void addMileage(long mileage) {
    this.mileage += mileage;
  }
}
