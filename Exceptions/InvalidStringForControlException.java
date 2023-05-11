package Exceptions;

public class InvalidStringForControlException extends Exception{
    public String mensaje;

    public InvalidStringForControlException(String mensaje){
        this.mensaje = mensaje;
    }

    @Override
    public String getMessage(){
        return mensaje;
    }
}
