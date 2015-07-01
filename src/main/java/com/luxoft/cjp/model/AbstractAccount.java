package com.luxoft.cjp.model;

public abstract class AbstractAccount implements Account {

    private float balance;


    public float getBalance() {
        return balance;
    }


    public void deposit(float amount) {
        setBalance(getBalance() + amount);
    }


    public void printDecimalValue() {
        System.out.println(Math.round(getBalance()*100)/100);
    }


    public void setBalance(float balance) {
        this.balance = balance;
    }


    public void printReport(){
        System.out.print(getBalance());
    }


}

