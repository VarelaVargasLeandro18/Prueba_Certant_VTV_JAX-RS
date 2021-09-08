package dao_abstract.exceptions;

/**
 *
 * @author Varela Vargas Leandro Gastón
 */
public class ReadEntityException extends Exception {

    /**
     * Crea una nueva ReadEntityException con su mensaje por defecto.
     */
    public ReadEntityException() {
        super("No se pudo leer la/s entidad/es solicitada.");
    }

    /**
     * Construye una ReadEntityException con su causa.
     *
     * @param cause causa de la excepción.
     */
    public ReadEntityException(Throwable cause) {
        super("No se pudo leer la/s entidad/es solicitada.", cause);
    }

}