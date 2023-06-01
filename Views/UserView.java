package Views;

import Controllers.GestorClientes;
import Exceptions.InvalidUserException;
import Exceptions.OptionOutOfRangeException;
import Models.Cliente;
import Models.Habitacion;
import Models.Reservas;
import Utils.Validaciones;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Clase con métodos para las vistas del proceso de registro de un cliente
 * @author Antoniobox
 * @since 2023-05-12
 * @version 1.0
 */
public class UserView {
    public static final float IVA = 0.21f;

    /**
     * Muestra un menu de registro para registrar clientes. Devuelve un cliente instanciado en base a los datos recogidos en el método
     * @return cliente instanciando con los datos recogidos en el método y usuario instanciado
     */
    public static Cliente menuRegistroCliente(GestorClientes gc) throws InvalidUserException {
        Scanner sc = new Scanner(System.in);
        String nombre, apellidos, email, telefono, fechaNacimiento, dni, frase, codigoSecreto ,nombreUsuario;;
        boolean volverARealizarRegistro = false, usuarioIncorrecto=false;

        do{
            usuarioIncorrecto = false;
            System.out.println("------------------ REGISTRO CLIENTE ------------------");
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

            do{
                System.out.println("Introduce tu nombre de usuario");
                nombreUsuario = sc.nextLine();
                try{
                    Validaciones.nombreUsuario(nombreUsuario);
                }catch(Exception e){
                    System.out.println(e.getMessage());
                    continue;
                }
                break;
            }while(true);

            //Se calcula el codigo secreto del cliente
            do {
                System.out.println("Introduce cuatro palabras separadas por espacios");
                frase=sc.nextLine();
                try{
                    codigoSecreto = Validaciones.control(frase);
                }catch (Exception e){
                    System.out.println(e.getMessage());;
                    continue;
                }
                break;
            }while(true);
            if(gc.buscarCliente(email, dni)){
                usuarioIncorrecto = true;
                System.out.println("Cliente ya existente");
                System.out.println("¿Desea volver a realizar el formulario?(s/n)");
                volverARealizarRegistro = sc.nextLine().equals("s");
            }
        }while(volverARealizarRegistro);
        if(usuarioIncorrecto){
            throw new InvalidUserException("Usuario inválido");
        }
        //Una vez verifica que el usuario es correcto, se imprimen sus datos de inicio de sesión
        System.out.println("Para iniciar sesión, tendrás que usar las siguientes credenciales:\n"+
                "email: "+email+"\n" +
                "Codigo Secreto: "+codigoSecreto);
        return new Cliente (nombre, apellidos, email, telefono, dni, fechaNacimiento, nombreUsuario, codigoSecreto);
    }

    /**
     * Método que muestra el menú principal. También pide al usuario elegir entre la opción de registrarse o la de iniciar sesión
     * @return 1 en caso de querer registrarse. 2 en caso de querer iniciar sesión. 0 en caso de salir
     */
    public static int menuInicio(){
        int opcion = -1;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("""
					Seleccione una de las siguientes opciones
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
        return opcion;
    }

    /**
     * Menú para el inicio de sesión
     * @param gc Gestor de clientes
     * @return cliente en caso de que coincidan los parametros con algun registro, null en caso de que no encontrarlo
     */
    public static Cliente menuInicioSesion(GestorClientes gc){
        Scanner sc = new Scanner(System.in);
        String email, frase;
        System.out.println("Introduce tu correo electronico");
        email = sc.nextLine();
        System.out.println("Introduce tu codigo secreto");
        frase = sc.nextLine();
        return gc.comprobarSiClienteExiste(email, frase);
    }

    /**
     * Menú que se muestra a los clientes que ya han iniciado sesión
     * @return opción seleccionada. 1 Reservar habitación, 2 FAQS, 0 Salir
     */
    public static int menuCliente() throws OptionOutOfRangeException{
        int opcion = 0;
        Scanner sc = new Scanner(System.in);
        do{
            System.out.println("1. Reservar habitación");
            System.out.println("2. FAQS");
            System.out.println("0. Salir");
            try{
                opcion = sc.nextInt();
            }catch(InputMismatchException nfe){
                System.out.println("Introduzca un número, por favor");
                sc.nextLine();
                continue;
            }catch(Exception e){
                System.out.println(e.getMessage());
                sc.nextLine();
                continue;
            }
            if(opcion < 0 || opcion > 2){
                throw new OptionOutOfRangeException("Opción inválida");
            }
            break;
        }while(true);
        return opcion;
    }

    /**
     * Menú que pide al usuario una opción para el pago de una reserva.
     * @return 1 si quiere pagar con tarjeta, 2 si quiere pagar con Bizum
     */
    public static int menuSeleccionPago(){
        Scanner sc = new Scanner(System.in);
        int opcion;
        do{
            try{
                System.out.println("Elige un método de pago\n" +
                        "1.Tarjeta de crédito\n" +
                        "2.Bizum");
                opcion = sc.nextInt();
            }catch(InputMismatchException e){
                System.out.println("Opción inválida");
                continue;
            }
            break;
        }while(true);
        return opcion;
    }

    /**
     * Permite al usuario pagar con tarjeta las reservas
     * @return true si el pago realizado, false en el caso contrario
     */
    public static boolean pagoConTarjeta(){
        String tarjetaCredito = "";
        Scanner sc = new Scanner(System.in);
        boolean pagoRealizado = false;
        do{
            System.out.println("Introduce el número de tarjeta de crédito");
            tarjetaCredito = sc.nextLine();

        }while(!Validaciones.validarTarjeta(tarjetaCredito));
        String visa = "415006";
        String americanExpress = "375699";
        String masterCard = " 515878";
        if(tarjetaCredito.substring(0, 6).equals(visa)){
            System.out.println("Tienes una VISA");
            pagoRealizado=true;
        }
        else if(tarjetaCredito.substring(0, 6).equals(americanExpress)){
            System.out.println("Tienes una American Express");
            pagoRealizado=true;
        }
        else if(tarjetaCredito.substring(0, 6).equals(masterCard)){
            System.out.println("Tienes una MasterCard");
            pagoRealizado=true;
        }
        else{
            System.out.println("No se ha detectado el tipo de tu tarjeta de crédito");
        }
        return pagoRealizado;
    }

    public static void menuReservarHabitacion(){
        System.out.println("***********  RESERVAR HABITACIÓN  ***********");
        Scanner sc = new Scanner(System.in);
        int personasReserva=0,opcionHabitaciones=0;
        String fechaEntrada, fechaSalida;

        ArrayList<Habitacion> habitaciones = new ArrayList<>();
        ArrayList<Habitacion> habitacionesRepetidas = new ArrayList<>();
        HashMap<Integer, String> opcionesHabitacion = new HashMap<>();
        //nOpciones determina el número de habitaciones que se muestran,
        //para saber si el usuario puede seleccionar alguna
        boolean seguirBuscando = true;
        int nOpciones = 0;


        do {
            System.out.println("¿Para cuántas personas se hace la reserva?");
            try{
                personasReserva = sc.nextInt();
            }catch(InputMismatchException e) {
                System.out.println("Opción inválida");
            }
        } while (personasReserva<=0);

       do {
            System.out.println("Introduzca la fecha de entrada(dd/mm/aaaa): ");
            fechaEntrada = sc.nextLine();
            System.out.println("Introduzca la fecha de salida(dd/mm/aaaa): ");
            fechaSalida = sc.nextLine();
            try{
                Validaciones.fecha(fechaEntrada, true);
                Validaciones.fecha(fechaSalida, true);
            }catch(Exception e){
                System.out.println(e.getMessage());
                continue;
            }
            break;
        } while (true);

        do{
            nOpciones=0;
            for (Habitacion h : habitaciones) {
                try{
                    if (h.getMax_personas() == personasReserva && h.comprobarDisponibilidadHabitacion(h.formatearFecha(fechaEntrada), h.formatearFecha(fechaSalida))) {
                        String precios = "";
                        System.out.println("* Opcion " + (++opcionHabitaciones));
                        System.out.println("Habitación " + h.getNombre() + " para " + h.getMax_personas());
                        nOpciones++;
                        precios += h.getPrecio();
                        System.out.println("Precio final: " + precios);
                        opcionesHabitacion.put(opcionHabitaciones, "" + h.getId());
                    }
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }
            }
            for (Habitacion h : habitaciones) {
                String precios = "";
                habitacionesRepetidas.add(h);
                for (Habitacion h_ : habitaciones) {
                    boolean habitacionRepetida = false;
                    for (Habitacion hr : habitacionesRepetidas) {
                        if (h_.equals(hr)) habitacionRepetida = true;
                    }
                    if (!habitacionRepetida) {
                        if (h.getMax_personas() + h_.getMax_personas() == personasReserva) {
                            System.out.println("* Opcion " + (++opcionHabitaciones));
                            System.out.println("Habitación " + h.getNombre() + " para " + h.getMax_personas());
                            System.out.println("Habitación " + h_.getNombre() + " para " + h_.getMax_personas());
                            nOpciones++;
                            precios += h.getPrecio() + "+" + h_.getPrecio();
                            System.out.println("Precio final: " + precios);
                            opcionesHabitacion.put(opcionHabitaciones, "" + h.getId() + "," + h_.getId());
                        }
                    }
                }
            }
            if(nOpciones==0){
                System.out.println("No se han encontrado habitaciones disponibles para esos criterios");
                System.out.println("¿Desea seguir intentando?");

                seguirBuscando = sc.nextLine().equals("S") || sc.nextLine().equals("s");

            }
        }while(nOpciones==0 && seguirBuscando);
        boolean habitacionCorrecta = false;
        int opcionHabitacion;
        if(nOpciones>0){
            do {
                do {
                    System.out.println("¿Qué opción desea?");
                    try {
                        opcionHabitacion = sc.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Opción inválida");
                        continue;
                    }
                    break;
                } while (true);
                for (int opcion_ : opcionesHabitacion.keySet()) {
                    if (opcion_ == opcionHabitacion) {
                        System.out.println("Habitaciones a la espera del pago...");
                        habitacionCorrecta = true;
                    } else {
                        habitacionCorrecta = false;
                    }
                }
                opcionHabitacion = habitacionCorrecta ? opcionHabitacion : -1;
            }while(opcionHabitacion==-1);
        }
    }

    public static boolean pagoBizum(){
        Scanner sc = new Scanner(System.in);
        boolean pagoRealizado = false;
        String bizum = "";
        System.out.println("Bizum");
        do{
            System.out.println("Introduce tu número de teléfono");
            bizum = sc.nextLine();
            try{
                Validaciones.telefono(bizum);
            }catch(Exception e){
                System.out.println("Número inválido");
                continue;
            }
            break;
        }while(true);
        return pagoRealizado;
    }

    public static void menuFAQS(){
        ArrayList<String> preguntasFrecuentes = new ArrayList<>();
        ArrayList<String> respuestas = new ArrayList<>();
        String respuesta;
        Scanner sc = new Scanner(System.in);
        preguntasFrecuentes.add("¿Cómo puedo llamar fuera del hotel con el teléfono de la habitación?");
        preguntasFrecuentes.add("¿Es gratis la comida de la nevera de la habitación?");
        respuestas.add("Marcando #0, y seguido de eso, el número");
        respuestas.add("No, los precios de los productos están en un folleto de la habitación");
        System.out.println("***** PREGUNTAS FRECUENTES *****");
        System.out.println("0. Salir");
        for(int i=0; i<preguntasFrecuentes.size(); i++){
            System.out.println((i+1)+" "+preguntasFrecuentes.get(i));
        }
        System.out.println("Selecciona una pregunta");
        respuesta = sc.nextLine();
        if(Validaciones.esUnNumero(respuesta, false) && !respuesta.equals("0")){
            System.out.println(respuestas.get(Integer.parseInt(respuesta)-1));
        }
        else if(respuesta.equals("0")) System.out.println("Saliendo");
    }

    public static void imprimirFactura(Reservas reserva, Cliente cliente, ArrayList<Habitacion> habitaciones){
        //TODO poner en el main que cuando el pago haya sido efectuado, se agregen a la habitacion las fechas ocupadas
        cliente.infoBasica();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        System.out.println("Fecha de facturación: "+dtf.format(now));
        double precioTotalReserva = 0;
        for(int x : reserva.getId_habitacion()){
            for(Habitacion h : habitaciones){
                if(h.getId()==x){
                    System.out.println("Habitacion "+h.getNombre()+" para "+h.getMax_personas());
                    precioTotalReserva+=h.getPrecio();
                }
            }
        }
        System.out.println("Precio total: "+(precioTotalReserva+precioTotalReserva*IVA));
    }
}
