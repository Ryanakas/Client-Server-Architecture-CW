/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.w1908599smartcampuscw.resources;


import com.mycompany.w1908599smartcampuscw.models.Sensor;
import com.mycompany.w1908599smartcampuscw.service.SensorService;
import java.net.URI;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;



/**
 *
 * @author ryand
 */


@Path("/sensors")
public class SensorResource {

    
    private SensorService service = new SensorService();
    
    
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addSensor(Sensor sensor){
        
        String response = service.addSensor(sensor);
        
        if ("Room404".equals(response)){
            
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("Room not found")
                           .build();
            
            
            
            
        }else if("Sensor Created".equals(response)){
            
            URI newUri = URI.create("/sensors/" + sensor.getId());
            return Response.created(newUri)
                           .entity("Sensor Created")
                           .build(); //returns header with room location
        
            
        
        }else{
            return Response.status(Response.Status.CONFLICT)
                       .entity("Sensor with this ID already exists")
                       .build();
        
        } 
        
        
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllSensors(
    @QueryParam("type") String type
    ){
        
        List<Sensor> response = service.getAllSensors(type);
        
        return  Response.ok()
                        .entity(response)
                        .build();
        
        
    }
    
    @Path("/{id}/readings")
    public SensorReadingResource getSensorReadingResource(
    @PathParam("id") String sensorId
    ) {
        return new SensorReadingResource(sensorId);
    }
        
    
    

}
