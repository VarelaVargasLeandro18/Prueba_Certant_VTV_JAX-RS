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
import model.inspeccion.Inspeccion;

public interface IRESTInspeccion {
	
	@GET
	public List<Inspeccion> getInspeccions() throws ReadEntityException;
	
	@GET
	@Path("/{InspeccionId}")
	public Inspeccion getInspeccionById( @PathParam("InspeccionId") String InspeccionId ) throws ReadEntityException;
	
	@POST
	public void postInspeccion (Inspeccion Inspeccion) throws CreateEntityException;
	
	@DELETE
	@Path("/{InspeccionId}")
	public Inspeccion deleteInspeccion ( @PathParam("InspeccionId") String InspeccionId ) throws ReadEntityException, DeleteEntityException;
	
}
