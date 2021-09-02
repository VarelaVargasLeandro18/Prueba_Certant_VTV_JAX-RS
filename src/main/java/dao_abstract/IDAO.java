package dao_abstract;

import java.util.List;

/**
 * Interface que define la funcionalidad de un CRUD standard.
 * @author bapo
 * @param <T> Clase de la entidad.
 * @param <K> Clase del @Id de la entidad.
 */
public interface IDAO<T, K> {
    
    /**
     * Lee una entidad por ID.
     * @param id
     * @return entidad buscada o null si no la encuentra.
     * @throws dao_abstract.ReadEntityException
     */
    public T readOne ( K id ) throws ReadEntityException;
    
    /**
     * Lee todas las entidades.
     * @return lista de entidades.
     * @throws dao_abstract.ReadEntityException
     */
    public List<T> readAll () throws ReadEntityException;
    
    /**
     * Borra un entidad.
     * @param entity
     * @throws dao_abstract.DeleteEntityException
     */
    public void delete ( T entity ) throws DeleteEntityException;
    
    /**
     * Actualiza una entidad.
     * @param updated
     * @return entidad actualizada.
     * @throws dao_abstract.UpdateEntityException
     */
    public T update ( T updated ) throws UpdateEntityException;
    
    /**
     * Crea una entidad.
     * @param created 
     * @throws dao_abstract.CreateEntityException 
     */
    public void create ( T created ) throws CreateEntityException;
    
}
