package com.nhnacademy.tdd2;

public class NoSuchPhoneNumberException extends RuntimeException {
    public NoSuchPhoneNumberException() {
        super("No Such PhoneNumber");
    }
}
