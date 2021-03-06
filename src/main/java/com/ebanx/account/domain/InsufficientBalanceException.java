package com.ebanx.account.domain;

public class InsufficientBalanceException extends RuntimeException {

    public InsufficientBalanceException(){
        super("", new Throwable("0"), true,false);
    }

    @Override
    public String getMessage() {
        return "0";
    }

    @Override
    public synchronized Throwable getCause() {
        return new Throwable("0");
    }

}
