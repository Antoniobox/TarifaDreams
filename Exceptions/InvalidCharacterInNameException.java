package Exceptions;

public class InvalidCharacterInNameException extends Exception{
    public String mensaje;
    public InvalidCharacterInNameException(String mensaje){
        this.mensaje = mensaje;
    }

    @Override
    public String getMessage(){
        return mensaje;
    }
}
