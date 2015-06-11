package com.luxoft.cjp.network;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by pamajcher on 2015-06-11.
 */
public class Client {
    public Client(){
        String messageToSend ="";
        ObjectOutputStream oos = null;
        Scanner scanner = new Scanner(System.in);
        try {
            Socket requestSocket = new Socket("localhost",2222);
            oos = new ObjectOutputStream(requestSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        do {
            messageToSend = scanner.nextLine();
            try {
                oos.writeObject(messageToSend);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }while(true);
    }
    public static void main(final String args[]) {
        com.luxoft.cjp.network.Client c = new Client();
    }
}
