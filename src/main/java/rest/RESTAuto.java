package rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao_abstract.IAutoDAO;
import dao_abstract.IEstadoInspeccionDAO;
import dao_abstract.exceptions.CreateEntityException;
import dao_abstract.exceptions.DeleteEntityException;
import dao_abstract.exceptions.ReadEntityException;
import dao_abstract.exceptions.UpdateEntityException;
import jwt.JWTAuthorization;
import model.Auto;
import rest.interfaces.IRESTAuto;

@Path("auto")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@JWTAuthorization
public class RESTAuto implements IRESTAuto {
	
	private IAutoDAO dao;
	private IEstadoInspeccionDAO eiDao;
	
	public RESTAuto (IAutoDAO dao, IEstadoInspeccionDAO eiDao) {
		this.dao = dao;
		this.eiDao = eiDao;
	}
	
	@Override
	public List<Auto> get() throws ReadEntityException{
		return this.dao.readAll();
	}
	
	@Override
	public Auto get( String autoId ) throws ReadEntityException{
		return this.dao.readOne(autoId);
	}

	@Override
	public void post(Auto auto) throws CreateEntityException {
		this.dao.create(auto);
	}
	
	@Override
	public Auto delete( String autoId ) throws ReadEntityException, DeleteEntityException {
		Auto auto = this.dao.readOne(autoId);
		this.dao.delete(auto);
		return auto;
	}
	
	@Override
	public Auto update( Auto auto, String autoId ) throws UpdateEntityException, ReadEntityException {
		Auto autoPersisted = this.dao.readOne(autoId);
		
		autoPersisted.setMarca( auto.getMarca() );
		autoPersisted.setModelo( auto.getModelo() );
		autoPersisted.setPropietario( auto.getPropietario() );
		
		return this.dao.update(autoPersisted);
	}
	
	@Override
	public List<Auto> vencidos () throws ReadEntityException {
		return this.dao.chequeoVencimiento(this.eiDao.leerPorEstado("Apto"));
	}

}
