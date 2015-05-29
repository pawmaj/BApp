package com.luxoft.cjp.service;

import com.luxoft.cjp.model.Client;
import com.luxoft.cjp.model.Command;

/**
 * Created by pamajcher on 2015-05-27.
 */
public class FindClientCommand implements Command {

    private BankServiceImpl bs;
    private String clientNameToFind;

    public BankServiceImpl getBs() {
        return bs;
    }


    public FindClientCommand(){}

    public FindClientCommand(BankServiceImpl bs, String clientNameToFind) {
        this.bs = bs;
        this.clientNameToFind = clientNameToFind;
    }

    @Override
    public Object execute() {
        Client c = bs.findClientByName(clientNameToFind);
        if (c != null) {
            System.out.println("Client found:");
            c.printReport();
            return true;
        }else {
            System.out.println("No " + clientNameToFind +" among clients.");
            return false;
        }


    }
    //RETURNS NULL IF NO SUCH NAME!
    @Override
    public void printCommandInfo() {
        System.out.println("Print a Client's info. Usage: 3 [name]");

    }
}
