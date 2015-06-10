package com.luxoft.cjp.service;

import com.luxoft.cjp.model.Client;
import com.luxoft.cjp.model.Command;

/**
 * Created by pamajcher on 2015-05-28.
 */
public class AddAccountCommand implements Command {
    private String typeOfAccountToAdd;
    private Client c;


    public Client getClient() {
        return c;
    }

    public AddAccountCommand(Client c, String ta) {
        this.c = c;
        this.typeOfAccountToAdd = ta;
    }
    public AddAccountCommand() {
        //this empty constructor is for the menu
        // The command will be constructed again, once the user provides all necessary arguments
    }


    public Boolean execute() {
        if (typeOfAccountToAdd.equals("saving")) { c.createAccount("saving"); return true; }
        if (typeOfAccountToAdd.equals("checking")){c.createAccount("saving"); return true; }
        return false;//fails if user provides wrong input
    }


    public void printCommandInfo() {
        System.out.println("Add an account and set it as active. Usage: 1 [name] ['saving'/'checking']. [name] must be of an existing client.");
    }
}

