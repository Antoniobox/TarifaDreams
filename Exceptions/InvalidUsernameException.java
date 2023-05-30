package Exceptions;

public class InvalidUsernameException extends Exception{
    public String mensaje;

    public InvalidUsernameException(String mensaje){
        this.mensaje = mensaje;
    }

    @Override
    public String getMessage(){
        return mensaje;
    }
}
