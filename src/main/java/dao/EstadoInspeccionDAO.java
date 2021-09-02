package dao;

import dao_abstract.AbstractDAO;
import dao_abstract.ReadEntityException;
import model.inspeccion.EstadoInspeccion;

/**
 *
 * @author Varela Vargas Leandro Gastón
 */
public final class EstadoInspeccionDAO extends AbstractDAO<EstadoInspeccion, Long> {

    public EstadoInspeccionDAO() {
        super(EstadoInspeccion.class);
    }
    
    public EstadoInspeccion leerPorEstado(String estado) throws ReadEntityException {
        try {
            String query = "SELECT ei FROM EstadoInspeccion ei WHERE ei.estado = :estado";

            return this.getEntityManager().createQuery(query, EstadoInspeccion.class)
                    .setParameter("estado", estado)
                    .getSingleResult();
        } catch ( Throwable ex ) {
            throw new ReadEntityException(ex);
        }
    }
    
}