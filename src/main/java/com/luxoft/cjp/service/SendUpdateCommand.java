package com.luxoft.cjp.service;

import com.luxoft.cjp.model.Client;
import com.luxoft.cjp.model.Command;

import java.util.Set;

/**
 * Created by pamajcher on 2015-06-19.
 */
public class SendUpdateCommand implements Command {
    private Set<Client> clientsToSend;

    public SendUpdateCommand(BankServiceImpl bs){
       clientsToSend = bs.getBank().getClients();

    }//empty constructor for the menu

    public Object execute() {
        return null;
    }

    public void printCommandInfo() {
    System.out.println("Connect to the central bank and send updates");
    }
}
