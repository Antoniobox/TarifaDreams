package Exceptions;

/**
 * Excepcion para cuando el cliente ya existía en el listado de clientes
 * @author Antoniobox
 * @since 2023-05-14
 * @version 1.0
 */
public class ClientAddedBeforeException extends Exception{
    public String mensaje;
    public ClientAddedBeforeException(String mensaje){
        this.mensaje = mensaje;
    }

    @Override
    public String getMessage(){
        return mensaje;
    }
}
