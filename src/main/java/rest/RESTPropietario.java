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

import dao.PropietarioDAO;
import dao_abstract.CreateEntityException;
import dao_abstract.DeleteEntityException;
import dao_abstract.ReadEntityException;
import model.personas.Propietario;

@Path("propietario")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RESTPropietario {

	private PropietarioDAO dao;
	
	public RESTPropietario() {
		this.dao = new PropietarioDAO();
	}
	
	@GET
	public List<Propietario> getPropietarios() throws ReadEntityException {
		return this.dao.readAll();
	}
	
	@GET
	@Path("/{PropietarioId}")
	public Propietario getPropietarioById( @PathParam("PropietarioId") String PropietarioId ) throws ReadEntityException {
		return this.dao.readOne( Long.parseLong(PropietarioId) );
	}
	
	@POST
	public void postPropietario (Propietario Propietario) throws CreateEntityException {
		this.dao.create(Propietario);
	}
	
	@DELETE
	@Path("/{PropietarioId}")
	public Propietario deletePropietario ( @PathParam("PropietarioId") String PropietarioId ) throws ReadEntityException, DeleteEntityException {
		Propietario Propietario = this.dao.readOne( Long.parseLong(PropietarioId) );
		this.dao.delete(Propietario);
		return Propietario;
	}
	
}