package rest.interfaces;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import dao_abstract.exceptions.ReadEntityException;
import model.inspeccion.Inspeccion;

public interface IRESTInspeccion extends IREST<Inspeccion, Long> {
	@GET
	@Path("/ultimosTresDias")
	public List<Inspeccion> inspeccionesUltimosTresDias () throws ReadEntityException;
	
	@GET
	@Path("/dePersonasConMasDeUnAuto/{CUIL}")
	public List<Inspeccion> inspeccionesDePersonaConMasDeUnAuto( @PathParam("CUIL") Long CUIL ) throws ReadEntityException;
	
}
