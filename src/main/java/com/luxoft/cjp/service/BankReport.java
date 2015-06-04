package com.luxoft.cjp.service;

import com.luxoft.cjp.model.Account;
import com.luxoft.cjp.model.Bank;
import com.luxoft.cjp.model.CheckingAccount;
import com.luxoft.cjp.model.Client;

import java.util.*;

/**
 * Created by pamajcher on 2015-06-03.
 */
public class BankReport {

    public static int getNumberOfClients(Bank b){
        int size = b.getClients().size();
        System.out.println("Number of clients:" + String.valueOf(size));
        return size;
    }
    public static int getAccountsNumber(Bank b){
        int accountCount = 0;
        for (Client c:b.getClients()){
            for(Account acc:c.getAccounts()) accountCount++;
        }
        System.out.println("Number of accounts: " + String.valueOf(accountCount));
        return accountCount;
    }
    public static Set<Client> getClientsSorted(Bank  b){
        System.out.println(b.getClients());
        //TODO fix it
        return null;
    }
    public static float getBankCreditSum(Bank b){
        float creditSum = 0.0F;
        for(Client c:b.getClients()){ //of all clients,
            for (Account a:c.getAccounts()){//of all accounts,
                if(a instanceof CheckingAccount){//if it's a checking account
                    creditSum+= a.getBalance()+((CheckingAccount) a).getOverdraft();//get available credit and sum it up
                }
            }
        }
        return creditSum;
    }

    public static Map<String,List<Client>> getClientByCity(Bank b){

        Map<String,List<Client>> cityClientsMap = new HashMap<String, List<Client>>();
        for(Client c : b.getClients()){
            if (!cityClientsMap.containsKey(c.getCity()))cityClientsMap.put(c.getCity(), new LinkedList<Client>());
                cityClientsMap.get(c.getCity()).add(c);
            }
        return cityClientsMap;
    }
}
