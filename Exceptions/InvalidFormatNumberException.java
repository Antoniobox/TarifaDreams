package Exceptions;

/**
 * InvalidFormatNumberException class
 * Excepcion para cuando el formato del número de teléfono es incorrecto
 */
public class InvalidFormatNumberException extends Exception{
    String mensaje;

    public InvalidFormatNumberException(String mensaje){
        this.mensaje = mensaje;
    }

    @Override
    public String getMessage(){
        return mensaje;
    }
}
