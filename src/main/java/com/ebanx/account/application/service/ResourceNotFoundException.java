package com.ebanx.account.application.service;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(){
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
