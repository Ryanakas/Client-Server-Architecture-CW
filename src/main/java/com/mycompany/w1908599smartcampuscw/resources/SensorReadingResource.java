/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.w1908599smartcampuscw.resources;

import com.mycompany.w1908599smartcampuscw.models.SensorReading;
import com.mycompany.w1908599smartcampuscw.service.SensorReadingService;
import java.net.URI;
import javax.ws.rs.*;
import javax.ws.rs.core.*;



/**
 *
 * @author ryand
 */

public class SensorReadingResource{
    
    SensorReadingService service = new SensorReadingService();
    
    
    private String sensorId;

    public SensorReadingResource(String sensorId) {
        this.sensorId = sensorId;
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addSensorReading(SensorReading sensorReading){
        
        String response = service.addSensorReading(sensorReading,sensorId);

        if ("Readding added".equals(response)){
            URI newUri = URI.create("/sensors/sensorId/readings/" + sensorReading.getId());
            return Response.created(newUri)
                           .entity("Sensor Reading Created")
                           .build(); //returns header with room location
        }else{
            
                return Response.ok()
                               .entity("Sensor Reading Updated")
                               .build(); //returns header with room location
        }
        
    }
    
    @GET
    @Path("/{readingsId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRoom(
    @PathParam("readingsId") String readingsId
    ){
        SensorReading sensorReading = service.getSensorReading(readingsId);

        if (sensorReading == null) {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("Sensor Reading not found")
                           .build();
        }

        return Response.ok(sensorReading).build();
    }
    
    
    
    
}
