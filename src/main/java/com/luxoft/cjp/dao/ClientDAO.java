package com.luxoft.cjp.dao;

import com.luxoft.cjp.model.Bank;
import com.luxoft.cjp.model.Client;

import java.util.List;

/**
 * Created by pamajcher on 2015-07-01.
 */
public interface ClientDAO {
    /**
     * Return client by its name, initialize client accounts.
    * @param bank
    * @param name
    * @return
     */
    Client findClientByName (Bank bank, String name) throws DAOException;

    /**
     * Returns the list of all clients of the Bank
    * And their accounts
    * @param bankId
    * @return
     */
    List <Client> getAllClients (Bank bank) throws DAOException;

    /**
     * Method should insert new Client (if id == null)
    * Or update client in database
    * @param client
    */
    void save (Client client) throws DAOException;

    /**
     * Method removes client from Database
    * @param client
    */
    void remove (Client client) throws DAOException;
}
