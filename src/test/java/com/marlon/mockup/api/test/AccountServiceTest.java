/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marlon.mockup.api.test;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.net.httpserver.HttpServer;
import java.util.Date;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 *
 * @author marlon
 */
public class AccountServiceTest{
    
    public static final String BASE_URL = "http://localhost:8080/mockup/api/";
    public static final String RESOURCE_PACKAGE = "com.marlon.mockup.api";
    public static final String INFO_USER = "v1/user";
    public static final long TIMEOUT = 200;
    public HttpServer httpServer;
    public Thread thread; 
    public static long startTime;
    public static long endTime;
    
    private static HttpServer createHttpServer() {
        try{
            ResourceConfig resourceConfig = new PackagesResourceConfig(RESOURCE_PACKAGE);
            return HttpServerFactory.create(BASE_URL, resourceConfig);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    private void threadStart() throws Exception{
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                startTime = new Date().getTime();
                Client client = Client.create();
                WebResource webResource = client.resource(BASE_URL + INFO_USER);
                ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
                endTime = new Date().getTime();    
            }
        });
        thread.start();
    }
    
    @Before
    public void setUp() throws Exception{
        httpServer = createHttpServer();
        if(httpServer != null && httpServer instanceof HttpServer){
            httpServer.start();
        }
        if(thread == null){
            threadStart();
        }
    }
    
    @After
    public void tearDown() throws Exception{
        if(httpServer != null && httpServer instanceof HttpServer){
            httpServer.stop(0);
        }
        if(thread != null && thread instanceof Thread && thread.isAlive()){
            thread.interrupt();
        }
    }
    
    /**
     * Test of get method, of class AccountService.
     */
    @Test
    public void testGet() throws Exception{
        Client client = Client.create();
        WebResource webResource = client.resource(BASE_URL + INFO_USER);
        ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        String json = response.getEntity(String.class);
        JsonObject jsonObject = new JsonObject();
        if(json != null){
            jsonObject = new JsonParser().parse(json).getAsJsonObject();
            System.out.println(json);
        }
        //Erro connection database - Travis CI
        if(jsonObject.get("code") == null || jsonObject.get("code").getAsInt() != 300){
            Assert.assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        }
    }
    
    @Test(timeout = TIMEOUT)
    public void timeoutTest() throws Exception {
        if(thread != null && thread instanceof Thread){ // && thread.isAlive()  
            Assert.assertTrue(endTime - startTime <= TIMEOUT);
        }else{
            throw new InterruptedException("Timeout");
        }
    }
}
