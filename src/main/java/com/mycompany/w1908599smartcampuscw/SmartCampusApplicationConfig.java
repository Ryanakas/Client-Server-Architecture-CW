/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.w1908599smartcampuscw;


import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig; 

/**
 *
 * @author ryand
 */

@ApplicationPath("/api/v1")
public class SmartCampusApplicationConfig extends ResourceConfig{
    
    public SmartCampusApplicationConfig() {

        packages("com.mycompany.w1908599smartcampuscw.resources");
        packages("com.mycompany.w1908599smartcampuscw.mappers");
        packages("com.mycompany.w1908599smartcampuscw.filter");


    }   
    
}
