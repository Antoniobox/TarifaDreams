package Exceptions;

public class DuplicatedHabitacionException extends Exception{
    public String mensaje;

    public DuplicatedHabitacionException(String mensaje){
        this.mensaje = mensaje;
    }

    @Override
    public String getMessage(){
        return mensaje;
    }
}