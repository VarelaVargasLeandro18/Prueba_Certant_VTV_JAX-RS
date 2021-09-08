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

public interface IRESTAuto extends IREST<Auto, String> {
	
	@GET
	@Path("/vencidos")
	public List<Auto> vencidos () throws ReadEntityException;
	
}
