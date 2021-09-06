package rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.InspectorDAO;
import dao_abstract.CreateEntityException;
import dao_abstract.DeleteEntityException;
import dao_abstract.ReadEntityException;
import jwt.JWTAuthorization;
import model.personas.Inspector;

@Path("inspector")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@JWTAuthorization
public class RESTInspector {
	
	private InspectorDAO dao;	
	
	public RESTInspector() {
		this.dao = new InspectorDAO();
	}
	
	@GET
	public List<Inspector> getInspectors() throws ReadEntityException {
		return this.dao.readAll();
	}
	
	@GET
	@Path("/{InspectorId}")
	public Inspector getInspectorById( @PathParam("InspectorId") String InspectorId ) throws ReadEntityException {
		return this.dao.readOne( Long.parseLong(InspectorId) );
	}
	
	@POST
	public void postInspector (Inspector Inspector) throws CreateEntityException {
		this.dao.create(Inspector);
	}
	
	@DELETE
	@Path("/{InspectorId}")
	public Inspector deleteInspector ( @PathParam("InspectorId") String InspectorId ) throws ReadEntityException, DeleteEntityException {
		Inspector Inspector = this.dao.readOne( Long.parseLong(InspectorId) );
		this.dao.delete(Inspector);
		return Inspector;
	}

}
