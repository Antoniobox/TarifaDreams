package Exceptions;

public class InvalidNumberOfFieldsException extends Exception{
    private String mensaje;
    public InvalidNumberOfFieldsException(String mensaje){
        this.mensaje = mensaje;
    }

    @Override
    public String getMessage(){
        return mensaje;
    }
}
