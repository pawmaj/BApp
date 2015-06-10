package com.luxoft.cjp.service;

import com.luxoft.cjp.model.BankException;
import com.luxoft.cjp.model.Client;
import com.luxoft.cjp.model.Command;
import com.luxoft.cjp.model.OverdraftLimitExceededException;

/**
 * Created by pamajcher on 2015-05-27.
 */
public class TransferCommand implements Command {

    private Client clientFrom;

    public Client getClientFrom() {
        return clientFrom;
    }

    public Client getClientTo() {
        return clientTo;
    }

    private Client clientTo;
    private float amount;


    public TransferCommand(BankServiceImpl bs, Client clientFrom, Client clientTo, float amount) {
        this.clientFrom = clientFrom;
        this.clientTo = clientTo;
        this.amount = amount;
    }


    public Boolean execute() {
        try {
            clientFrom.withdraw(amount);
            clientTo.deposit(amount);
            return true;
        } catch (OverdraftLimitExceededException e) {
            //print all information about failed transfer
            System.out.println("You can't give what you don't have."
                    +" Balance:"+e.getBalance()
                    +" Overdraft:"+e.getOverdraft()
                    +" Can't transfer "+e.getAmount()
                    +" now.");
        } catch (BankException e) {
            e.printStackTrace();
        }finally{
            return false;
        }
    }


    public void printCommandInfo() {
        System.out.println("Attempts to transfer between the clients' active accounts. Returns true/false on success/failure");

    }
}


