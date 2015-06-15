package com.luxoft.cjp.service;


import com.luxoft.cjp.model.Bank;
import com.luxoft.cjp.network.Server;

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

        //Run server:
        //Example commands: John,withdraw,10
         //                 John,info
        com.luxoft.cjp.network.Server s = new Server(bs);

       //Interactive Mode. Client from the feed is already in it.
        BankCommander.runInteractiveMode(bs);





    }
}



