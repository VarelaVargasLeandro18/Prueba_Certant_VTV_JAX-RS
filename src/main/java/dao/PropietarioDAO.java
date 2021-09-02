package dao;

import dao_abstract.AbstractDAO;
import model.personas.Propietario;

/**
 *
 * @author Varela Vargas Leandro Gastón
 */
public final class PropietarioDAO extends AbstractDAO<Propietario, Long> {
    
    public PropietarioDAO() {
        super(Propietario.class);
    }
    
}