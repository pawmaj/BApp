package com.luxoft.cjp.service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by pamajcher on 2015-06-15.
 */
public class BankRemoteOffice {

    public static void main(String[] args){
        //display a menu
        System.out.println("Welcome to the remote office");
        System.out.println("Commands are:\n Add [c/s/b];balance;overdraft;name;[m/f];email;phone;city");
        String messageToSend ="";
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        Scanner scanner = new Scanner(System.in);
        try {
            Socket requestSocket = new Socket("localhost",2222);
            oos = new ObjectOutputStream(requestSocket.getOutputStream());
            ois = new ObjectInputStream(requestSocket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Connected to the bank.");
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
        //send feed lines with commands
        //recieve input
    }


