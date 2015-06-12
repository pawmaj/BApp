package com.luxoft.cjp.network;

import com.luxoft.cjp.model.*;
import com.luxoft.cjp.service.BankServiceImpl;
import com.luxoft.cjp.service.InvalidBankArgumentException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by pamajcher on 2015-06-11.
 */
public class Server {
    static BankServiceImpl bsi ;

    public Server(){
        String message = "";
        try {
            ServerSocket providerSocket = new ServerSocket(2222,10);
            Socket connection = providerSocket.accept();
            ObjectInputStream ois = new ObjectInputStream(connection.getInputStream());
            do {
                message = (String) ois.readObject();
                System.out.println("Received: " + message);//syntac
                processAndExecuteMessage(message);
            }while (!message.equals("exit"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void processAndExecuteMessage(String message) {
        String[] messArr = message.split(" ");
        //parse message array

        com.luxoft.cjp.model.Client c = bsi.getClient(messArr[0]);
        if (c != null) {//if client found
            if (messArr[1].equals("withdraw")) try {//withdraw
                c.withdraw(Float.valueOf(messArr[2]));
            } catch (BankException e) {
                System.out.print("Not enough funds");
            }//end withdraw
        } else {
            try {
                bsi.addClient(new com.luxoft.cjp.model.Client(
                        messArr[0],//name
                        Float.valueOf(messArr[1]),//overdraft
                        messArr[2],//electronic address
                        messArr[3],//phone number
                        messArr[4],//city
                        messArr[5]
                        ));
            } catch (InvalidBankArgumentException e) {
                //TODO send message
                e.printStackTrace();
            } catch (ClientExistsException e) {
                //TODO send messaged
                e.printStackTrace();
            }
        }
    }

    public static void main(final String args[]) {
        Bank b = new Bank();
        bsi  = new BankServiceImpl(b);
        com.luxoft.cjp.network.Server s = new Server();
    }
}
