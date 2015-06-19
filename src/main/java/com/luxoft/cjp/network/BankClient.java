package com.luxoft.cjp.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by pamajcher on 2015-06-11.
 */
public class BankClient {
    //TODO: move logic outside the constructor to the run() method
    String messageToSend;
    ObjectOutputStream oos;
    ObjectInputStream ois;
    Scanner scanner;
    public BankClient(){
        messageToSend ="";
        oos = null;
        ois = null;
        scanner = new Scanner(System.in);

    }
    public static void main(final String args[]) {
        BankClient c = new BankClient();
        c.run();
    }

    public void run(){
        try {
            Socket requestSocket = new Socket("localhost",2004);
            oos = new ObjectOutputStream(requestSocket.getOutputStream());
            ois = new ObjectInputStream(requestSocket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Connected to the server");
        do {
            try {

                System.out.print(">");
                messageToSend = scanner.next();
                oos.writeObject(messageToSend);
                System.out.println((String)ois.readObject());//write out server's responses immediately
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }while(true);
    }
}
