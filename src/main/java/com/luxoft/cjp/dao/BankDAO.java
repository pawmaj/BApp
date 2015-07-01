package com.luxoft.cjp.dao;

import com.luxoft.cjp.model.Bank;

/**
 * Created by pamajcher on 2015-07-01.
 */
public interface BankDAO {

    Bank getBankByName (String name) throws DAOException, BankNotFoundException;



    void save(Bank bank) throws DAOException;



    void remove(Bank bank) throws DAOException;
}
