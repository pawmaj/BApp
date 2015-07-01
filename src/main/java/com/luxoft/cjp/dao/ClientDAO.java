package com.luxoft.cjp.dao;

import com.luxoft.cjp.model.Bank;
import com.luxoft.cjp.model.Client;

import java.util.List;

/**
 * Created by pamajcher on 2015-07-01.
 */
public interface ClientDAO {


    Client findClientByName (Bank bank, String name) throws DAOException;





    List<Client> getAllClients (Bank bank) throws DAOException;





    void save (Client client) throws DAOException;




    void remove (Client client) throws DAOException;
}
