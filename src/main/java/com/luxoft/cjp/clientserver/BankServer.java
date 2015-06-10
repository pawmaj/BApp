package com.luxoft.cjp.clientserver;


import com.luxoft.cjp.model.Bank;
import com.luxoft.cjp.model.BankException;
import com.luxoft.cjp.model.Client;
import com.luxoft.cjp.service.BankService;
import com.luxoft.cjp.service.BankServiceImpl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * Created by pamajcher on 2015-06-10.
 */
public class BankServer implements Runnable {
     static Bank b = new Bank();
     static BankService bs = new BankServiceImpl(b);
     ServerSocket providerSocket;
     Socket connection = null;
     ObjectOutputStream out;
     ObjectInputStream in;
     static String message;
     static String operationResult;

  public void run() {
        try {
            // 1. creating a server socket
            providerSocket = new ServerSocket(2004, 10);
            // 2. Wait for connection
            System.out.println("Waiting for connection");
            connection = providerSocket.accept();
            System.out.println("Connection received from "
                    + connection.getInetAddress().getHostName());
            // 3. get Input and Output streams
            out = new ObjectOutputStream(connection.getOutputStream());
            out.flush();
            in = new ObjectInputStream(connection.getInputStream());
            sendMessage("Connection successful");
            // 4. The two parts communicate via the input and output streams
            do {
                try {
                    message = (String) in.readObject();
                    System.out.println("client>" + message);
                    parseAndExecuteMessage(message);
                    sendMessage(operationResult);
                    if (message.equals("bye"))
                        sendMessage("bye");
                } catch (ClassNotFoundException classnot) {
                    System.err.println("Data received in unknown format");
                }

            } while (!message.equals("bye"));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } finally {
            // 4: Closing connection
            try {
                in.close();
                out.close();
                providerSocket.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    void sendMessage(final String msg) {
        try {
            out.writeObject(msg);
            out.flush();
            System.out.println("server>" + msg);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public static void main(final String args[]) {
        BankServer server = new BankServer();
        try {
            bs.addClient(new Client("John", 150F, "hn@jn.jn", "876543456", "Wawa", "m"));
            bs.getClient("John").createAccount("checking");
        }catch (Exception e){
        System.out.print("sdfgdsdfgd");
        }
        while (true) {
            server.run();
        }
    }
    private void parseAndExecuteMessage(String message){
        String[] messArr = message.split(";");
        if (messArr[0].equals("withdraw")){
            try {
                bs.getClient(messArr[1]).withdraw(Float.valueOf(messArr[2]));
                operationResult="withdraw successful";
            }catch (BankException e) {
                operationResult="withdraw failed";
            }
        }else if (messArr[0].equals("deposit")){
            bs.getClient(messArr[1]).deposit(Float.valueOf(messArr[2]));
            operationResult="deposit successful";
        }
    }
}
