package com.luxoft.cjp.network;

import com.luxoft.cjp.model.Bank;
import com.luxoft.cjp.model.BankException;
import com.luxoft.cjp.service.BankFeedService;
import com.luxoft.cjp.service.BankServiceImpl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/** if asked from client to withdraw
 * -if client has more than 1 account send number of accounts to be managed
 * -or take a look only at first account
 * Created by pamajcher on 2015-06-11.
 */
public class BankServer {
    // static BankServiceImpl bsi ;
    private String outMessage;

    public BankServer(BankServiceImpl bsi) {
        String message = "";
        outMessage = "";
        try {
            ServerSocket providerSocket = new ServerSocket(2004, 10);
            Socket connection = providerSocket.accept();
            ObjectInputStream ois = new ObjectInputStream(connection.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(connection.getOutputStream());
            do {
                message = (String) ois.readObject();
                System.out.println("Received: " + message);//print the received string
                processAndExecuteMessage(message, bsi);
                oos.writeObject(outMessage);
            } while (!message.equals("exit"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void processAndExecuteMessage(String message, BankServiceImpl bsi) {
        String[] messArr = message.split(",");
        //parse message array

        com.luxoft.cjp.model.Client c = bsi.getClient(messArr[0]);

            if (c != null) {//if client found

            }//TODO: Just ask for informaion
         if (messArr[1].equals("info"))//get funds info
        {
            outMessage = "Balance:" + c.getBalance() + " overdraft:" + c.getInitialOverdraft();
        }
         /*else if (messArr[0].matches("accounttype=.*")) {//if a feed detected
        BankFeedService.loadStringFeed(message,bsi);//make bank feed service take care of the feed
        }*/else{
                outMessage = "Unknown command";
            }

    }


    public static void main(final String args[]) {
        Bank b = new Bank();
        BankServiceImpl bsi  = new BankServiceImpl(b);
        BankServer s = new BankServer(bsi);
    }
}
