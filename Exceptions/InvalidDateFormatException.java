package Exceptions;

public class InvalidDateFormatException extends Exception{
    public String mensaje;

    public InvalidDateFormatException(String mensaje){
        this.mensaje = mensaje;
    }

    @Override
    public String getMessage(){
        return mensaje;
    }
}
