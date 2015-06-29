package com.luxoft.cjp.service;

import com.luxoft.cjp.model.Bank;
import com.luxoft.cjp.model.Client;
import com.sun.glass.ui.Size;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by pamajcher on 2015-06-29.
 */
public class BankServiceImplTest {
    private Bank fBank;
    private BankService fBankService;
    private List<Client> fListOfClients;
    private final Integer NumberOfTestClients = 100;


    @Before
    public void setUp() throws Exception {
        fBank = new Bank();
        fBankService = new BankServiceImpl(fBank);

        fListOfClients = new LinkedList<Client>();
        for (int i = 0; i<NumberOfTestClients;i++){
            String number = new String(String.valueOf(i+1));
            fListOfClients.add(

                    new Client("Client"+number,                 //name
                            0,                                  //initial overdraft
                            "client"+number+"@client.pl",       //electronic address
                            "0700 "+number,                     //phone
                            "City"+ number,                     //city
                            "m"                                 //gender
                            )
            );
        }//end setting up list of clients

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetBank() throws Exception {
        assertTrue(fBankService.getBank() instanceof Bank);
    }

    @Test
    public void testAddClient() throws Exception {
        for (Client c: fListOfClients) fBankService.addClient(c);
        Collection<Client> ClientsFromBank = fBankService.getClientsSorted();
        Assert.assertEquals(ClientsFromBank.size(),fListOfClients.size(),0);
    }

    @Test
    public void testRemoveClient() throws Exception {
        for (Client c: fListOfClients) fBankService.addClient(c);
        int SizeBefore = fBankService.getClientsSorted().size();
        int numberOfDeletions = 10;

        assertTrue(numberOfDeletions < SizeBefore);//test the test

        for (int i = 0;i<numberOfDeletions;i++) fBankService.removeClient(fBankService.getBank(),fBankService.getClient("Client"+String.valueOf(i +1)));
        int SizeAfter = fBankService.getClientsSorted().size();

        assertEquals(SizeAfter,SizeBefore-numberOfDeletions,0);
    }

    @Test
    public void testAddAccount() throws Exception {

    }

    @Test
    public void testGetClient() throws Exception {
        fBankService.addClient(
                new Client("testGetClient",                  //name
                        10,                                 //initial overdraft
                        "test@get.client",                  //electronic addess
                        "0700TESTGETCLIENT",                //phone
                        "CityTestGetClient",                //city
                        "f"                                 //gender
                )
        );
        Client c = fBankService.getClient("testGetClient");

        //check if all client's fields are correct:
        assertEquals(c.getName(),"testGetClient");
        assertEquals(c.getInitialOverdraft(),10,0);
        assertEquals(c.getElectronicAddress(),"test@get.client");
        assertEquals(c.getPhoneNumber(),"0700TESTGETCLIENT");
        assertEquals(c.getCity(),"CityTestGetClient");
        assertEquals(c.getGender(),"f");
    }

    @Test
    public void testGetClient1() throws Exception {

    }

    @Test
    public void testSaveClient() throws Exception {

    }

    @Test
    public void testLoadClient() throws Exception {

    }

    @Test
    public void testReport() throws Exception {

    }
}