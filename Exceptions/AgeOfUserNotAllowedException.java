package Exceptions;

/**
 * Excepcion para cuando el usuario que intenta registrarse es menor de edad
 * @author Antoniobox
 * @since 2023-05-10
 * @version 1.0
 */
public class AgeOfUserNotAllowedException extends Exception{
    public String mensaje;

    public AgeOfUserNotAllowedException(String mensaje){
        this.mensaje = mensaje;
    }

    @Override
    public String getMessage(){
        return mensaje;
    }
}
