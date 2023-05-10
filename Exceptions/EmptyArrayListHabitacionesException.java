package Exceptions;

/**
 * Excepcion para cuando se requiere el ArrayList de habitaciones con alguna habitación
 * @author Antoniobox
 * @since 2023-05-10
 * @version 1.0
 */
public class EmptyArrayListHabitacionesException extends Exception{
    public String mensaje;
    public EmptyArrayListHabitacionesException(String mensaje){
        this.mensaje = mensaje;
    }

    @Override
    public String getMessage(){
        return mensaje;
    }
}
