package dao_abstract;

/**
 *
 * @author Varela Vargas Leandro Gastón
 */
public class CreateEntityException extends Exception {

    /**
     * Crea una nueva CreateEntityException con su mensaje por defecto.
     */
    public CreateEntityException() {
        super("No se pudo crear la entidad.");
    }

    /**
     * Crea una nueva CreateEntityException con su causa.
     *
     * @param cause causa de la excepción.
     */
    public CreateEntityException(Throwable cause) {
        super("No se pudo crear la entidad.", cause);
    }

}