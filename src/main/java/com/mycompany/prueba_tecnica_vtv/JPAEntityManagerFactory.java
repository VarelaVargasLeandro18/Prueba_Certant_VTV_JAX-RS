package com.mycompany.prueba_tecnica_vtv;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Varela Vargas Leandro Gastón
 */
public class JPAEntityManagerFactory {
    
    private static final EntityManagerFactory emf = buildFactory();
    private static EntityManager em = null;
    
    private static final String PERSISTENCE_UNIT_NAME = "Prueba_Tecnica_VTV";
    
    private static EntityManagerFactory buildFactory() {
        
        try {
            return Persistence.createEntityManagerFactory( PERSISTENCE_UNIT_NAME );
        }
        catch ( Throwable ex ) {
            throw new RuntimeException(ex);
        }
        
    }
    
    public static EntityManager getEntityManager() {
        
        if ( em == null || !em.isOpen() )
            em = emf.createEntityManager();
                
        return em;
    }
    
}
