package dao_abstract;

/**
 *
 * @author Varela Vargas Leandro Gastón
 */
public class DeleteEntityException extends Exception {

    /**
     * Crea una nueva DeleteEntityException con su mensaje por defecto.
     */
    public DeleteEntityException() {
        super("No se pudo borrar la entidad.");
    }

    /**
     * Crea una nueva DeleteEntityException con su causa.
     *
     * @param cause causa de la excepción.
     */
    public DeleteEntityException(Throwable cause) {
        super("No se pudo borrar la entidad.", cause);
    }

}