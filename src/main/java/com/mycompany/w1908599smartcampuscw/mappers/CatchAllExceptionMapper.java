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
public class CatchAllExceptionMapper implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable e) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                       .type(MediaType.APPLICATION_JSON)
                       .entity(new ErrorMessageModel(
                            "An unexpected error occurred",
                            500,
                            "/api/v1/docs/errors/500"
                        ))
                        .build();
    }
    
}
