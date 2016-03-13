/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marlon.mockup.api;

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
        
        long dateTime = new Date().getTime();
        StringBuilder json = new StringBuilder();
        
        if(id.isEmpty()){
            json.append("[{\"id\":12,");
            json.append("\"name\":\"Lorem ipsum.\",");
            json.append("\"cpf\":\"666.666.666-66\",");
            json.append("\"email\":\"loremipsum@gmail.com\",");
            json.append("\"phone\":\"(31)98888-8888\",");
            json.append("\"createdAt\":\"%s\"},");
            json.append("{\"id\": 13,");
            json.append("\"name\":\"Marlon Araújo.\",");
            json.append("\"cpf\":\"090.122.616-51\",");
            json.append("\"email\":\"marlonfda@gmail.com\",");
            json.append("\"phone\":\"(31)99999-9999\",");
            json.append("\"createdAt\":\"%s\"}]");
        }else{
            json.append("{\"id\": 13,");
            json.append("\"name\":\"Marlon Araújo.\",");
            json.append("\"cpf\":\"090.122.616-51\",");
            json.append("\"email\":\"marlonfda@gmail.com\",");
            json.append("\"phone\":\"(31)99999-9999\",");
            json.append("\"createdAt\":\"%s\"}");
        }
        
        String strJson = String.format(json.toString(), dateTime, dateTime).toString();
        
	return Response.ok(strJson, MediaType.APPLICATION_JSON + ";charset=utf-8").build();
    }
}
