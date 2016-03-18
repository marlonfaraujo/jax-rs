/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marlon.mockup.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.marlon.mockup.db.DAOAccount;
import java.sql.SQLException;
import java.util.Date;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
        StringBuilder json = new StringBuilder();
        
        json.append("{\"id\": 13,");
        json.append("\"name\":\"Marlon Araújo.\",");
        json.append("\"cpf\":\"090.122.616-51\",");
        json.append("\"email\":\"marlonfda@gmail.com\",");
        json.append("\"phone\":\"(31)99999-9999\",");
        json.append("\"createdAt\":\"%s\"}");
        
        String strJson = String.format(json.toString(), dateTime).toString();
        
	return Response.ok(strJson, MediaType.APPLICATION_JSON + ";charset=utf-8").build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Path("/user{id: (/\\d+)?}")
    public Response get(@PathParam("id") String id){
        
        String strJson = "";
        JsonObject jsonObject = new JsonObject();
        
        if(id.isEmpty()){
            JsonArray jsonArray = new JsonArray();
            jsonObject.addProperty("id", 12);
            jsonObject.addProperty("name", "Lorem ipsum");
            jsonObject.addProperty("cpf", "666.666.666-66");
            jsonObject.addProperty("email", "loremipsum@gmail.com");
            jsonObject.addProperty("phone", "(31)98888-8888");
            jsonObject.addProperty("createdAt", new Date().getTime());
            jsonArray.add(jsonObject);
            
            jsonObject = new JsonObject();
            jsonObject.addProperty("id", 13);
            jsonObject.addProperty("name", "Marlon Araújo");
            jsonObject.addProperty("cpf", "090.122.616-51");
            jsonObject.addProperty("email", "marlonfda@gmail.com");
            jsonObject.addProperty("phone", "(31)99999-9999");
            jsonObject.addProperty("createdAt", new Date().getTime());
            jsonArray.add(jsonObject);
            
            strJson = jsonArray.toString();
        }else{
            jsonObject.addProperty("id", 13);
            jsonObject.addProperty("name", "Marlon Araújo");
            jsonObject.addProperty("cpf", "090.122.616-51");
            jsonObject.addProperty("email", "marlonfda@gmail.com");
            jsonObject.addProperty("phone", "(31)99999-9999");
            jsonObject.addProperty("createdAt", new Date().getTime());
            
            strJson = jsonObject.toString();
        }
        
	return Response.ok(strJson, MediaType.APPLICATION_JSON + ";charset=utf-8").build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Path("/users")
    public Response list(){
        
        String strJson = "";
        JsonObject jsonObject = new JsonObject();
        try {
            DAOAccount daoAccount = new DAOAccount();
            strJson = daoAccount.list();
        } catch (SQLException ex) {
            jsonObject.addProperty("code", 300);
            jsonObject.addProperty("message", "Erro database.");
            strJson = jsonObject.toString();
            
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(strJson).type(MediaType.APPLICATION_JSON + ";charset=utf-8").build();	
        }
        return Response.ok(strJson, MediaType.APPLICATION_JSON + ";charset=utf-8").build();
    }
    
}
