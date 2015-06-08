package com.luxoft.cjp.service;

import com.luxoft.cjp.model.Bank;
import com.luxoft.cjp.model.Client;
import com.luxoft.cjp.model.ClientExistsException;

/**
 * Created by pamajcher on 2015-06-08.
 */
public class TestSerialization {
    static Bank b = new Bank();
    static Bank c = new Bank();

    static BankServiceImpl bs = new BankServiceImpl(b);
    static BankServiceImpl bsc = new BankServiceImpl(c);

    public static void test(){
        Client c = null;
        try {
            c = new Client("Aldona", 150, "ald@on.a", "63456765", "Wawa", "f");
            bs.addClient(c);

        }catch(InvalidBankArgumentException e){

        } catch (ClientExistsException e) {
            e.printStackTrace();
        }

        bs.saveClient(c,"testfolder");
        BankFeedService.loadFeed("testfolder", bsc);

        //compare two reports
        bs.report();
        bsc.report();
    }

}
