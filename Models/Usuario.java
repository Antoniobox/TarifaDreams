package Models;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;

/**
 * Clase Usuario
 *
 * @author Antoniobox
 * @since 2023/04/17
 * @version 1.0
 */
public class Usuario implements Serializable {
    private String nombreUsuario;
    private String password;
    private String email;
    private String notificaciones;
    private boolean rol;

    Usuario(){}

    public Usuario(String nombreUsuario, String password, String email, boolean rol) {
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.email = email;
        this.rol = rol;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNotificaciones() {
        return notificaciones;
    }

    public void setNotificaciones(String notificaciones) {
        this.notificaciones = notificaciones;
    }

    public boolean isRol() {
        return rol;
    }

    public void setRol(boolean rol) {
        this.rol = rol;
    }

}
