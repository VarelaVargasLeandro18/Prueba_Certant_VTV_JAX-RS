package dao_abstract;

/**
 *
 * @author Varela Vargas Leandro Gastón
 */
public class UpdateEntityException extends Exception {

    /**
     * Crea una nueva UpdateEntityException con su mensaje por defecto
     */
    public UpdateEntityException() {
        super("No se pudo actualizar la entidad.");
    }

    /**
     * Crea una nueva UpdateEntityException con su causa.
     *
     * @param cause causa de la excepción.
     */
    public UpdateEntityException(Throwable cause) {
        super("No se pudo actualizar la entidad.", cause);
    }
}
