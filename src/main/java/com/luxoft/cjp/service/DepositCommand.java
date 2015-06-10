package com.luxoft.cjp.service;

import com.luxoft.cjp.model.Client;
import com.luxoft.cjp.model.Command;

/**
 * Created by pamajcher on 2015-05-27.
 */
public class DepositCommand implements Command {

    private Client c;
    private float amount;


    public Client getClient() {
        return c;
    }

    public DepositCommand(Client c, float amount) throws InvalidBankArgumentException {
        if (amount <= 0) throw new InvalidBankArgumentException(String.valueOf(amount));
        this.c = c;
        this.amount = amount;
    }
    public DepositCommand(){}


    public Object execute() {
        c.deposit(amount);
        return true;
    }


    public void printCommandInfo() {
        System.out.println("Deposits on currently active account. Usage: 2 [name] [amount]. [name] must be of an existing client.");

    }
}


