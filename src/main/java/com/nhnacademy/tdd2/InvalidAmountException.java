package com.nhnacademy.tdd2;

public class InvalidAmountException extends RuntimeException {

  public InvalidAmountException(long amount) {
    super("Invalid amount request " + amount);
  }
}
