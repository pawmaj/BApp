package com.luxoft.cjp.model;

/**
 * Created by pamajcher on 2015-05-21.
 */


public interface Account extends Report {

    float getBalance();

    void deposit(float amount);

    void withdraw(float amount) throws NotEnoughFundsException;

    //prints rounded balance
    void printDecimalValue();

    float getOverdraft();
}


