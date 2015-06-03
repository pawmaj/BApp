package com.luxoft.cjp.service;

import com.luxoft.cjp.model.BankException;
import com.luxoft.cjp.model.Client;
import com.luxoft.cjp.model.Command;
import com.luxoft.cjp.model.OverdraftLimitExceededException;

/**
 * Created by pamajcher on 2015-05-27.
 */
public class WithdrawCommand implements Command {
    private BankServiceImpl bs;
    private Client c;
    private float amount;

    public BankServiceImpl getBs() {
        return bs;
    }

    public Client getClient() {
        return c;
    }

    public WithdrawCommand(){}

    public WithdrawCommand(BankServiceImpl bs, Client c, float amount) throws InvalidBankArgumentException {
        if (amount <= 0) throw new InvalidBankArgumentException(String.valueOf(amount));
        this.amount = amount;
        this.bs = bs;
        this.c = c;
    }

    public Boolean execute() {
        try {
           c.withdraw(amount);
            return true;
        } catch (OverdraftLimitExceededException e) {
           //print all information about failed withdrawal
            System.out.println(" Balance:"+e.getBalance()
                    +" Overdraft:"+e.getOverdraft()
                    +" Cannot withdraw "+e.getAmount()
                    +" now. We hardly appreciate your custom.");
        } catch (BankException e) {
            e.printStackTrace();
        }finally{
            return false;
        }
    }


    public void printCommandInfo() {
        System.out.println("Attempts to withdraw. Usage: 4 [name] [amount]");

    }
}
