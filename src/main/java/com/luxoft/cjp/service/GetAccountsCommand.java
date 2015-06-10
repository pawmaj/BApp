package com.luxoft.cjp.service;

import com.luxoft.cjp.model.Client;
import com.luxoft.cjp.model.Command;

/**
 * Created by pamajcher on 2015-05-27.
 */
public class GetAccountsCommand implements Command {

    private Client c;



    public Client getClient() {
        return c;
    }

    public GetAccountsCommand(Client c) {
        this.c = c;
    }

    @Override
    public Object execute() {
        return c.getAccounts();
    }

    //TODO make it return an empty list if a client has no accounts yet
    @Override
    public void printCommandInfo() {
        System.out.println("Returns LinkedList of client's accounts");

    }
}

