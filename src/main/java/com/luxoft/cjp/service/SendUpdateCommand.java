package com.luxoft.cjp.service;

import com.luxoft.cjp.model.Client;
import com.luxoft.cjp.model.Command;
import com.luxoft.cjp.network.BankRemoteOffice;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Set;

/**
 * Created by pamajcher on 2015-06-19.
 */
public class SendUpdateCommand implements Command {
    //domain-specfic fields:
    private Set<Client> clientsToSend;

    //network technical fields:
    String messageToSend;
    ObjectOutputStream oos;


    public SendUpdateCommand(BankServiceImpl bs){
       clientsToSend = bs.getBank().getClients();

    }

    public Object execute() {
        messageToSend ="";
        oos = null;
        try {
            Socket requestSocket = new Socket("localhost",2004);
            oos = new ObjectOutputStream(requestSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Connected to the server");
        try {
            for (Client c:clientsToSend){//for every client
                messageToSend = BankRemoteOffice.serializeClientToString(c);
                oos.writeObject(messageToSend);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void printCommandInfo() {
    System.out.println("Connect to the central bank and send updates");
    }

}
