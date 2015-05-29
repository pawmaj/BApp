package com.luxoft.cjp.service;

import com.luxoft.cjp.model.Client;
import com.luxoft.cjp.model.ClientExistsException;
import com.luxoft.cjp.model.Command;

/**
 * Created by pamajcher on 2015-05-27.
 */
public class AddClientCommand implements Command {
    private BankServiceImpl bs;
    private Client c;

    public BankServiceImpl getBs() {
        return bs;
    }

    public Client getClient() {
        return c;
    }

    public AddClientCommand(BankServiceImpl bs, Client c) {
        this.bs = bs;
        this.c = c;
    }
    public AddClientCommand() {
    //this empty constructor is for the menu
    // The command will be constructed again, once the user inputs necessary arguments
    }

    @Override
    public Boolean execute() {
        try {
            bs.addClient(c);
            return true;
        }catch (ClientExistsException e){
            System.out.println("Client "+ e.getExistingClientName() + " exists.");
            return false;
        }
    }

    @Override
    public void printCommandInfo() {
        System.out.println("Register a client. Usage: 0 [name] [initial overdraft] [e-mail address] [telephone] ['m'/'f']");
    }
}
