package com.luxoft.cjp.service;

import com.luxoft.cjp.model.Bank;
import com.luxoft.cjp.model.Client;
import com.luxoft.cjp.model.ClientExistsException;
import com.luxoft.cjp.model.accountTypes;

/**
 * Created by pamajcher on 2015-05-21.
 */
public class BankServiceImpl implements BankService {

    private Bank b;

    public BankServiceImpl(Bank b) {
        this.b = b;
    }


    public void addClient(Client client) throws ClientExistsException{
    b.addClient(client);
    }


    public void removeClient(Bank bank, Client client) {
        bank.clients.remove(client);

    }


    public void addAccount(Client client, accountTypes type) {
        client.createAccount(type);
    }


    public void setActiveAccount(Client client, int number) {
        client.setActiveAccount(client.getAccounts().get(number));
    }


    public Client findClientByName(String name) {
        for (Client c: b.getClients()){
            if(c.getName().equals(name)) return c;
        }
        return null;
    }
    //Overloaded for searching in any bank
    public Client findClientByName(String name, Bank b) {
        for (Client c: b.getClients()){
            if(c.getName().equals(name)) return c;
        }
        return null;
    }
    public void report(){
        b.printReport();
    }
}
//^Hi,\\s*[A-Z][a-z]{2,} [A-Z][a-z]{2,}\\s*!$