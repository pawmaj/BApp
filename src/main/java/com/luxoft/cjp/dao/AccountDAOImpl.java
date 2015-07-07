package com.luxoft.cjp.dao;

import com.luxoft.cjp.model.Account;

import java.sql.*;
import java.util.List;

/**
 * Created by pamajcher on 2015-07-07.
 */
public class AccountDAOImpl extends BaseDaoImpl implements AccountDAO {

    Connection conn;

    @Override
    public void save(Account account) throws DAOException {
        //TODO do it
        String sql = "INSERT INTO ACCOUNT (id,user,balance) VALUES ? ? ?";
        PreparedStatement statement;
        try {
            this.openConnection();
            statement = conn.prepareStatement(sql);

            statement.setInt(1,account.getId());
            statement.setString(2,account.getUser());
            statement.setFloat(3,account.getBalance());

            statement.executeUpdate();
        }catch (SQLException e){
            throw new DAOException();
        }finally {
            closeConnection();
        }

    }

    @Override
    public void add(Account account) throws DAOException {
        String sql = "INSERT INTO ACCOUNT (id,user,balance) VALUES ? ? ?";
        PreparedStatement statement;
        try {
            this.openConnection();
            statement = conn.prepareStatement(sql);

            statement.setInt(1,account.getId());
            statement.setString(2,account.getUser());
            statement.setFloat(3,account.getBalance());

            statement.executeUpdate();
        }catch (SQLException e){
            throw new DAOException();
        }finally {
            closeConnection();
        }

    }

    @Override
    public void removeByClientId(String clientName) throws DAOException {
        String sql = "DELETE FROM ACCOUNT (id,user,balance) WHERE user=?";//TODO do it
        PreparedStatement statement;
        try {
            this.openConnection();
            statement = conn.prepareStatement(sql);

            statement.setString(1,clientName);

            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            throw new DAOException();
        }finally {
            closeConnection();
        }
    }

    @Override
    public List<Account> getClientAccounts(String clientName) throws DAOException {
        String sql = "SELECT * FROM ACCOUNT WHERE user=?";
        PreparedStatement statement;
        try {
            this.openConnection();
            statement = conn.prepareStatement(sql);

            statement.setString(1, clientName);

            ResultSet rs = statement.executeQuery();
            //TODO do it
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException();
        } finally {
            closeConnection();
        }
        return null;
    }
}
