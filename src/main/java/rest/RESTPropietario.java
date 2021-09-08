package rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao_abstract.IPropietarioDAO;
import dao_abstract.exceptions.CreateEntityException;
import dao_abstract.exceptions.DeleteEntityException;
import dao_abstract.exceptions.ReadEntityException;
import jwt.JWTAuthorization;
import model.personas.Propietario;
import rest.interfaces.IRESTPropietario;

@Path("propietario")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@JWTAuthorization
public class RESTPropietario implements IRESTPropietario {

	private IPropietarioDAO dao;
	
	public RESTPropietario(IPropietarioDAO dao) {
		this.dao = dao;
	}
	
	@Override
	public List<Propietario> getPropietarios() throws ReadEntityException {
		return this.dao.readAll();
	}
	
	@Override
	public Propietario getPropietarioById( String PropietarioId ) throws ReadEntityException {
		return this.dao.readOne( Long.parseLong(PropietarioId) );
	}
	
	@Override
	public void postPropietario (Propietario Propietario) throws CreateEntityException {
		this.dao.create(Propietario);
	}
	
	@Override
	public Propietario deletePropietario ( String PropietarioId ) throws ReadEntityException, DeleteEntityException {
		Propietario Propietario = this.dao.readOne( Long.parseLong(PropietarioId) );
		this.dao.delete(Propietario);
		return Propietario;
	}
	
}