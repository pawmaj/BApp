package com.luxoft.cjp.model;

import java.util.*;

public class Bank implements Report {

    private int id;
    private String name;

    private Set<Client> clients = new HashSet<Client>();
    private Map<String, Client> clientsMap = new HashMap<String, Client>();
    private List<ClientRegistrationListener> listeners = new LinkedList<ClientRegistrationListener>();

    public int getId() {
        return id;
    }

    public Collection<Client> getClientsSorted()
    {
        TreeSet cts = new TreeSet(new Comparator() {
            public int compare(Object o1, Object o2) {
                Client c1 = (Client)o1;
                Client c2 = (Client)o2;
                return c1.getName().compareTo(c2.getName());
            }
        });
        for(Client c:clients) cts.add(c);

        return Collections.unmodifiableCollection(cts);
    }

    public Set<Client> getClients() {
        return clients;
    }

    public Map<String, Client> getClientsMap() {
        return clientsMap;
    }

    public Integer getNumberOfClients(){
        System.out.println("Number of clients:"  +clients.size());
        return clients.size();
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

    public void setId(int id) {
        this.id = id;
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

        public Bank(String name){
            this.name = name;
            listeners.add(new PrintClientListener());
            listeners.add(new EmailNotificationListener());
            listeners.add(new MapClientListener());

        }

}

