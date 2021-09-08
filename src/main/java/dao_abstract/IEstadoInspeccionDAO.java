package dao_abstract;

import dao_abstract.exceptions.ReadEntityException;
import model.inspeccion.EstadoInspeccion;

public interface IEstadoInspeccionDAO extends IDAO <EstadoInspeccion, Long> {
	
	public EstadoInspeccion leerPorEstado(String estado) throws ReadEntityException;
	
}
