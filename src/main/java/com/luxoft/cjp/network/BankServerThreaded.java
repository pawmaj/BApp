package com.luxoft.cjp.network;

import com.luxoft.cjp.model.Bank;
import com.luxoft.cjp.service.BankServiceImpl;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by pamajcher on 2015-06-22.
 */
public class BankServerThreaded implements Runnable {
    static final Integer PORT = 2005;
    static final Integer POOL_SIZE = 10000;
    static ServerSocket serverSocket;
    static ExecutorService pool = Executors.newFixedThreadPool(POOL_SIZE);

    public static void setBank(Bank b) {
        BankServerThreaded.b = b;
    }

    public static void setBankServiceImpl(BankServiceImpl bsi) {
        BankServerThreaded.bsi = bsi;
    }

    static Bank b = new Bank(id);
    static BankServiceImpl bsi = new BankServiceImpl(b);
    public static AtomicInteger clientCounter;

    public void run(){
        clientCounter.set(0);//initialize client counter
        boolean running = true;

            try {
                serverSocket = new ServerSocket(PORT);

            }catch (IOException e) {
                e.printStackTrace();
            }
            pool.execute(new BankServerMonitor()); // run the monitor
                while (running) {
                    try {
                        Socket clientSocket = serverSocket.accept();
                        clientCounter.incrementAndGet();
                        pool.execute(new ServerThread(clientSocket,bsi));
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }


        } //end run


}