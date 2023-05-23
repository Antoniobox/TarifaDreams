package Views;

import Models.Cliente;
import Utils.Validaciones;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * View para inicio de sesión de un cliente
 * @author Antoniobox
 * @since 2023-05-15
 * @version 1.0
 */
public class InicioSesionView {

    /**
     * Muestra el menu de inicio de sesión de un cliente y devuelve el cliente en caso de que haya iniciado sesión
     * @return objeto cliente en caso de haber iniciado sesión, null en caso de que haya cancelado el inicio de sesión
     */
    public static Cliente menuInicioSesion(ArrayList<Cliente> listadoClientes){
        String correo, codigo, volverAIntentar;
        boolean seguirIntentando = true, intentoFallido = true;
        Scanner sc = new Scanner(System.in);
        System.out.println("------------------ INICIO SESIÓN ------------------");
        do{
            try{
                if(!seguirIntentando) {
                    return null;
                }
                System.out.println("Introduce tu correo electrónico");
                correo = sc.nextLine();
                Validaciones.email(correo);
                System.out.println("Introduce tu código secreto");
                codigo = sc.nextLine();
                for(Cliente cliente : listadoClientes){
                    if(cliente.equals(correo, codigo)){
                        return cliente;
                    }
                }
                System.out.println("No se ha encontrado el cliente");
                System.out.println("¿Seguir intentando?(S/N)");
                volverAIntentar = sc.nextLine();
                seguirIntentando = volverAIntentar.equals("S") || volverAIntentar.equals("s");
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }while(true);
    }
}
