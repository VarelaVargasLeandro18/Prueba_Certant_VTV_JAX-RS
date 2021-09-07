package dao;

import dao_abstract.AbstractDAO;
import dao_abstract.ReadEntityException;
import model.personas.TipoPropietario;

/**
 *
 * @author Varela Vargas Leandro Gastón
 */
public class TipoPropietarioDAO extends AbstractDAO<TipoPropietario, Long> {
    
    public TipoPropietarioDAO() {
        super(TipoPropietario.class);
    }
    
    public TipoPropietario buscarPorTipo (String tipo) throws ReadEntityException {
        try {
            return this.getEntityManager().createQuery( "SELECT tp FROM TipoPropietario tp WHERE tp.tipo = :tipo", TipoPropietario.class )
                .setParameter( "tipo" , tipo)
                .getSingleResult();
        } catch ( Throwable ex ) {
            throw new ReadEntityException(ex);
        }
    }
    
}