package Models;

/**
 * Clase Administrador, la cual hereda de la clase Usuario
 *
 * @author Antoniobox antonioboxsanchez@gmail.com
 * @version 1.0
 * @since 2023-04-23
 */
public class Administrador extends Usuario{
    private  boolean activo;

    Administrador(String nombre, String password, String email, boolean rol){
        super(nombre, password, email, rol);
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
