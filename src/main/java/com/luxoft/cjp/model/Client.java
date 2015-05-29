package com.luxoft.cjp.model;

import com.luxoft.cjp.service.InvalidBankArgumentException;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Client implements Report {

    private String name;
    private List<Account> accounts = new LinkedList<Account>();
    private Account activeAccount;
    private float initialOverdraft;
    private String electronicAddress;
    private String phoneNumber;
    private Gender gender;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getClientSalutation() {
        return this.getGender().getTitle() + " " + this.getName();
    }

    public void setActiveAccount(Account a) {
        activeAccount = a;
    }

    public float getBalance() {
        return activeAccount.getBalance();
    }

    public List<Account> getAccounts() {
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

    public void withdraw(float x) throws BankException {
        activeAccount.withdraw(x);
    }
    //create an account and set it as active
    public void createAccount(accountTypes type) {
        if (type.equals(accountTypes.CHECKING)) {
            accounts.add(new CheckingAccount());
            setActiveAccount(accounts.get(accounts.size()-1));
        }
        if (type.equals(accountTypes.SAVING)){
            accounts.add(new SavingAccount(0));
            setActiveAccount(accounts.get(accounts.size()-1));

        }
    }



    public Client(String name, float initialOverdraft, String electronicAddress, String phoneNumber, String gender) throws InvalidBankArgumentException {
        if (!electronicAddress.matches("[a-z|A-Z]*@[a-zA-Z]*.[a-zA-Z]*")) throw new InvalidBankArgumentException(electronicAddress);
        this.name = name;
        this.initialOverdraft = initialOverdraft;
        this.electronicAddress = electronicAddress;
        this.phoneNumber = phoneNumber;
        //decide gender based on the String argument
        if (gender.equals("m")){
            this.gender = Gender.MALE;
        }else if (gender.equals("f")) {
            this.gender = Gender.FEMALE;
        }else {
            throw new InvalidBankArgumentException(gender);
        }
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
}
