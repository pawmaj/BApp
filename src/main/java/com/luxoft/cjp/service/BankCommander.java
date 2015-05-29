package com.luxoft.cjp.service;

import com.luxoft.cjp.model.*;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Created by pamajcher on 2015-05-27.
 */
public class BankCommander {

    public static Bank currentBank = new Bank();
    public static BankServiceImpl bs = new BankServiceImpl(currentBank);
    public static Gender gen = Gender.MALE;

    static Command[] commands = {
                    new AddClientCommand(),
                    new AddAccountCommand(),
                    new DepositCommand(),
                    new FindClientCommand(),
                    new WithdrawCommand(),
                    //Anonymous class for exit command:
                    new Command(){
                        public Object execute() { System.exit(0);   return 0;  }//Sadly, this return is unreachable
                        public void printCommandInfo() { System.out.print("Exit"); }
                        }
                    };

    public static void main(String[] args) {

        //Main loop:
        while (true){
            //print command list
            for (int i=0;i<commands.length;i++) { // show menu
                System.out.print(i+") ");
                commands[i].printCommandInfo();
            }
            try//catch null pointer or invalid argument exceptions when user types too short or wrong command
            {

                System.out.println();//Start on a new line for
                Scanner scanner = new Scanner(System.in);

                //read a line
                String inputLine = scanner.nextLine();

                //split the line by whitespaces
                String[] command = inputLine.split("\\s+");

                //extract command number
                int commandNumber = Integer.valueOf(command[0]);

                //construct commands based on user input
                switch (commandNumber) {
                    case 0://add client
                        commands[commandNumber] = new AddClientCommand(bs, new Client(command[1], //name
                                Integer.valueOf(command[2]),//ovedraft
                                command[3],//address
                                command[4],//phone
                                command[5]));//gender
                        break;
                    case 1://add account
                        commands[commandNumber] = new AddAccountCommand(bs.findClientByName(command[1]),//name of an existing client
                                command[2]);//type of account to add - 'saving' or 'checking'
                        break;
                    case 2://deposit
                        commands[commandNumber] = new DepositCommand(bs.findClientByName(command[1]),//name of an existing client
                                Integer.valueOf(command[2]));//amount to deposit (on currently active account)
                        break;
                    case 3://find client
                        commands[commandNumber] = new FindClientCommand(bs,command[1]);//name to find
                        break;
                    case 4://withdraw
                        commands[commandNumber] = new WithdrawCommand(bs,
                                                                    bs.findClientByName(command[1]),//name of client who withdraws
                                                                    Integer.valueOf(command[2]));//amount to withdraw
                }

                commands[commandNumber].execute();

                //MAGIC - handle all wrong input with just three catch blocks!
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
            }
        }


    }


