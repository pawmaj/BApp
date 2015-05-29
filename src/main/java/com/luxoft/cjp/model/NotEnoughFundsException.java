package com.luxoft.cjp.model;

import com.luxoft.cjp.model.BankException;

/**
 * Created by pamajcher on 2015-05-22.
 */
public class NotEnoughFundsException extends BankException {

    private float amount;
    private float balance;

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }


    public NotEnoughFundsException(float amount, float balance) {
        this.amount = amount;
        this.balance = balance;
    }
}
