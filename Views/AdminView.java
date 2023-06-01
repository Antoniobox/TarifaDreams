package Views;

import Controllers.GestorClientes;
import Controllers.GestorHabitaciones;
import Exceptions.EmptyArrayListException;
import Exceptions.InvalidUserException;
import Models.Habitacion;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AdminView {

    /**
     * Menu principal para los administradores
     * @return 1 para gestionar clientes, 2 para gestionar habitaciones, 3 para gestionar reservas, 4 para volver al menú anterior, 0 para salir de la aplicacion
     */
    public static int menuAdmin(){
        Scanner sc = new Scanner(System.in);
        int opcion=-1;
        do{
            System.out.println("""
                Bienvenido al menú de administrador, seleccione una de las siguientes opciones
                1. Gestionar clientes
                2. Gestionar habitaciones
                3. Gestionar reservas
                4. Volver al menú anterior
                0. Salir de la aplicación
                """);
            try{
                opcion = sc.nextInt();
            }catch(InputMismatchException e){
                System.out.println("Seleccione una opción numérica");
            }
            if(opcion<0 || opcion > 4){
                System.out.println("Seleccione una opción válida");
            }
        }while(opcion<0 || opcion > 4);
        return opcion;
    }

    public static void registroHabitaciones(GestorHabitaciones gh){
        int id, num_camas, max_personas;
        float precio = -1;
        String nombre, descripcion;

        Scanner sc = new Scanner(System.in);
        System.out.println("----------REGISTRO HABITACIONES----------");
        do{
            try{
                id = sc.nextInt();
                if(Habitacion.comprobarHabitacion(id, gh)){
                    System.out.println("Habitacion ya existente");
                    continue;
                }
            }catch(InputMismatchException e){
                System.out.println(e.getMessage());
                continue;
            }
            break;
        }while(true);

        do{
            nombre = sc.nextLine();
            if(nombre.length() < 0){
                System.out.println("Nombre vacio");
                continue;
            }
            break;
        }while(true);

        do{
            descripcion = sc.nextLine();
            if(descripcion.length() < 0){
                System.out.println("Nombre vacio");
                continue;
            }
            break;
        }while(true);

        do{
            try{
                num_camas = sc.nextInt();
                if(num_camas < 1){
                    System.out.println("Más de 0 habitaciones a ser posible");
                    continue;
                }
            }catch(InputMismatchException e){
                System.out.println("Formato numérico por dios");
                continue;
            }
            break;
        }while(true);

        do{
            try{
                max_personas = sc.nextInt();
                if(max_personas < 1){
                    System.out.println("Más de 0 habitaciones a ser posible");
                    continue;
                }
            }catch(InputMismatchException e){
                System.out.println("Formato numérico por dios");
                continue;
            }
            break;
        }while(true);

        do{
            try{
                precio = sc.nextFloat();
            }catch(InputMismatchException e){
                System.out.println("Precio inválido");
            }
            if(precio<0.0f){
                System.out.println("El precio es incorrecto");
            }
        }while(precio < 0.0f);

        gh.agregarHabitacion(new Habitacion(id, nombre, descripcion, num_camas, max_personas, precio));
    }
    public static void menuGestionClientes(GestorClientes gc){
        Scanner sc = new Scanner(System.in);
        int opcion = -1;
        do {
            System.out.println("------ MENÚ DE GESTIÓN DE CLIENTES ------");
            System.out.println("1. Agregar cliente");
            System.out.println("2. Consultar cliente");
            System.out.println("3. Actualizar cliente");
            System.out.println("4. Eliminar cliente");
            System.out.println("5. Regresar al menú anterior");
            System.out.print("Elija una opción: ");

            try{
                opcion = sc.nextInt();
            }catch(InputMismatchException e){
                opcion = -1;
            }

            switch (opcion) {
                case 1:
                    try{
                        gc.agregarCliente(UserView.menuRegistroCliente(gc));
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                        opcion = -1;
                    }
                    break;
                case 2:
                    gc.consultarCliente();
                    break;
                case 3:
                    try{
                        gc.actualizarCliente();
                        break;
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }

                case 4:
                    try{
                        gc.eliminarCliente();
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    System.out.println("Regresando al menú anterior...");
                    break;
                default:
                    System.out.println("Opción inválida, intente de nuevo");
            }
        } while (opcion != 5);
    }

    public static void menuGestionHabitaciones(GestorHabitaciones gh){
        Scanner sc = new Scanner(System.in);
        int opcion = -1;
        do {
            System.out.println("------ MENÚ DE GESTIÓN DE HABITACIONES ------");
            System.out.println("1. Agregar habitacion");
            System.out.println("2. Consultar habitacion");
            System.out.println("3. Actualizar habitacion");
            System.out.println("4. Eliminar habitacion");
            System.out.println("5. Regresar al menú anterior");
            System.out.print("Elija una opción: ");

            try{
                opcion = sc.nextInt();
            }catch(InputMismatchException e){
                opcion = -1;
            }

            switch (opcion) {
                case 1:
                    try{
                        registroHabitaciones(gh);
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                        opcion = -1;
                    }
                    break;
                case 2:
                    try{
                        gh.mostrarHabitaciones();
                    }catch(EmptyArrayListException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    try{
                        gh.actualizarCliente();
                        break;
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }

                case 4:
                    try{
                        gh.eliminarCliente();
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    System.out.println("Regresando al menú anterior...");
                    break;
                default:
                    System.out.println("Opción inválida, intente de nuevo");
            }
        } while (opcion != 5);
    }
}
