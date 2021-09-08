package dao;

import java.time.LocalDateTime;
import java.util.List;

import dao_abstract.AbstractDAO;
import dao_abstract.IInspeccionDAO;
import dao_abstract.exceptions.ReadEntityException;
import model.inspeccion.Inspeccion;
import model.personas.Propietario;

/**
 *
 * @author Varela Vargas Leandro Gastón
 */
public class InspeccionDAO extends AbstractDAO<Inspeccion, Long> implements IInspeccionDAO {
    
    public InspeccionDAO() {
        super(Inspeccion.class);
    }
    
    /**
     * Segundo Informe Pt2 - Obtención de Lista de Inspecciones en los últimos tres días.
     * @return List inspecciones en los últimos tres días.
     * @throws dao_abstract.exceptions.ReadEntityException
     */
    @Override
    public List<Inspeccion> obtenerInspeccionesUltimosTresDias() throws ReadEntityException {
        try {
            String query = "SELECT I FROM Inspeccion I WHERE I.fecha >= :tresdiasantes";
            return this.getEntityManager()
                    .createQuery(query, Inspeccion.class)
                    .setParameter("tresdiasantes", LocalDateTime.now().minusDays(3l).withHour(0).withMinute(0).withSecond(0))
                    .getResultList();
        } catch ( Throwable ex ) {
            throw new ReadEntityException(ex);
        }
    }
    
    /**
     * Tercer Informe Pt2 - Obtención de Inspecciones realizadas a los autos de una persona en particular con más de un auto.
     * @param p Propietario cuyas inspecciones se quieren conocer
     * @return List inspecciones de un dueño si tiene más de tres autos. Empty si solo tiene uno.
     */
    @Override
    public List<Inspeccion> obtenerInspeccionesDePersonaSiTieneMasDeUnAuto( Propietario p ) throws ReadEntityException {
        try {
            String query = "FROM Inspeccion I WHERE I.inspeccionado IN "
                    + "(SELECT A FROM Auto A WHERE A.propietario IN "
                    + "(SELECT A.propietario FROM Auto A GROUP BY A.propietario HAVING COUNT(A.propietario) > 1 AND A.propietario = :propietario) )";
            return this.getEntityManager()
                    .createQuery(query, Inspeccion.class)
                    .setParameter("propietario", p)
                    .getResultList();
        } catch ( Throwable ex ) {
            throw new ReadEntityException(ex);
        }     
    }
    
}
