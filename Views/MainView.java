package Views;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Clase que contiene los métodos del menú principal
 */
public class MainView {
    //TODO pensar si se debería de hacer un método para cuando se requiere el retroceder hacia atras
    //TODO pensar si poner aquí el método para iniciar sesión
    /**
     * Método que muestra el menú principal. También pide al usuario elegir entre la opción de registrarse o la de iniciar sesión
     * @return 1 en caso de querer registrarse. 2 en caso de querer iniciar sesión. 0 en caso de salir
     */
    public static int menuInicio(){
        int opcion = -1;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("""
					Selecciones una de las siguientes opciones
					0. Salir
					1. Registrar un usuario
					2. Iniciar Sesión""");
            try{
                opcion=sc.nextInt();
            }catch(InputMismatchException e){
                System.out.println("Debes de elegir una opción numérica");
                sc.nextLine();
            }
        }while(opcion!=1 && opcion != 2 && opcion != 0);
        sc.close();
        return opcion;
    }

    public static boolean menuInicioSesion(){
        Scanner sc = new Scanner(System.in);
        String email, frase;
        System.out.println("Introduce tu correo electronico");
        email = sc.nextLine();
        System.out.println("Introduce tu codigo secreto");
        frase = sc.nextLine();
    }
}
