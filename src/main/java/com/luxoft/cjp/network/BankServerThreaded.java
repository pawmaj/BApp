package com.luxoft.cjp.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by pamajcher on 2015-06-22.
 */
public class BankServerThreaded {
    final Integer PORT = 2004;
    final Integer POOL_SIZE = 5;

    public BankServerThreaded() {
        try {
            ServerSocket socket = new ServerSocket(PORT);
        }catch (IOException e){
            e.printStackTrace();
        }
        ExecutorService pool = Executors.newFixedThreadPool(POOL_SIZE);

    }

}