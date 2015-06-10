package com.luxoft.cjp.service;

import com.luxoft.cjp.model.Client;
import com.luxoft.cjp.model.ClientExistsException;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by pamajcher on 2015-06-08.
 */
public class BankFeedService {


    public static Boolean loadFeed(String folder, BankService bs){


        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(folder + "/client.feed","r");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String feedLine = null;

        while(true) {//Continue reading lines until exception occurs meaning there are no more lines (or some other error)
            try {
                feedLine = raf.readLine();

            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }

            try {
                feedLine = new String(feedLine.getBytes("UTF-8"), "Unicode");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return false;
            }  catch (NullPointerException e) {//This is the usual exit point from the while(true)
                return true;
            }
            //parsing the feed
            String feedArray[] = feedLine.split(";");
            Map<String, String> feedMap = new HashMap<String, String>();
            feedMap.put("accounts", feedArray[0].substring(12, 13));

            //thanks to the following "hack", any length of a property is possible:
            String[] balance = feedArray[1].split("=");
            feedMap.put("balance", balance[1]);

            String[] overdraft = feedArray[2].split("=");
            feedMap.put("overdraft", overdraft[1]);

            String[] name = feedArray[3].split("=");
            feedMap.put("name", name[1]);

            feedMap.put("gender", feedArray[4].substring(7, 8));

            String[] email = feedArray[5].split("=");
            feedMap.put("email", email[1]);

            String[] phone = feedArray[6].split("=");
            feedMap.put("phone", phone[1]);

            String[] city = feedArray[7].split("=");
            feedMap.put("city", city[1]);
            //end parsing feed

            System.out.println("Feed Loaded: " + feedMap);


            try {
                //Construct new client from the feed:
                bs.addClient(new Client(feedMap.get("name"),
                        Float.valueOf(feedMap.get("overdraft")),
                        feedMap.get("email"),
                        feedMap.get("phone"),
                        feedMap.get("city"),
                        feedMap.get("gender")));
                //Client constructed, now initialize his accounts according to the feed:
                if (feedMap.get("accounts").equals("c")) {//if client only has checking account
                    bs.addAccount(bs.getClient(feedMap.get("name")), "checking");//add checking account

                } else if (feedMap.get("accounts").equals("s")) {//if client only has saving account
                    bs.addAccount(bs.getClient(feedMap.get("name")), "saving");//add saving account
                    bs.getClient(feedMap.get("name")).deposit(Float.valueOf(feedMap.get("balance")));//and deposit his savings

                } else if (feedMap.get("accounts").equals("b")) {//if client has both accounts
                    bs.addAccount(bs.getClient(feedMap.get("name")), "saving");//add saving account
                    bs.addAccount(bs.getClient(feedMap.get("name")), "checking");//add checking account
                    bs.getClient(feedMap.get("name")).deposit(Float.valueOf(feedMap.get("balance")));//and deposit his savings
                }
            } catch (InvalidBankArgumentException e) {
                System.out.println("Corrupterd feed. Invalid argument:" + e.getInvalidArg());

            } catch (ClientExistsException e) {//if client already exists
                //set his balance from the feed
                bs.getClient(feedMap.get("name")).deposit(Float.valueOf(feedMap.get("balance")));
            }
        }//end while(true)


    }




    public static void writeExampleFeed(){
        File f0 = new File("example");
        f0.mkdir();
        try {
            RandomAccessFile f1 = new RandomAccessFile("example/client.feed", "rw");
            f1.writeChars("accounttype=c;balance=0;overdraft=50;name=John;gender=m;email=john@john.jo;phone=0700770880;City=Krakow;\n");
            f1.writeChars("accounttype=b;balance=0;overdraft=50;name=Suzy;gender=f;email=suzy@su.zy;phone=0700-S-U-Z-Y;City=Krakow;");
            f1.close();
        }catch(FileNotFoundException e){
            System.out.println("file not found");
        } catch (IOException e) {
            System.out.println("IOException");
        }
    }
}


