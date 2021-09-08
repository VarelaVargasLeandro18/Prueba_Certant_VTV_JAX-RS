package dao_abstract;

import com.mycompany.prueba_tecnica_vtv.JPAEntityManagerFactory;

import dao_abstract.exceptions.CreateEntityException;
import dao_abstract.exceptions.DeleteEntityException;
import dao_abstract.exceptions.ReadEntityException;
import dao_abstract.exceptions.UpdateEntityException;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * @author Varela Vargas Leandro Gastón
 * @param <T> Clase de la entidad.
 * @param <K> Clase/primitivo de la llave de la entidad.
 */
public abstract class AbstractDAO<T,K> implements IDAO<T,K> {
    
    private final Class<T> entityClass;

    public AbstractDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
    
    protected EntityManager getEntityManager() {
        return JPAEntityManagerFactory.getEntityManager();
    }

    @Override
    public T readOne(K id) throws ReadEntityException {
        
        T findedEntity = null;
        
        try {
            findedEntity = this.getEntityManager().find( this.entityClass , id );
        }
        catch ( Throwable ex ) {
            throw new ReadEntityException(ex);
        }
        
        return findedEntity;
        
    }

    @Override
    public List<T> readAll() throws ReadEntityException {
        
        List<T> findedEntities;
        EntityManager em = this.getEntityManager();
        
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery cq = cb.createQuery();
            Root <T> rootEntity = cq.from(this.entityClass);
            
            cq.select(rootEntity);
            
            findedEntities = em.createQuery(cq).getResultList();
        }
        catch ( Throwable ex ) {
            throw new ReadEntityException(ex);
        }
        
        return findedEntities;
    }

    @Override
    public void delete(T entity) throws DeleteEntityException  {
        try {
            this.getEntityManager().getTransaction().begin();
            entity = this.getEntityManager().merge(entity);
            this.getEntityManager().remove(entity);
            this.getEntityManager().getTransaction().commit();
        }
        catch ( Throwable ex ) {
            this.rollbackTransactions();
            throw new DeleteEntityException(ex);
        }
    }

    @Override
    public T update(T updated) throws UpdateEntityException {
        try {
            return this.getEntityManager().merge(updated);
        }
        catch ( Throwable ex ) {
            throw new UpdateEntityException(ex);
        }
    }
    
    @Override
    public void create( T created ) throws CreateEntityException {
        try {
            this.getEntityManager().getTransaction().begin();
            this.getEntityManager().persist(created);
            this.getEntityManager().getTransaction().commit();
        } catch ( Throwable ex ) {
            this.rollbackTransactions();
            throw new CreateEntityException(ex);
        }
    }
    
    public void rollbackTransactions() {
        this.getEntityManager().getTransaction().rollback();
    }
    
    public void commitTransactions() {
        this.getEntityManager().getTransaction().commit();
    }
    
}
