package com.luxoft.cjp.network;

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
 * TODO:Fix exception when there is space after name
 */
public class BankServer {
    private BankServiceImpl bsi ;
    private String message = "";
    private String outMessage = "";

    public BankServer(BankServiceImpl bsi) {
        this.bsi = bsi;
        run();
    }

    public void run(){
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
            outMessage = "Balance:" + c.getBalance() + " overdraft:" + c.getInitialOverdraft();//print his infp
        }
        try {
            if (messArr[0].matches("accounttype=.*")) {//if a feed detected
                BankFeedService.loadStringFeed(message, bsi);//make bank feed service take care of the feed
            }  else {
                outMessage = "Unknown command";
            }
        }catch (ArrayIndexOutOfBoundsException e){
            outMessage = "Wrong syntax";
        }

    }


}
