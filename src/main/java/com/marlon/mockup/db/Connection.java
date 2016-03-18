/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marlon.mockup.db;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import java.sql.SQLException;

/**
 *
 * @author marlon
 */
public class Connection {
    
    private static ConnectionSource connectionSource = null;
    private static final String databaseUrl = "jdbc:mysql://localhost:3306/test";
    private static final String userdb = "root";
    private static final String passworddb = "";

    private Connection(){

    }
    
    public synchronized static ConnectionSource getInstance() throws SQLException{
        if(connectionSource == null){
            connectionSource = new JdbcConnectionSource(databaseUrl, userdb, passworddb);
        }
        return connectionSource;
    }
    
}
