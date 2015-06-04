package com.luxoft.cjp.service;

import com.luxoft.cjp.model.Bank;
import com.luxoft.cjp.model.Client;
import com.luxoft.cjp.model.ClientExistsException;
import com.luxoft.cjp.model.accountTypes;

/**
 * Created by pamajcher on 2015-05-21.
 */
public interface BankService {

    public void addClient(Client client) throws ClientExistsException;

    public void removeClient(Bank bank,Client client);

    public void addAccount(Client client, accountTypes type);

    public void switchActiveAccount(Client client);

    //returns null if no such client is found
    public Client findClientByName(String name);

    //overloaded for searchning in any bank
    public Client findClientByName(String name, Bank b);

}