/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.w1908599smartcampuscw.mappers;

import com.mycompany.w1908599smartcampuscw.models.ErrorMessageModel;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.*;

/**
 *
 * @author ryand
 */

@Provider // This annotation registers the mapper with JAX-RS
public class RoomNotEmptyExceptionMapper implements ExceptionMapper<RoomNotEmptyException> {

    @Override
    public Response toResponse(RoomNotEmptyException e) {
        ErrorMessageModel errormessage = new ErrorMessageModel(
                e.getMessage(),
                409,
                "/api/v1/docs/errors/409");
        
        return Response.status(Response.Status.CONFLICT)
                       .type(MediaType.APPLICATION_JSON)
                       .entity(errormessage)
                       .build();
    }
    
}
