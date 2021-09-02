package dao;

import dao_abstract.AbstractDAO;
import dao_abstract.ReadEntityException;
import model.personas.Inspector;

/**
 *
 * @author Varela Vargas Leandro Gastón
 */
public final class InspectorDAO extends AbstractDAO<Inspector, Long> {

    public InspectorDAO() {
        super(Inspector.class);
    }
    
    /**
     * Verifica si el Inspector cuyos usuario y contraseñas ingresados existe y lo devuelve.
     * @param usuario usuario del Inspector
     * @param contrasenia contraseña del Inspector
     * @return Inspector inspector encontrado.
     */
    public Inspector ingresar( String usuario, String contrasenia ) throws ReadEntityException {
        
        try {
            String query = "SELECT I FROM Inspector I WHERE I.usuario = :usuario AND I.contrasenia = :contrasenia";
            return this.getEntityManager()
                    .createQuery(query, Inspector.class)
                    .setParameter("usuario", usuario)
                    .setParameter("contrasenia", contrasenia)
                    .getSingleResult();
        } catch ( Throwable ex ) {
            throw new ReadEntityException(ex);
        }
        
    }
    
}
