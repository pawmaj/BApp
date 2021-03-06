package com.luxoft.cjp.model;

public class SavingAccount extends AbstractAccount implements Account {

    private int id;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public synchronized void withdraw(float amount) throws NotEnoughFundsException {
        if(getBalance() >= amount) {
            setBalance(getBalance() - amount);
        }else{
            throw new NotEnoughFundsException(amount, getBalance());
        }
    }

    public float getOverdraft() {
        return 0F;
    }

    @Override
    public String getUser() {
        return null;
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

