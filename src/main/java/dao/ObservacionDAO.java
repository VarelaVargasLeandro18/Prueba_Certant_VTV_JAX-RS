package dao;

import dao_abstract.AbstractDAO;
import model.inspeccion.Observacion;

/**
 *
 * @author Varela Vargas Leandro Gastón
 */
public final class ObservacionDAO extends AbstractDAO<Observacion, Long> {
    
    public ObservacionDAO() {
        super(Observacion.class);
    }
    
}
