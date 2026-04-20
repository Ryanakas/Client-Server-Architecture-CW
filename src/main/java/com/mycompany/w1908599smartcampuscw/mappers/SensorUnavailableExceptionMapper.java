/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.w1908599smartcampuscw.mappers;

import com.mycompany.w1908599smartcampuscw.models.ErrorMessageModel;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author ryand
 */

@Provider // This annotation registers the mapper with JAX-RS
public class SensorUnavailableExceptionMapper implements ExceptionMapper<SensorUnavailableException> {

    @Override
    public Response toResponse(SensorUnavailableException e) {
        ErrorMessageModel errormessage = new ErrorMessageModel(
                e.getMessage(),
                403,
                "/api/v1/docs/errors/403");
        
        return Response.status(Response.Status.FORBIDDEN)
                       .type(MediaType.APPLICATION_JSON)
                       .entity(errormessage)
                       .build();
    }
    
    
}
