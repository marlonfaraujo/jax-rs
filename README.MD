
[![Build Status](https://travis-ci.org/marlonfda/jax-rs.svg?branch=master)](https://travis-ci.org/marlonfda/jax-rs)

##Examples

Java Class (AccountService.java):
```java
package com.marlon.mockup.api;
import ...

@Path("/v1")
public class AccountService {
    
    @POST
    @Path("/user")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response register(String content) {
        long dateTime = new Date().getTime();
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
        
        return Response.ok(new Gson().toJson(account), MediaType.APPLICATION_JSON + ";charset=utf-8").build();
    }
}

```