package com.luxoft.cjp.model;

public enum Gender {
    MALE("Mr."),
    FEMALE("Ms.");
    private String title;

    public String getTitle(){
        return title;
    }

    Gender (String title){
        this.title = title;
    }

}

