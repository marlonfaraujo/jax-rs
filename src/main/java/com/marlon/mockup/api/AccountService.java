/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marlon.mockup.api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.marlon.mockup.db.DAOAccount;
import com.marlon.mockup.db.DAOFactory;
import com.marlon.mockup.models.Account;
import java.sql.SQLException;
import java.util.Date;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author marlon
 */

@Path("/v1")
public class AccountService {
    
    @POST
    @Path("/user")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response register(String content) {
        long dateTime = new Date().getTime();
        //StringBuilder json = new StringBuilder();
        //json.append("\"createdAt\":\"%s\"}");
        //String strJson = String.format(json.toString(), dateTime).toString();
        JsonObject jsonObject = new JsonObject();
        try{
            Account account = new Gson().fromJson(content, Account.class);
            account.setCreatedAt(dateTime);
            new DAOAccount().create(account);
        }catch(SQLException e){
            jsonObject.addProperty("code", 300);
            jsonObject.addProperty("message", "Erro database.");
            jsonObject.addProperty("description", e.getMessage());
            
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(jsonObject.toString()).type(MediaType.APPLICATION_JSON + ";charset=utf-8").build();
        }
	return Response.ok(content, MediaType.APPLICATION_JSON + ";charset=utf-8").build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Path("/user{noop: (/)?}{id: ((?<=/)\\d+)?}")
    public Response get(@PathParam("id") int id){
        
        Account account;
        JsonObject jsonObject = new JsonObject();
        
        try{
            account = (Account) new DAOAccount().get(id);
        }catch(SQLException e){
            jsonObject.addProperty("code", 300);
            jsonObject.addProperty("message", "Erro database.");
            jsonObject.addProperty("description", e.getMessage());
            
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(jsonObject.toString()).type(MediaType.APPLICATION_JSON + ";charset=utf-8").build();
            
        }
        
        if(account == null){
            jsonObject.addProperty("code", 500);
            jsonObject.addProperty("message", "Not found");
            jsonObject.addProperty("description", "Usuário não encontrado com os parâmetros informados");
            
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(jsonObject.toString()).type(MediaType.APPLICATION_JSON + ";charset=utf-8").build();
            
        }
        return Response.ok(new Gson().toJson(account), MediaType.APPLICATION_JSON + ";charset=utf-8").build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Path("/users")
    public Response list(){
        
        String strJson = "";
        JsonObject jsonObject = new JsonObject();
        try {
            //strJson = new DAOFactory<Account>(Account.class).list();
            //strJson = new DAOFactory<Account>() {}.list();
            strJson = new DAOAccount().list();
        } catch (SQLException e) {
            jsonObject.addProperty("code", 300);
            jsonObject.addProperty("message", "Erro database.");
            jsonObject.addProperty("description", e.getMessage());
            
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(jsonObject.toString()).type(MediaType.APPLICATION_JSON + ";charset=utf-8").build();	
        }
        return Response.ok(strJson, MediaType.APPLICATION_JSON + ";charset=utf-8").build();
    }
    
}
