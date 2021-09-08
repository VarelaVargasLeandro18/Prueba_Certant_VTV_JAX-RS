package dao_abstract;

import java.util.List;

import dao_abstract.exceptions.ReadEntityException;
import model.inspeccion.Inspeccion;
import model.personas.Propietario;

public interface IInspeccionDAO extends IDAO<Inspeccion, Long> {
	
	public List<Inspeccion> obtenerInspeccionesUltimosTresDias() throws ReadEntityException;
	public List<Inspeccion> obtenerInspeccionesDePersonaSiTieneMasDeUnAuto( Propietario p ) throws ReadEntityException;
	
}
