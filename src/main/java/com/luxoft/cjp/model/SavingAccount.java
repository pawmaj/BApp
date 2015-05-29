package com.luxoft.cjp.model;

import com.luxoft.cjp.model.AbstractAccount;
import com.luxoft.cjp.model.Account;
import com.luxoft.cjp.model.NotEnoughFundsException;

public class SavingAccount extends AbstractAccount implements Account {


    public SavingAccount(float initialBalance){
        setBalance(initialBalance);
    }


    public void withdraw(float amount) throws NotEnoughFundsException {
        if(getBalance() >= amount) {
            setBalance(getBalance() - amount);
        }else{
            throw new NotEnoughFundsException(amount, getBalance());
        }
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SavingAccount ");
        sb.append("balance =");
        sb.append(getBalance());
        return sb.toString();
    }
}
