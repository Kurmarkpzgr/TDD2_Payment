package com.nhnacademy.tdd2;

public class NotEnoughMileageException extends RuntimeException{

  public NotEnoughMileageException() {
    super("Not enough mileage");
  }
}
