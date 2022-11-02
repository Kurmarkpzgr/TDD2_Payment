package com.nhnacademy.tdd2;

public class InvalidUsingMileageException extends RuntimeException {

  public InvalidUsingMileageException(long usingMileage) {
    super("Invalid mileage using request error " + usingMileage + "is over amount or negative number");
  }

}
