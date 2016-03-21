/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marlon.mockup.db;

import com.google.gson.Gson;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;

/**
 *
 * @author marlon
 */
//public abstract class DAOFactory<T>{
public class DAOFactory<T>{
    private Class<T> to;
    Dao<T, String> dao;
    
    public DAOFactory(Class<T> to) throws SQLException {
        this.to = to;
        //this.to = (Class<T>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        dao = DaoManager.createDao(Connection.getInstance(), this.to);
    } 
    
    public String list() throws SQLException {
        return new Gson().toJson(dao.queryForAll());
    }
}
