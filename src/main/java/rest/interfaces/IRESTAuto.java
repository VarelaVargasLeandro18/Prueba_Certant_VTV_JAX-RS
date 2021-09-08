package rest.interfaces;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import dao_abstract.exceptions.CreateEntityException;
import dao_abstract.exceptions.DeleteEntityException;
import dao_abstract.exceptions.ReadEntityException;
import dao_abstract.exceptions.UpdateEntityException;
import model.Auto;

public interface IRESTAuto {
	
	@GET
	public List<Auto> getAutos() throws ReadEntityException;
	
	@GET
	@Path("/{autoId}")
	public Auto getAutoById( @PathParam("autoId") String autoId ) throws ReadEntityException;
	
	@POST
	public void postAuto (Auto auto) throws CreateEntityException;
	
	@DELETE
	@Path("/{autoId}")
	public Auto deleteAuto ( @PathParam("autoId") String autoId ) throws ReadEntityException, DeleteEntityException;
	
	@PUT
	@Path("/{autoId}")
	public Auto updateAuto ( Auto auto, @PathParam("autoId") String autoId ) throws UpdateEntityException, ReadEntityException;
	
	@GET
	@Path("/vencidos")
	public List<Auto> vencidos () throws ReadEntityException;
	
}
