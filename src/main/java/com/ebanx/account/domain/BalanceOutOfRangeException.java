package com.ebanx.account.domain;

public class BalanceOutOfRangeException extends RuntimeException {

    public BalanceOutOfRangeException(){
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
