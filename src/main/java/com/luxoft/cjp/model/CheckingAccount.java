package com.luxoft.cjp.model;

public class CheckingAccount extends AbstractAccount implements Account {
    //TODO override the deposit() function to auto-repay overdraft

    private float overdraft;

    public void setOverdraft(float x){
    this.overdraft = x;
    }

    public float getOverdraft() {
        return overdraft;
    }
    public CheckingAccount(float overdraft){
        this.overdraft = overdraft;
    }

    public void withdraw(float amount) throws OverdraftLimitExceededException {

        if (getBalance()- amount < 0) {
            if (overdraft + getBalance() < amount)
                throw new OverdraftLimitExceededException(overdraft, getBalance(), amount);
        setOverdraft(overdraft+getBalance()-amount);
        }else {
            setBalance(getBalance() - amount);
        }
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
