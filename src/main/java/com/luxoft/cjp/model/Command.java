package com.luxoft.cjp.model;

/**
 * Created by pamajcher on 2015-05-27.
 */
public interface Command {


    Object execute();

    //prints usage information
    void printCommandInfo();


}
