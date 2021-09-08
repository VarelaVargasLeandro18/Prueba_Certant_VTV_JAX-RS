package dao_abstract;

import dao_abstract.exceptions.ReadEntityException;
import model.personas.Inspector;

public interface IInspectorDAO extends IDAO<Inspector, Long> {
	
	public Inspector ingresar( String usuario, String contrasenia ) throws ReadEntityException;	
	
}