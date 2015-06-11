package com.luxoft.cjp.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by pamajcher on 2015-06-11.
 */
public class Server {

    public Server(){
        String message = "";
        try {
            ServerSocket providerSocket = new ServerSocket(2222,10);
            do {
                Socket connection = providerSocket.accept();
                ObjectInputStream ois = new ObjectInputStream(connection.getInputStream());
                message = (String) ois.readObject();
                System.out.println("Received: " + message);
            }while (!message.equals("exit"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void main(final String args[]) {
        com.luxoft.cjp.network.Server s = new Server();
    }
}
