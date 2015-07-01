package com.luxoft.cjp.dao;

import com.luxoft.cjp.model.Bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by pamajcher on 2015-07-01.
 */
public class BankDaoImpl extends BaseDAOImpl {

    Connection conn;



    public Bank getBankByName(String name) throws DAOException, BankNotFoundException {

        Bank bank = new Bank(name);

        String sql = "SELECT ID, NAME FROM BANK WHERE name=?";

        PreparedStatement stmt;

        try {

            this.openConnection();

            stmt = conn.prepareStatement(sql);.=
            

            stmt.setString(1, name);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                int id  = rs.getInt("ID");

                bank.setId(id);

            } else {

                throw new BankNotFoundException(name);

            }

        } catch (SQLException e) {

            e.printStackTrace();

            throw new DAOException();

        } finally {

            closeConnection();

        }

        return bank;

    }

}