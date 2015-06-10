package com.luxoft.cjp.service;

import com.luxoft.cjp.model.Client;
import com.luxoft.cjp.model.Command;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Created by pamajcher on 2015-06-09.
 */
public class BankCommander {
    static Map<Integer, Command> commandMap = null;
    static {
        commandMap = new HashMap<Integer, Command>();
        commandMap.put(0, new AddClientCommand());
        commandMap.put(1, new AddAccountCommand());
        commandMap.put(2, new DepositCommand());
        commandMap.put(3, new FindClientCommand());
        commandMap.put(4, new WithdrawCommand());
        commandMap.put(5, new Command() {
            public Object execute() {
                System.exit(0);
                return 0;//Sadly, this return is unreachable
            }

            public void printCommandInfo() {
                System.out.print("Exit");
            }
        });
    }
    public static void runInteractiveMode(BankServiceImpl bs){
        //Main loop:
        while (true){
            //print command list
            for (int i=0;i<commandMap.size();i++) { // show menu
                System.out.print(i+") ");
                commandMap.get(i).printCommandInfo();
            }


            try//catch null pointer or invalid argument exceptions when user types too short or wrong command
            {

                System.out.println();//Start on a new line
                Scanner scanner = new Scanner(System.in);

                //read a line
                String inputLine = scanner.nextLine();

                //split the line by whitespaces
                String[] command = inputLine.split("\\s+");

                //extract command number
                Integer commandNumber = Integer.valueOf(command[0]);

                //construct commands based on user input:
                switch (commandNumber) {
                    case 0://add client
                        commandMap.put(commandNumber,  new AddClientCommand(bs, new Client(command[1], //name
                                Integer.valueOf(command[2]),//ovedraft
                                command[3],//address
                                command[4],//phone
                                command[5],//city
                                command[6])));//gender
                        break;
                    case 1://add account
                        commandMap.put(commandNumber, new AddAccountCommand(bs.getClient(command[1]),//name of an existing client
                                command[2]));//type of account to add - 'saving' or 'checking'
                        break;
                    case 2://deposit
                        commandMap.put(commandNumber, new DepositCommand(bs.getClient(command[1]),//name of an existing client
                                Integer.valueOf(command[2])));//amount to deposit (on currently active account)
                        break;
                    case 3://find client
                        commandMap.put(commandNumber, new FindClientCommand(bs,command[1]));//name to find
                        break;
                    case 4://withdraw
                        commandMap.put(commandNumber, new WithdrawCommand(bs,
                                bs.getClient(command[1]),//name of client who withdraws
                                Integer.valueOf(command[2])));//amount to withdraw
                }

                commandMap.get(commandNumber).execute();


                //MAGIC - handle all wrong input with just three catch blocks:
            }catch (ArrayIndexOutOfBoundsException e){
                System.out.println("Wrong syntax - not enough arguments.");
            }catch(NumberFormatException e) {
                System.out.println("Wrong syntax - expected a number.");
            }catch(InvalidBankArgumentException e){
                System.out.println(e.getInvalidArg() + " is an invalid argument.");

                //This may happen, but not the user's fault
            }catch(NoSuchElementException e){
                System.out.println("Internal parser error. Please try a different shell.");
            }
            //DEBUG output to show my set sorting clients by the city:
            //System.out.println(BankReport.getClientByCity(currentBank));
            //BankReport.getAccountsNumber(currentBank);
            //BankReport.getClientsSorted(currentBank);
        }
    }

    //add example command
    public void registerCommand(String name, Command command){
        final String aname = name;
        commandMap.put(commandMap.size(), command);//(size() as the key, to put it at the end, because keys follow 0,1,2,3...

    }
    //remove command with with given number
    void removeCommand(String commandNumber){
        commandMap.remove(Integer.valueOf(commandNumber));
    }
}
