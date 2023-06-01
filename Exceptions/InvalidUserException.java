package Exceptions;

/**
 * Excepcion para cuando en el registro un usuario ya existe
 * @author Antoniobox
 */
public class InvalidUserException extends Exception{
    String mensaje;
    public InvalidUserException(String mensaje){
        this.mensaje = mensaje;
    }

    @Override
    public String getMessage(){
        return mensaje;
    }
}
