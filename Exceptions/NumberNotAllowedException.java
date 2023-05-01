package Exceptions;

/**
 * NumberNotAllowedException
 * Excepcion para cuando el número de telefono no está permitido.
 * Ejemplo: 123456789
 */
public class NumberNotAllowedException extends Exception{
    String mensaje;
    public NumberNotAllowedException(String mensaje){
        this.mensaje = mensaje;
    }

    @Override
    public String getMessage(){
        return mensaje;
    }
}
