package com.luxoft.cjp.service;

import com.luxoft.cjp.model.Client;
import com.luxoft.cjp.model.Command;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by pamajcher on 2015-06-15.
 */
public class BankRemoteOffice {
    private static String inputLine = "";

    public static void main(String[] args){
        //make map of commands
        Map<Integer, Command> commandMap= new HashMap<Integer,Command>();
        commandMap.put(0, new AddClientCommand());
        commandMap.put(1, new AddAccountCommand());
        commandMap.put(2, new DepositCommand());
        //display a menu
        System.out.println("Welcome to the remote office");
        //TODO:
        //1.print commands info
        //2.get input
        //3.serialize information and send it to remote BankApplication

        String messageToSend ="";
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        Scanner scanner = new Scanner(System.in);
        try {
            Socket requestSocket = new Socket("localhost",2222);
            oos = new ObjectOutputStream(requestSocket.getOutputStream());
            ois = new ObjectInputStream(requestSocket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Connected to the bank.");
        do {
            try {

                System.out.print(">");
                inputLine = scanner.next();
                String[] command = inputLine.split("\\s+");

                //extract command number
                Integer commandNumber = Integer.valueOf(command[0]);

                //construct commands based on user input:

                switch (commandNumber) {
                    case 0://add client
                         messageToSend = serializeClientToString( new Client(command[1], //name
                                Integer.valueOf(command[2]),//ovedraft
                                command[3],//address
                                command[4],//phone
                                command[5],//city
                                command[6]));//gender
                        break;
                }
                oos.writeObject(messageToSend);
                System.out.println((String)ois.readObject());//write out server's responses immediately
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InvalidBankArgumentException e) {
                    e.printStackTrace();
                }
            }while(true);
    }
    public static String serializeClientToString(Client c){
        StringBuilder sb = new StringBuilder();
        if (c.getAccounts().size()>1){//if the client has both accounts
            sb.append("accounttype=b;");
        }
        sb      .append("balance=").append(c.getBalance()).append(";")
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
    }


