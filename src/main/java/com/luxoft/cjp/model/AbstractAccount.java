package com.luxoft.cjp.model;

public abstract class AbstractAccount implements Account {

    private float balance;

    @Override
    public float getBalance() {
        return balance;
    }

    @Override
    public void deposit(float amount) {
        setBalance(getBalance() + amount);
    }

    @Override
    public void printDecimalValue() {
        System.out.println(Math.round(getBalance()*100)/100);
    }


    public void setBalance(float balance) {
        this.balance = balance;
    }

    @Override
    public void printReport(){
        System.out.print(getBalance());
    }


}

