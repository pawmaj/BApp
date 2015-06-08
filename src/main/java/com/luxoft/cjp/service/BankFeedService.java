package com.luxoft.cjp.service;

import com.luxoft.cjp.model.Client;
import com.luxoft.cjp.model.ClientExistsException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by pamajcher on 2015-06-08.
 */
public class BankFeedService {

    public static Boolean loadFeed(String folder, BankService bs){
        RandomAccessFile f2 = null;
        try {
            f2 = new RandomAccessFile(folder + "/example.feed","r");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String feedLine = null;
        try {
            feedLine = f2.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            feedLine = new String(feedLine.getBytes("UTF-8"), "Unicode");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //parsing the feed
        String feedArray[] = feedLine.split(";");
        Map<String,String> feedMap = new HashMap<String,String>();
        feedMap.put("type",feedArray[0].substring(12,13));
        String[] balance = feedArray[1].split("=");
        feedMap.put("balance",balance[1]);
        String[] overdraft = feedArray[2].split("=");
        feedMap.put("overdraft",overdraft[1]);
        String[] name = feedArray[3].split("=");
        feedMap.put("name", name[1]);
        feedMap.put("gender",feedArray[4].substring(7,8));
        //end parsing feed

        System.out.println("Feed Loaded: " + feedMap);


            try{
            //add new client if name doesn't exist yet
            bs.addClient(new Client(feedMap.get("name"),
                                    Float.valueOf(feedMap.get("overdraft")),
                                    "from@fe.ed",//feed doesn't support this property yet
                                    "777-from-feed",//feed doesn't support this property yet
                                    "FeedCity",//feed doesn't support this property yet
                                    feedMap.get("gender")));
            }catch (InvalidBankArgumentException e){
                System.out.println("Corrupterd feed. Invalid argument:" + e.getInvalidArg());
            } catch (ClientExistsException e) {
                //TODO update client
            }
        return true;

    }




    }

