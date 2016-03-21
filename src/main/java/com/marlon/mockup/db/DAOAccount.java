/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marlon.mockup.db;

import com.marlon.mockup.models.Account;
import java.sql.SQLException;

/**
 *
 * @author marlon
 */
public class DAOAccount extends DAOBase<Account> {
        
    public DAOAccount() throws SQLException{
        super(Account.class);
        //super();
    }
}
