package Exceptions;

public class InvalidEmailFormatException extends Exception{
    public String mensaje;

    public InvalidEmailFormatException(String mensaje){
        this.mensaje = mensaje;
    }

    @Override
    public String getMessage(){
        return mensaje;
    }
}