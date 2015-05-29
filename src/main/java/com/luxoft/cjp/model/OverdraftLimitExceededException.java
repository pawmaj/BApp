package com.luxoft.cjp.model;

import com.luxoft.cjp.model.NotEnoughFundsException;

/**
 * Created by pamajcher on 2015-05-22.
 */
public class OverdraftLimitExceededException extends NotEnoughFundsException {


    private float amount;
    private float balance;
    private float overdraft;

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public float getOverdraft() {
        return overdraft;
    }

    public void setOverdraft(float overdraft) {
        this.overdraft = overdraft;
    }

    public OverdraftLimitExceededException(float overdraft, float balance1, float amount1) {
        super(amount1,balance1);
        this.overdraft = overdraft;
        this.balance = balance1;
        this.amount = amount1;
    }
}
