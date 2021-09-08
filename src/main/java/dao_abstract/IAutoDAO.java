package dao_abstract;

import java.util.List;

import dao_abstract.exceptions.ReadEntityException;
import model.Auto;
import model.inspeccion.EstadoInspeccion;

public interface IAutoDAO extends IDAO<Auto, String> {
	
	public List<Auto> inspeccionadosSemana() throws ReadEntityException;
	public List<Auto> chequeoVencimiento (EstadoInspeccion aprobado) throws ReadEntityException;
	public List<Auto> buscarPorCondicion ( EstadoInspeccion condicion ) throws ReadEntityException;
	
}
