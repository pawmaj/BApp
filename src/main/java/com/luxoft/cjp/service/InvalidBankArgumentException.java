package com.luxoft.cjp.service;

/**
 * Created by pamajcher on 2015-05-29.
 */
public class InvalidBankArgumentException extends Exception {


    private String invalidArg;
    public String getInvalidArg() {
        return invalidArg;
    }
    public InvalidBankArgumentException(String arg){
        this.invalidArg = arg;
    }
}
