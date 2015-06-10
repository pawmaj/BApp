package com.luxoft.cjp.service;

import com.luxoft.cjp.model.Bank;

/**
 * Created by pamajcher on 2015-05-27.
 */
public class BankApplication {

    private static Bank currentBank = new Bank();
    private static BankServiceImpl bs = new BankServiceImpl(currentBank);

    public static void main(String[] args) {

        //Report mode:
        if (args.length > 1 && args[1].equals("-report")) {

            BankReport.getAccountsNumber(currentBank);
            BankReport.getBankCreditSum(currentBank);
            BankReport.getClientByCity(currentBank);
            BankReport.getNumberOfClients(currentBank);
            BankReport.getClientsSorted(currentBank);
            System.exit(0);
        }
        //Test feeds:
       TestSerialization.test(bs);

       //Interactive Mode. Client from the feed is already in it.
        BankCommander.runInteractiveMode(bs);





    }
}



