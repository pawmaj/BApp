package com.luxoft.cjp.dao;

/**
 * Created by pamajcher on 2015-07-01.
 */
public class BankNotFoundException extends Exception {
    private String nameNotFound;

    public String getNameNotFound() {
        return nameNotFound;
    }

    public BankNotFoundException(String nameNotFound){
        this.nameNotFound = nameNotFound;
    }
}
