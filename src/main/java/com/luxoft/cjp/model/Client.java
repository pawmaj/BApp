package com.luxoft.cjp.model;

import com.luxoft.cjp.service.InvalidBankArgumentException;

import java.util.*;

public class Client implements Report, Comparable {

    private String name;
    private Set<Account> accounts = new HashSet<Account>();
    private Account activeAccount;
    private float initialOverdraft;
    private String electronicAddress;
    private String phoneNumber;
    private String gender;
    private String city;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getInitialOverdraft() {
        return initialOverdraft;
    }

    public String getCity() { return city; }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }


    public String getClientSalutation() {
        return this.getTitle() + " " + this.getName();
    }

    public void setActiveAccount(Account a) {
        activeAccount = a;
    }

    public float getBalance() {
        return activeAccount.getBalance();
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public String getElectronicAddress() {
        return electronicAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void deposit(float x) {
        activeAccount.deposit(x);
    }

    public void withdraw(float x) throws NotEnoughFundsException {
        activeAccount.withdraw(x);
    }
    //create an account and set it as active
    public Boolean createAccount() {
        Account a = new SavingAccount();
        accounts.add(a);
        setActiveAccount(a);
        return true;
    }


    public Client(String name, float initialOverdraft, String electronicAddress, String phoneNumber, String city, String gender) throws InvalidBankArgumentException {
        if (!electronicAddress.matches("[a-zA-Z0-9]*@[a-zA-Z]*.[a-zA-Z]*"))
            throw new InvalidBankArgumentException(electronicAddress);
        this.name = name;
        this.initialOverdraft = initialOverdraft;
        this.electronicAddress = electronicAddress;
        this.phoneNumber = phoneNumber;
        this.city = city;
        //decide gender based on the String argument
        this.gender = gender;
    }

    public void printReport() {
        //print information about all accounts of the client
        System.out.println(getClientSalutation());
        String accountsReport = "";
        Iterator<Account> it = accounts.iterator();
        int k = 1;
        while (it.hasNext()) {
            accountsReport = "";
            accountsReport = accountsReport + "Account " + k + ": ";
            System.out.print(accountsReport);
            it.next().printReport();
            System.out.println();
            k++;
        }

    }

    @Override
    public String toString() {


        StringBuilder sb = new StringBuilder();

        sb.append("Client name: ");
        sb.append(getClientSalutation());
        sb.append("\n");
        Iterator<Account> it = accounts.iterator();
        while (it.hasNext()) { sb.append(it.next().toString()); }

        return sb.toString();
    }


    //I check client equality only by name, because in this application name acts as a client's unique id
    //(method addClient guarantees no duplicated names among registered clients)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(getName(), client.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    //Keeping equals() and compare() consistency
    public int compareTo(Object o) {
        Client c = (Client)o;
       return getName().compareTo(c.getName());

    }
    public String getTitle(){
        if (this.gender.equals("m"))return "Mr.";
        if (this.gender.equals("f"))return "Ms.";
        return "NoTitle!";
    }

}

