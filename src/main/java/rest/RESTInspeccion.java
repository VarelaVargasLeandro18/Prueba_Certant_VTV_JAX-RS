package rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao_abstract.IInspeccionDAO;
import dao_abstract.exceptions.CreateEntityException;
import dao_abstract.exceptions.DeleteEntityException;
import dao_abstract.exceptions.ReadEntityException;
import jwt.JWTAuthorization;
import model.inspeccion.Inspeccion;
import rest.interfaces.IRESTInspeccion;

@Path("inspeccion")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@JWTAuthorization
public class RESTInspeccion implements IRESTInspeccion {
	
	private IInspeccionDAO dao;	
	
	public RESTInspeccion(IInspeccionDAO dao) {
		this.dao = dao;
	}
	
	@Override
	public List<Inspeccion> getInspeccions() throws ReadEntityException {
		return this.dao.readAll();
	}
	
	@Override
	public Inspeccion getInspeccionById( String InspeccionId ) throws ReadEntityException {
		return this.dao.readOne( Long.parseLong(InspeccionId) );
	}
	
	@Override
	public void postInspeccion (Inspeccion Inspeccion) throws CreateEntityException {
		this.dao.create(Inspeccion);
	}
	
	@Override
	public Inspeccion deleteInspeccion ( String InspeccionId ) throws ReadEntityException, DeleteEntityException {
		Inspeccion Inspeccion = this.dao.readOne( Long.parseLong(InspeccionId) );
		this.dao.delete(Inspeccion);
		return Inspeccion;
	}
	
}
