package com.luxoft.cjp.network;

import com.luxoft.cjp.model.Bank;
import com.luxoft.cjp.service.BankService;
import com.luxoft.cjp.service.BankServiceImpl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by pamajcher on 2015-06-22.
 */
public class ServerThread {
    BankServiceImpl bsi = null;
    Socket clientSocket;
    private String message;
    private String outMessage;

    public void setBankService(BankServiceImpl bs, Socket clientSocket) {
        this.bsi = bs;
    }
    public ServerThread (Socket clientSocket){
        this.clientSocket = clientSocket;
    }

    public void run(){
        BankServer server = new BankServer(bsi);
        try {
            ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
            do {
            message = (String) ois.readObject();
            System.out.println("Received: " + message);//print the received string
            server.processAndExecuteMessage(message, bsi);
            oos.writeObject(outMessage);
            }   while (!message.equals("exit"));
    } catch (IOException e) {
        e.printStackTrace();
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }
    }
}
