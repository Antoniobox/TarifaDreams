package Exceptions;

public class EmptyStringException extends Exception {
    public String mensaje;
    public EmptyStringException(String mensaje){
        this.mensaje = mensaje;
    }

    @Override
    public String getMessage(){
        return mensaje;
    }
}
