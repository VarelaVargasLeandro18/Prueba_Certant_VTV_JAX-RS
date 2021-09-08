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
import model.personas.Propietario;

public interface IRESTPropietario {

	@GET
	public List<Propietario> getPropietarios() throws ReadEntityException;
	
	@GET
	@Path("/{PropietarioId}")
	public Propietario getPropietarioById( @PathParam("PropietarioId") String PropietarioId ) throws ReadEntityException;
	
	@POST
	public void postPropietario (Propietario Propietario) throws CreateEntityException;
	
	@DELETE
	@Path("/{PropietarioId}")
	public Propietario deletePropietario ( @PathParam("PropietarioId") String PropietarioId ) throws ReadEntityException, DeleteEntityException;
	
	
	
}
