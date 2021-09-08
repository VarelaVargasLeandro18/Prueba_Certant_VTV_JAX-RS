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

import dao_abstract.IInspeccionDAO;
import dao_abstract.exceptions.CreateEntityException;
import dao_abstract.exceptions.DeleteEntityException;
import dao_abstract.exceptions.ReadEntityException;
import jwt.JWTAuthorization;
import model.inspeccion.Inspeccion;

@Path("inspeccion")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@JWTAuthorization
public class RESTInspeccion {
	
	private IInspeccionDAO dao;	
	
	public RESTInspeccion(IInspeccionDAO dao) {
		this.dao = dao;
	}
	
	@GET
	public List<Inspeccion> getInspeccions() throws ReadEntityException {
		return this.dao.readAll();
	}
	
	@GET
	@Path("/{InspeccionId}")
	public Inspeccion getInspeccionById( @PathParam("InspeccionId") String InspeccionId ) throws ReadEntityException {
		return this.dao.readOne( Long.parseLong(InspeccionId) );
	}
	
	@POST
	public void postInspeccion (Inspeccion Inspeccion) throws CreateEntityException {
		this.dao.create(Inspeccion);
	}
	
	@DELETE
	@Path("/{InspeccionId}")
	public Inspeccion deleteInspeccion ( @PathParam("InspeccionId") String InspeccionId ) throws ReadEntityException, DeleteEntityException {
		Inspeccion Inspeccion = this.dao.readOne( Long.parseLong(InspeccionId) );
		this.dao.delete(Inspeccion);
		return Inspeccion;
	}
	
}
