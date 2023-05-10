package Exceptions;

/**
 * Excepcion para cuando los campos de un registro de la BD de habitaciones no es válido por los datos que contiene
 * @author Antoniobox
 * @since 2023-05-09
 * @version 1.0
 */
public class InvalidFieldsHabitacion extends Exception{
    private String mensaje;
    public InvalidFieldsHabitacion(String mensaje){
        this.mensaje = mensaje;
    }

    @Override
    public String getMessage(){
        return mensaje;
    }
}
