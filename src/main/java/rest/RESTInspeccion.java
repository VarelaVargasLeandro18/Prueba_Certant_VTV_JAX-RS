package rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.InspeccionDAO;
import dao_abstract.CreateEntityException;
import dao_abstract.DeleteEntityException;
import dao_abstract.ReadEntityException;
import dao_abstract.UpdateEntityException;
import model.inspeccion.Inspeccion;

@Path("inspeccion")
// @RequestScoped Innecesarios
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RESTInspeccion {
	
	private InspeccionDAO dao;	
	
	public RESTInspeccion() {
		this.dao = new InspeccionDAO();
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
	
	/*@PUT
	@Path("/{InspeccionId}")
	public Inspeccion updateInspeccion ( Inspeccion Inspeccion, @PathParam("InspeccionId") String InspeccionId ) throws UpdateEntityException, ReadEntityException {
		Inspeccion InspeccionPersisted = this.dao.readOne( Long.parseLong(InspeccionId) );
		
		InspeccionPersisted.setEstado( Inspeccion.obtenerEstadoGeneral() );
		InspeccionPersisted.setFecha( Inspeccion.getFecha() );
		
		
		return this.dao.update(InspeccionPersisted);
	}*/
	
}
