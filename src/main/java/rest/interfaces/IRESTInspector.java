package rest.interfaces;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import dao_abstract.exceptions.CreateEntityException;
import dao_abstract.exceptions.DeleteEntityException;
import dao_abstract.exceptions.ReadEntityException;
import model.personas.Inspector;

public interface IRESTInspector {
	
	@GET
	public List<Inspector> getInspectors() throws ReadEntityException;
	
	@GET
	@Path("/{InspectorId}")
	public Inspector getInspectorById( @PathParam("InspectorId") String InspectorId ) throws ReadEntityException;
	
	@POST
	public void postInspector (Inspector Inspector) throws CreateEntityException;
	
	@DELETE
	@Path("/{InspectorId}")
	public Inspector deleteInspector ( @PathParam("InspectorId") String InspectorId ) throws ReadEntityException, DeleteEntityException;
	
}
