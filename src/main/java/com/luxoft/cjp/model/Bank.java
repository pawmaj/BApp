package com.luxoft.cjp.model;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Bank implements Report {

    public List<Client> clients = new LinkedList<Client>();
    private List<ClientRegistrationListener> listeners = new LinkedList<ClientRegistrationListener>();

    public List<Client> getClients(){
        return clients;
    }

    public Bank(){
        listeners.add(new PrintClientListener());
        listeners.add(new EmailNotificationListener());


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

    @Override
    public void printReport() {
        //Display information about each client:
        Iterator<Client> it = clients.iterator();
        while (it.hasNext()){
            it.next().printReport();
        }

    }

    private class PrintClientListener implements ClientRegistrationListener {
        @Override
        public void onClientAdded(Client c) {
            System.out.print("Registered "); c.printReport();
        }
    }
    private class EmailNotificationListener implements ClientRegistrationListener {
        @Override
        public void onClientAdded(Client c) {
            System.out.println("Notification email for client " + c.getClientSalutation() +" to be sent.");
        }
    }

}
