package com.bridgelabz.addressbookapp.exception;

public class AddressBookException extends RuntimeException {

    public String message;
    public ExceptionType type;

    public AddressBookException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }

    public enum ExceptionType{
        USER_NOT_FOUND
    }

}
