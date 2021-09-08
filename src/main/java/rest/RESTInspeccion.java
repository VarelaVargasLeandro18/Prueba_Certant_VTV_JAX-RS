package rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao_abstract.IInspeccionDAO;
import dao_abstract.IPropietarioDAO;
import dao_abstract.exceptions.CreateEntityException;
import dao_abstract.exceptions.DeleteEntityException;
import dao_abstract.exceptions.ReadEntityException;
import dao_abstract.exceptions.UpdateEntityException;
import jwt.JWTAuthorization;
import model.inspeccion.Inspeccion;
import model.personas.Propietario;
import rest.interfaces.IRESTInspeccion;

@Path("inspeccion")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@JWTAuthorization
public class RESTInspeccion implements IRESTInspeccion {
	
	private IInspeccionDAO dao;	
	private IPropietarioDAO pDao;
	
	public RESTInspeccion(IInspeccionDAO dao, IPropietarioDAO pDao) {
		this.dao = dao;
		this.pDao = pDao;
	}
	
	@Override
	public List<Inspeccion> get() throws ReadEntityException {
		return this.dao.readAll();
	}
	
	@Override
	public Inspeccion get( Long InspeccionId ) throws ReadEntityException {
		return this.dao.readOne( InspeccionId );
	}
	
	@Override
	public void post(Inspeccion Inspeccion) throws CreateEntityException {
		this.dao.create(Inspeccion);
	}
	
	@Override
	public Inspeccion delete( Long InspeccionId ) throws ReadEntityException, DeleteEntityException {
		Inspeccion Inspeccion = this.dao.readOne( InspeccionId );
		this.dao.delete(Inspeccion);
		return Inspeccion;
	}

	@Override
	public List<Inspeccion> inspeccionesUltimosTresDias() throws ReadEntityException {
		List<Inspeccion> inspecciones = this.dao.obtenerInspeccionesUltimosTresDias();
		return inspecciones;
	}

	@Override
	public List<Inspeccion> inspeccionesDePersonaConMasDeUnAuto( Long CUIL ) throws ReadEntityException {
		Propietario propietario = this.pDao.readOne(CUIL);
		List<Inspeccion> inspecciones = this.dao.obtenerInspeccionesDePersonaSiTieneMasDeUnAuto( propietario );
		return inspecciones;
	}

	@Override
	public Inspeccion update(Inspeccion entity, Long Id) throws UpdateEntityException, ReadEntityException {
		if ( entity == null ) throw new UpdateEntityException();
		// TODO Auto-generated method stub
		return null;
	}
	
}
