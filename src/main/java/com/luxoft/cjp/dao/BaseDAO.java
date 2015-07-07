package com.luxoft.cjp.dao;

import java.sql.Connection;

/**
 * Created by pamajcher on 2015-07-01.
 */
public interface BaseDAO {
    Connection openConnection() throws DAOException;
    Connection closeConnection();
}
