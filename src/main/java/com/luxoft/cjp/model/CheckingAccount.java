package com.luxoft.cjp.model;

public class CheckingAccount extends AbstractAccount implements Account {

    private float overdraft;

    public void setOverdraft(float x){

    }

    @Override
    public void withdraw(float amount) throws OverdraftLimitExceededException {
        //TODO Fix the following logic:
        if (getBalance() - amount < -overdraft) throw new OverdraftLimitExceededException(overdraft, getBalance(), amount);
        setBalance(getBalance()- amount);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CheckingAccount ");
        sb.append("overdraft=").append(overdraft).append("\n");
        sb.append("balance=").append(getBalance());
        return sb.toString();
    }
}
