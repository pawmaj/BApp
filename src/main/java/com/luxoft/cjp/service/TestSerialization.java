package com.luxoft.cjp.service;

/**
 * Created by pamajcher on 2015-06-08.
 */
public class TestSerialization {


    public static void test(BankServiceImpl bs){
        BankFeedService.writeExampleFeed(); //(example client's name is John)
        BankFeedService.loadFeed("example", bs); //load a client from the example feed
        bs.saveClient(bs.getClient("John"), "example"); //save this client again
    }

}

