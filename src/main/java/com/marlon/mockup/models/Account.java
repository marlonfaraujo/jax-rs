/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marlon.mockup.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 *
 * @author marlon
 */

@DatabaseTable(tableName = "ACCOUNT")
public class Account {
    
    @DatabaseField(generatedId = true, unique = true)
    private int id;
    
    @DatabaseField(canBeNull = false)
    private String name;
    
    @DatabaseField(canBeNull = false, unique = true)
    private String cpf;
    
    @DatabaseField(canBeNull = false, unique = true)
    private String email;
    
    private String phone;
    
    private long createdAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }
}
