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

public interface IREST<T,K> {

	@GET
	public List<T> get() throws ReadEntityException;
	
	@GET
	@Path("/{Id}")
	public T get( @PathParam("Id") K Id ) throws ReadEntityException;
	
	@POST
	public void post(T entity) throws CreateEntityException;
	
	@DELETE
	@Path("/{Id}")
	public T delete( @PathParam("Id") K Id ) throws ReadEntityException, DeleteEntityException;
	
	@PUT
	@Path("/{Id}")
	public T update( T enity, @PathParam("Id") K Id ) throws UpdateEntityException, ReadEntityException;
	
}
