package com.cliffsun.transport.config;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.google.inject.Singleton;

@Provider
@Singleton
public class TflApiExceptionMapper implements ExceptionMapper<TflApiException>{

	@Override
	public Response toResponse(TflApiException exception) {
        return Response.status(Status.SERVICE_UNAVAILABLE).entity(exception.getMessage()).build(); 

	}

}
