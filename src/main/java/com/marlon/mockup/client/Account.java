/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marlon.mockup.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 *
 * @author marlon
 */
public class Account {
    
    public static void main(String[] args){
        try{
            Client client = Client.create();
            WebResource webResource = client.resource("http://localhost:8080/mockup/api/v1/user");
            
            ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
            
            if(response.getStatus() != Response.Status.OK.getStatusCode()){
                throw new Exception("Erro ao obter usuário: " + response.getStatus());
            }
            String json = response.getEntity(String.class);
            System.out.println(json);
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
