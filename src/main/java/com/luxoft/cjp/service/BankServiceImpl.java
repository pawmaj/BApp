package com.luxoft.cjp.service;

import com.luxoft.cjp.model.Bank;
import com.luxoft.cjp.model.Client;
import com.luxoft.cjp.model.ClientExistsException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by pamajcher on 2015-05-21.
 */
public class BankServiceImpl implements BankService {

    private Bank b;

    public BankServiceImpl(Bank b) {
        this.b = b;
    }


    public void addClient(Client client) throws ClientExistsException{
    b.addClient(client);
    }


    public void removeClient(Bank bank, Client client) {
        bank.getClients().remove(client);

    }


    public void addAccount(Client client, String type) {
        client.createAccount(type);
    }


    public void switchActiveAccount(Client client) {
       //TODO fix this

       // client.setActiveAccount(client.getAccounts().get(number));
    }


    public Client getClient(String name) {
        return b.getClientsMap().get(name);
    }
    //Overloaded for searching in any bank
    public Client getClient(String name, Bank b) {
        return b.getClientsMap().get(name);
    }



    public void saveClient(Client c, String folder) {
        StringBuilder sb = new StringBuilder();//initialize the string which describes a client
        if (c.getAccounts().size()>1){//if the client has both accounts
            sb.append("accounttype=b;");
        }
        //serialize remaining properties:
        sb
                .append("balance=").append(c.getBalance()).append(";")
                .append("overdraft=").append(c.getInitialOverdraft()).append(";")
                .append("name=").append(c.getName()).append(";")
                .append("gender=").append(c.getGender()).append(";")
                .append("email=").append(c.getElectronicAddress()).append(";")
                .append("phone=").append(c.getPhoneNumber()).append(";")
                .append(("City=")).append(c.getCity()).append(";");
        File f0 = new File(folder);
        f0.mkdir();
        try {
            RandomAccessFile f1 = new RandomAccessFile(folder +"/client.feed", "rw");
            f1.writeChars(sb.toString());

        }catch(FileNotFoundException e){
            System.out.println("file not found");
        } catch (IOException e) {
            System.out.println("IOException");
        }

    }


    public void loadClient(String folder) {
        BankFeedService.loadFeed(folder, this);
    }

    public void report(){
        b.printReport();
    }
}
//^Hi,\\s*[A-Z][a-z]{2,} [A-Z][a-z]{2,}\\s*!$

