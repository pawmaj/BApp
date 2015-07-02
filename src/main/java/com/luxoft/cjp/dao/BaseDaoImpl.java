/*
package com.luxoft.cjp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

*/
/**
 * Created by pamajcher on 2015-07-01.
 *//*


    public class BaseDaoImpl {

        Connection conn;

        public Connection openConnection() throws DAOException {

            try {

                Class.forName("org.h2.Driver"); // this is driver for H2

                conn = DriverManager.getConnection("jdbc:h2:~/bank",

                        "sa", // login

                        "" // password

                );

                return conn;

            } catch(ClassNotFoundException | SQLException e) {

                e.printStackTrace();

                throw new DAOException();

            }

            return null;

        }



        public void closeConnection() {

            try {

                conn.close();

            } catch(SQLException e) {

                e.printStackTrace();

            }

        }

    }
}
*/
