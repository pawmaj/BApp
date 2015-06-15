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

/**
 * Created by pamajcher on 2015-06-11.
 */
public class Server {
    // static BankServiceImpl bsi ;
    private String outMessage;

    public Server(BankServiceImpl bsi) {
        String message = "";
        outMessage = "";
        try {
            ServerSocket providerSocket = new ServerSocket(2222, 10);
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
                try{
                if (messArr[1].equals("withdraw"))//withdraw

                    c.withdraw(Float.valueOf(messArr[2]));
                outMessage = "Withdraw successfull";
                System.out.println("Withdraw Successful");
            }catch(BankException e){
                outMessage = "Not enough funds";
                System.out.print("Not enough funds");
            }catch(IndexOutOfBoundsException e){
                outMessage = "Wrong syntax";
            }
         if (messArr[1].equals("info"))//get funds info
        {
            outMessage = "Balance:" + c.getBalance() + " overdraft:" + c.getInitialOverdraft();
        }

        } else if (messArr[0].matches("accounttype=.*")) {//if a feed detected
        BankFeedService.loadStringFeed(message,bsi);//make bank feed service take care of the feed
        }else{
                outMessage = "Unknown command";
            }

    }


    public static void main(final String args[]) {
        Bank b = new Bank();
        BankServiceImpl bsi  = new BankServiceImpl(b);
        com.luxoft.cjp.network.Server s = new Server(bsi);
    }
}
