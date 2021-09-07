package dao;

import java.time.LocalDateTime;
import java.util.List;

import dao_abstract.AbstractDAO;
import dao_abstract.ReadEntityException;
import model.Auto;
import model.inspeccion.EstadoInspeccion;

/**
 *
 * @author Varela Vargas Leandro Gastón
 */
public class AutoDAO extends AbstractDAO<Auto, String> {

    public AutoDAO() {
        super(Auto.class);
    }
    
    /**
     * Primer Informe - Obtención de Autos Inspeccionados en la última semana,
     * es decir, desde hace siete días del día de la fecha hasta hoy.
     * @return List autos inspeccionados en la semana.
     * @throws dao_abstract.ReadEntityException
     */
    public List<Auto> inspeccionadosSemana() throws ReadEntityException {
        
        try {
            String query = "SELECT I.inspeccionado FROM Inspeccion I WHERE I.fecha >= :ultimaSemana";
            
            return this.getEntityManager()
                    .createQuery(query, Auto.class)
                    .setParameter("ultimaSemana", LocalDateTime.now().minusWeeks(1l))
                    .getResultList();
        } catch ( Throwable ex ) {
            throw new ReadEntityException(ex);
        }
        
    }
    
    /**
     * Cuarto Informe - Chequeo de fechas de vencimiento dentro del año de haberse
     * ejecutado una Inspeccion o que no esté aprobada.
     * @param aprobado Valor de String de un estado aprobado.
     * @return 
     * @throws dao_abstract.ReadEntityException 
     */
    public List<Auto> chequeoVencimiento (EstadoInspeccion aprobado) throws ReadEntityException {
        
        try {
            String query = "SELECT i.inspeccionado FROM Inspeccion i "
                    + "WHERE i.estado = :aprobado AND i.fecha <= :unanioatras";
            return this.getEntityManager()
                    .createQuery(query, Auto.class)
                    .setParameter("aprobado", aprobado)
                    .setParameter("unanioatras", LocalDateTime.now().minusYears(1l))
                    .getResultList();
        } catch(Throwable ex) {
            throw new ReadEntityException(ex);
        }
        
    }
    
    /**
     * Quinto Informe - Buscar por condicion
     */
    public List<Auto> buscarPorCondicion ( EstadoInspeccion condicion ) throws ReadEntityException {
        
        try {
            String query = "SELECT i.inspeccionado FROM Inspeccion i "
                    + "WHERE i.estado = :condicion";
            return this.getEntityManager()
                    .createQuery(query, Auto.class)
                    .setParameter("condicion", condicion)
                    .getResultList();
        } catch(Throwable ex) {
            throw new ReadEntityException(ex);
        }
        
    }
    
}