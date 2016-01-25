package com.companyName.RestTest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path(value="/companyName")
public interface UpgradeEndpoint {
	
	// method to start a process with the following parameters
	@GET
	@Path(value = "/{group}/{artifact}/{version}/{processId}")
	public Response startProcess(@PathParam("group") String group, @PathParam("artifact") String artifact,
			@PathParam("version") String version, @PathParam("processId") String processId);
	
	@GET
	@Path(value = "/{processInstanceIdString}/{sendSignal}")
	public Response sendSignal(@PathParam("processInstanceIdString") String processInstanceIdString, @PathParam("sendSignal") String sendSignal);
	
	
	@GET
	@Path(value = "/{group}/{artifact}/{version}/{processId}/{processInstanceIdString}")
	public Response retrieveStatus(@PathParam("group") String group, @PathParam("artifact") String artifact,
			@PathParam("version") String version, @PathParam("processId") String processId, @PathParam("processInstanceIdString") String processInstanceIdString);
	
	// method to check the whether extended path of the application is functioning properly
	@GET
	@Path(value = "/health")
	public Response healthCheck();
}
