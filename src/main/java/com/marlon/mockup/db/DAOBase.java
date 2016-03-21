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
public class DAOBase <T>{
    private Class<T> to;
    private Dao<T, Integer> dao;
    
    public DAOBase(Class<T> to) throws SQLException {
        this.to = to;
        //this.to = (Class<T>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        dao = DaoManager.createDao(Connection.getInstance(), this.to);
    } 
    
    public String list() throws SQLException {
        return new Gson().toJson(dao.queryForAll());
    }
    
    public Object get(int id) throws SQLException{
        return dao.queryForId(id);
    }
    
    public void create(Object obj) throws SQLException{
        dao.create(to.cast(obj));
    }
    
    public void update(Object obj) throws SQLException{
        dao.update(to.cast(obj));
    }
    
    public void delete(Object obj) throws SQLException{
        dao.delete(to.cast(obj));
    }
}
