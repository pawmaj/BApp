package com.luxoft.cjp.network;

/**
 * Created by pamajcher on 2015-06-22.
 */
public class BankServerMonitor implements Runnable {

    public void run() {
        while (true) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Client counter:" + BankServerThreaded.clientCounter);
        }
    }
}
