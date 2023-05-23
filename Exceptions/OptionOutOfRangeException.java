package Exceptions;

/**
 * Excepcion que indica que una opción seleccionada por el usuario no se encuentra entre las opciones permitidas
 * @author Antoniobox
 * @version 1.0
 * @since 2023-05-23
 */
public class OptionOutOfRangeException extends Exception{
    public String mensaje;

    public OptionOutOfRangeException(String mensaje){
        this.mensaje = mensaje;
    }

    @Override
    public String getMessage(){
        return mensaje;
    }
}
