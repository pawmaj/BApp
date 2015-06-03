package com.luxoft.cjp.model;

import java.util.*;

public class Bank implements Report {

    public Set<Client> clients = new TreeSet<Client>();
    private List<ClientRegistrationListener> listeners = new LinkedList<ClientRegistrationListener>();

    public Set<Client> getClients(){
        return clients;
    }

    public Bank(){
        listeners.add(new PrintClientListener());
        listeners.add(new EmailNotificationListener());

    }
    public Integer getNumberOfClients(){
        Properties props = new Properties();
        String s = props.getProperty("java.version");
        System.out.println("Number of clients:"  +clients.size());
        return clients.size();
    }
    public void getClientsSorted(){
        //Stored in treeMap, they are already sorted
        System.out.print(getClients());
    }


    public void addClient(Client c) throws ClientExistsException {
        Iterator<Client> it = clients.iterator();
        while(it.hasNext()){
            if (c.getName().equals(it.next().getName())) throw new ClientExistsException(c.getName());
        }
        clients.add(c);
        for (ClientRegistrationListener l: listeners){
            l.onClientAdded(c);
        }
    }


    public void printReport() {
        //Display information about each client:
        Iterator<Client> it = clients.iterator();
        while (it.hasNext()){
            it.next().printReport();
        }

    }

    private class PrintClientListener implements ClientRegistrationListener {

        public void onClientAdded(Client c) {
            System.out.print("Registered "); c.printReport();
        }
    }
    private class EmailNotificationListener implements ClientRegistrationListener {

        public void onClientAdded(Client c) {
            System.out.println("Notification email for client " + c.getClientSalutation() +" to be sent.");
        }
    }

}
