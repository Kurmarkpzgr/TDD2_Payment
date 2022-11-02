package com.nhnacademy.tdd2;

public class NotEnoughCashException extends RuntimeException {

  public NotEnoughCashException() {
    super("Not Enough Cash");
  }
}
