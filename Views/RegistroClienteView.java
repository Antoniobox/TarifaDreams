package Views;

import Exceptions.InvalidFormatNumberException;
import Exceptions.NumberNotAllowedException;
import Models.Cliente;
import Utils.Validaciones;

import java.util.Scanner;

/**
 * Clase con métodos para las vistas del proceso de registro de un cliente
 * @author Antoniobox
 * @since 2023-05-12
 * @version 1.0
 */
public class RegistroClienteView {
    /**
     * Muestra un menu de registro para registrar clientes. Devuelve un cliente instanciado en base a los datos recogidos en el método
     * @return cliente instanciando con los datos recogidos en el método
     */
    public Cliente menuRegistroCliente(){
        Scanner sc = new Scanner(System.in);
        String nombre, apellidos, email, telefono, fechaNacimiento, dni, frase;

        //Se recoge el nombre del cliente
        do {
            System.out.println("Introduce tu nombre:");
            nombre=sc.nextLine();
            try{
                Validaciones.nombreYApellidos(nombre, false);
            }catch(Exception e){
                System.out.println(e.getMessage());
                continue;
            }
            break;
        }while(true);

        //Se recogen los apellidos del cliente
        do {
            System.out.println("Introduce tus apellidos");
            apellidos=sc.nextLine();
            try{
                Validaciones.nombreYApellidos(apellidos, true);
            }catch(Exception e){
                System.out.println(e.getMessage());
                continue;
            }
            break;
        }while(true);

        //Se recoge el email del cliente
        do {
            System.out.println("Introduce el email");
            email=sc.nextLine();
            try{
                Validaciones.email(email);
            }catch(Exception e){
                System.out.println(e.getMessage());
                continue;
            }
            break;
        }while(true);

        //Se recoge el número de telefono del cliente
        do {
            System.out.println("Introduce el numero de telefono");
            telefono=sc.nextLine();
            try{
                Validaciones.telefono(telefono);
            }catch(Exception e){
                System.out.println(e.getMessage());
                continue;
            }
            break;
        }while(true);

        //Se recoge la fecha de nacimiento del cliente
        do {
            System.out.println("Introduce la fecha de nacimiento");
            fechaNacimiento=sc.nextLine();
            try{
                Validaciones.fecha(fechaNacimiento, false);
            }catch(Exception e){
                System.out.println(e.getMessage());
                continue;
            }
            break;
        }while(true);

        //Se recoge el dni del cliente
        do{
            System.out.println("Introduce tu dni");
            dni=sc.nextLine();
            try{
                Validaciones.dni(dni);
            }catch(Exception e){
                System.out.println(e.getMessage());
                continue;
            }
            break;
        }while(true);

        //Se calcula el codigo secreto del cliente
        String codigoSecreto;
        do {
            System.out.println("Introduce cuatro palabras separadas por espacios");
            frase=sc.nextLine();
            try{
                codigoSecreto = Validaciones.control(frase);
            }catch (Exception e){
                e.getMessage();
                continue;
            }
            break;
        }while(true);

        return new Cliente(nombre, apellidos, email, telefono, dni, fechaNacimiento, codigoSecreto);
    }
}
