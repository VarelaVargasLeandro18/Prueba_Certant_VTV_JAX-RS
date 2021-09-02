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

//import org.glassfish.jersey.process.internal.RequestScoped;

import dao.AutoDAO;
import dao.EstadoInspeccionDAO;
import dao_abstract.CreateEntityException;
import dao_abstract.DeleteEntityException;
import dao_abstract.ReadEntityException;
import dao_abstract.UpdateEntityException;
import model.Auto;



@Path("auto")
// @RequestScoped Innecesario
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RESTAuto {
	
	private AutoDAO dao;
	
	public RESTAuto () {
		this.dao = new AutoDAO();
	}
	
	@GET
	public List<Auto> getAutos() throws ReadEntityException {
		return this.dao.readAll();
	}
	
	@GET
	@Path("/{autoId}")
	public Auto getAutoById( @PathParam("autoId") String autoId ) throws ReadEntityException {
		return this.dao.readOne(autoId);
	}
	
	@POST
	public void postAuto (Auto auto) throws CreateEntityException {
		this.dao.create(auto);
	}
	
	@DELETE
	@Path("/{autoId}")
	public Auto deleteAuto ( @PathParam("autoId") String autoId ) throws ReadEntityException, DeleteEntityException {
		Auto auto = this.dao.readOne(autoId);
		this.dao.delete(auto);
		return auto;
	}
	
	@PUT
	@Path("/{autoId}")
	public Auto updateAuto ( Auto auto, @PathParam("autoId") String autoId ) throws UpdateEntityException, ReadEntityException {
		Auto autoPersisted = this.dao.readOne(autoId);
		
		autoPersisted.setMarca( auto.getMarca() );
		autoPersisted.setModelo( auto.getModelo() );
		autoPersisted.setPropietario( auto.getPropietario() );
		
		return this.dao.update(autoPersisted);
	}
	
	@GET
	@Path("/vencidos")
	public List<Auto> vencidos () throws ReadEntityException {
		EstadoInspeccionDAO eiDAO = new EstadoInspeccionDAO();
		return this.dao.chequeoVencimiento(eiDAO.leerPorEstado("Apto"));
	}

}
