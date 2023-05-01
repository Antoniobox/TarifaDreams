package Exceptions;

/**
 * InvalidFormatNumberException class
 * Call this exception for phone numbers format that may be wrong
 */
public class InvalidFormatNumberException extends Exception{
    String mensaje;

    InvalidFormatNumberException(String mensaje){
        this.mensaje = mensaje;
    }

    @Override
    public String getMessage(){
        return mensaje;
    }
}
