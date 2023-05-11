package Exceptions;

/**
 * Excepcion que se lanza cuando existe un error en el formato del dni, o cuando no se sigue el estandar que usa el mismo
 * @author Antoniobox
 * @since 2023-05-11
 * @version 1.0
 */
public class InvalidDNIFormatException extends Exception{
    public String mensaje;

    public InvalidDNIFormatException(String mensaje){
        this.mensaje = mensaje;
    }

    @Override
    public String getMessage(){
        return mensaje;
    }
}
