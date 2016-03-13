/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marlon.mockup.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.BindException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 *
 * @author marlon
 */
public class Account {
    
    public static final String BASE_URL = "http://localhost:8080/mockup/api/";
    public static final String RESOURCE_PACKAGE = "com.marlon.mockup.api";
    
    public static void main(String[] args){
        try{
            createHttpServer();
            Client client = Client.create();
            WebResource webResource = client.resource(BASE_URL + "v1/user/");
            ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
            if(response.getStatus() != Response.Status.OK.getStatusCode()){
                throw new Exception("Erro ao obter dados do usuário: " + response.getStatus());
            }
            String json = response.getEntity(String.class);
            System.out.println(json);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private static void createHttpServer() {
        try{
            ResourceConfig resourceConfig = new PackagesResourceConfig(RESOURCE_PACKAGE);
            HttpServer httpServer = HttpServerFactory.create(BASE_URL, resourceConfig);
            httpServer.start();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
