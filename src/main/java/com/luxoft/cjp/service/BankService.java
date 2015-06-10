package com.luxoft.cjp.service;

import com.luxoft.cjp.model.Bank;
import com.luxoft.cjp.model.Client;
import com.luxoft.cjp.model.ClientExistsException;

/**
 * Created by pamajcher on 2015-05-21.
 */
public interface BankService {

    public void addClient(Client client) throws ClientExistsException;

    public void removeClient(Bank bank,Client client);

    public void addAccount(Client client, String type);

    public void switchActiveAccount(Client client);

    //returns null if no such client is found
    public Client getClient(String name);

    //overloaded for searchning in any bank
    public Client getClient(String name, Bank b);

    public void saveClient(Client c, String folder);

    public void loadClient(String folder);

}

