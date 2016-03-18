/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marlon.mockup.db;

import com.google.gson.Gson;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.marlon.mockup.models.Account;
import java.sql.SQLException;

/**
 *
 * @author marlon
 */
public class DAOAccount {
    Dao<Account, String> daoAccount;
    
    public DAOAccount(){
    }
    
    public String list() throws SQLException{
        daoAccount = DaoManager.createDao(Connection.getInstance(), Account.class);
        return new Gson().toJson(daoAccount.queryForAll());
    }
}
