package dao;

import dao_abstract.AbstractDAO;
import model.inspeccion.Medicion;

/**
 *
 * @author Varela Vargas Leandro Gastón
 */
public class MedicionDAO extends AbstractDAO<Medicion, Long> {
    
    public MedicionDAO() {
        super(Medicion.class);
    }
    
}
