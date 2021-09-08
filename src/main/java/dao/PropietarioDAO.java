package dao;

import dao_abstract.AbstractDAO;
import dao_abstract.IPropietarioDAO;
import model.personas.Propietario;

/**
 *
 * @author Varela Vargas Leandro Gastón
 */
public class PropietarioDAO extends AbstractDAO<Propietario, Long> implements IPropietarioDAO {
    
    public PropietarioDAO() {
        super(Propietario.class);
    }
    
}