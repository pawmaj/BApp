package com.luxoft.cjp.model;

/**
 * Created by pamajcher on 2015-05-22.
 */
public class ClientExistsException extends Exception {

    private String existingClientName;

    public String getExistingClientName() {
        return existingClientName;
    }

    public void setExistingClientName(String existingClientName) {
        this.existingClientName = existingClientName;
    }

    public ClientExistsException(String exname){
        this.existingClientName = exname;
    }
}
