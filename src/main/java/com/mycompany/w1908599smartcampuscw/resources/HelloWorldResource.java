/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.w1908599smartcampuscw.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

/**
 *
 * @author ryand
 */

@Path("/hello")
public class HelloWorldResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getHello() {
        return "Hello, World!";
    }
    
    @GET
    @Path("/test-error")
    @Produces(MediaType.APPLICATION_JSON)
    public Response testError() {
        String x = null;
        x.length(); // 💥 NullPointerException
        return Response.ok().build();
    }
}

