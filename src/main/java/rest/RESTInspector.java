package rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao_abstract.IInspectorDAO;
import dao_abstract.exceptions.CreateEntityException;
import dao_abstract.exceptions.DeleteEntityException;
import dao_abstract.exceptions.ReadEntityException;
import dao_abstract.exceptions.UpdateEntityException;
import jwt.JWTAuthorization;
import model.personas.Inspector;
import rest.interfaces.IRESTInspector;

@Path("inspector")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@JWTAuthorization
public class RESTInspector implements IRESTInspector {
	
	private IInspectorDAO dao;	
	
	public RESTInspector(IInspectorDAO dao) {
		this.dao = dao;
	}
	
	@Override
	public List<Inspector> get() throws ReadEntityException {
		return this.dao.readAll();
	}
	
	@Override
	public Inspector get( Long InspectorId ) throws ReadEntityException {
		return this.dao.readOne( InspectorId );
	}
	
	@Override
	public void post(Inspector Inspector) throws CreateEntityException {
		this.dao.create(Inspector);
	}
	
	@Override
	public Inspector delete( Long InspectorId ) throws ReadEntityException, DeleteEntityException {
		Inspector Inspector = this.dao.readOne( InspectorId );
		this.dao.delete(Inspector);
		return Inspector;
	}

	@Override
	public Inspector update(Inspector auto, Long Id) throws UpdateEntityException, ReadEntityException {
		// TODO Auto-generated method stub
		return null;
	}

}
