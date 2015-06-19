package com.luxoft.cjp.network;

import com.luxoft.cjp.model.Bank;
import com.luxoft.cjp.model.Client;
import com.luxoft.cjp.service.BankCommander;
import com.luxoft.cjp.service.BankServiceImpl;
import com.luxoft.cjp.service.SendUpdateCommand;

/**
 * Created by pamajcher on 2015-06-15.
 */
public class BankRemoteOffice {
    private String inputLine = "";
    private BankCommander bankComm;
    private Bank b;
    private  BankServiceImpl bs;


    public static void main(String[] args) {
        BankRemoteOffice remoteOffice = new BankRemoteOffice();
        //this causes null pointer exception

        remoteOffice.run();


    }

    public static String serializeClientToString(Client c) {
        StringBuilder sb = new StringBuilder();
        //serialize only information about the first account:
        sb.append("balance=").append(c.getBalance()).append(";")
                .append("overdraft=").append(c.getInitialOverdraft()).append(";")
                .append("name=").append(c.getName()).append(";")
                .append("gender=").append(c.getGender()).append(";")
                .append("email=").append(c.getElectronicAddress()).append(";")
                .append("phone=").append(c.getPhoneNumber()).append(";")
                .append(("City=")).append(c.getCity()).append(";");
        return sb.toString();
    }
    //send feed lines with commands
    //recieve input

    public BankRemoteOffice() {

        b = new Bank();
        bs = new BankServiceImpl(b);
        bankComm = new BankCommander();
        bankComm.registerCommand("Send update command", new SendUpdateCommand(bs));
    }
    public void run(){
        bankComm.runInteractiveMode(bs);
    }
}

