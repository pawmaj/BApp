package com.luxoft.cjp.network;

import com.luxoft.cjp.model.Bank;
import com.luxoft.cjp.model.Client;
import com.luxoft.cjp.model.ClientExistsException;
import com.luxoft.cjp.service.BankServiceImpl;
import com.luxoft.cjp.service.InvalidBankArgumentException;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by pamajcher on 2015-06-22.
 */
public class BankServerThreadedTest {

    @Test
    public void clientMustSeeAvailableFunds() throws InvalidBankArgumentException,ClientExistsException{
        Bank b = new Bank();
        BankServiceImpl bsi = new BankServiceImpl(b);
        bsi.addClient(new Client("JohnSmith", 0, "john@smi.th", "0700770770", "Krakow", "m"));
        Client client = bsi.getClient("JohnSmith");
        bsi.addAccount(bsi.getClient("JohnSmith"),"saving");
        client.deposit(2000);
        BankServerThreaded bankServerThreaded = new BankServerThreaded();//create new bank server threaded
        bankServerThreaded.setBsi(bsi);//attach our current bank to it
        Thread t = new Thread(new BankServerThreaded());
        t.start();
        try {
            t.join();
        }catch (InterruptedException e){

        }
        double amount = client.getBalance();

        for (int i = 0; i <1; i ++) {
        BankClientMock bankClientMock = new BankClientMock(client);
        bankClientMock.start();

        }
        double amount2 = client.getBalance();

        org.junit.Assert.assertEquals(amount - 1000, amount2, 100);
    }

    private class BankClientMock {
        com.luxoft.cjp.model.Client client;
        com.luxoft.cjp.network.BankClient networkClient = new BankClient();

        public BankClientMock(com.luxoft.cjp.model.Client client) {
        this.client = client;
        }
        public void start(){
            try {

                networkClient.sendCustomMessage(client.getName()+",withdraw,1");
            }catch (Exception e){

            }
        }
    }
}