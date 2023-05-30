package Views;

import Exceptions.OptionOutOfRangeException;
import Models.Cliente;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Clase AdministradorView con los menús para los administradores
 * @author Antoniobox
 * @since 2023-05-30
 * @version 1.0
 */
public class AdministradorView {
    /**
     * Menu para los administradores que les permite gestionar los tipos de datos del hotel
     * @return opcion seleccionada. 1 para gestionar clientes, 2 para gestionar habitaciones, 3 para gestionar reservas, 4 para volver al menú anterior, 5 para salir de la aplicación
     * @throws OptionOutOfRangeException
     */
    public static int menuAdministrador() throws OptionOutOfRangeException {
        Scanner sc = new Scanner(System.in);
        int opcion = -1;
        System.out.println("""
                1. Gestionar clientes
                2. Gestionar habitaciones
                3. Gestionar reservas
                4. Volver al menú anterior
                0. Salir de la aplicación
                """);
        do{
            try{
                opcion = sc.nextInt();
            }catch(InputMismatchException e){
                System.out.println("Introduce una cifra");
            }
            if(opcion < 0 || opcion > 4){
                throw new OptionOutOfRangeException("Opción inválida");
            }
        }while(opcion < 0 || opcion > 4);
        sc.close();
        return opcion;
    }

    /**
     * Muestra las opciones que tiene un administrador sobre los clientes
     */
    private static void mostrarOpcionesGestionCliente(){
        System.out.println("""
                1. Eliminar cliente
                2. Actualizar cliente
                3. Mostrar datos
                """);
    }

    public static void gestionarClientes(ArrayList<Cliente> clientes) throws OptionOutOfRangeException {
        Scanner sc = new Scanner(System.in);
        int cliente = -1;
        System.out.println("Ha accedido a la gestión de clientes");
        do{
            for(int i=0; i<clientes.size(); i++){
                System.out.println(i+". ");
                clientes.get(i).infoBasica();
            }
            System.out.println("Seleccione un cliente para gestionar");
            try{
                cliente = sc.nextInt();
            }catch(InputMismatchException e){
                System.out.println("Introduce una opción numérica");
            }
            if(cliente < 0 || cliente >= clientes.size()){
                throw new OptionOutOfRangeException("Opción inválida");
            }
        }while(cliente < 0 || cliente >= clientes.size());


        mostrarOpcionesGestionCliente();
    }
}
