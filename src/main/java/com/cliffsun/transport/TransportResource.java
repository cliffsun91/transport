package com.cliffsun.transport;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/transport")
public class TransportResource {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String transportGet() {
		return "Transport Service v0.0";
	}
	
}
