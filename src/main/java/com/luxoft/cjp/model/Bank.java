package com.luxoft.cjp.model;

import java.util.*;

public class Bank implements Report {


    private Set<Client> clients = new HashSet<Client>();
    private Map<String, Client> clientsMap = new HashMap<String, Client>();
    private List<ClientRegistrationListener> listeners = new LinkedList<ClientRegistrationListener>();

    //This getter obscures internal implementation
    //and returns unmodifiable collection
    public Collection<Client> getClients()
    {
        return Collections.unmodifiableCollection((Collection<Client>) clients);
    }

    public Map<String, Client> getClientsMap() {
        return clientsMap;
    }

    public Integer getNumberOfClients(){
        System.out.println("Number of clients:"  +clients.size());
        return clients.size();
    }
    public void getClientsSorted(){

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

    //Map the client to his name
    class MapClientListener implements ClientRegistrationListener {

        public void onClientAdded(Client c) {
            getClientsMap().put(c.getName(), c);
        }
    }
     class PrintClientListener implements ClientRegistrationListener {

        public void onClientAdded(Client c) {
            System.out.print("Registered "); c.printReport();
        }
    }
     class EmailNotificationListener implements ClientRegistrationListener {

        public void onClientAdded(Client c) {
            System.out.println("Notification email for client " + c.getClientSalutation() +" to be sent.");
        }
    }

        public Bank(){
            listeners.add(new PrintClientListener());
            listeners.add(new EmailNotificationListener());
            listeners.add(new MapClientListener());

        }

}
