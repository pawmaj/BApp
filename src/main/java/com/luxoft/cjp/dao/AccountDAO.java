package com.luxoft.cjp.dao;

import com.luxoft.cjp.model.Account;

import java.util.List;

/**
 * Created by pamajcher on 2015-07-01.
 */
public interface AccountDAO {
    public void save(Account account) throws DAOException;

    public void add(Account account) throws DAOException;

    public void removeByClientId(int idClient) throws DAOException;

    public List<Account> getClientAccounts(int idClient) throws DAOException;
}
