package Controllers;

import Exceptions.EmptyArrayListException;
import Exceptions.OptionOutOfRangeException;
import Interfaces.CRUD;
import Models.Cliente;
import Models.Habitacion;
import Utils.Validaciones;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Clase GestorHabitaciones
 * Clase para poder gestionar múltiples habitaciones
 *
 * @author Antoniobox
 * @since 2023-05-08
 * @version 1.0
 */
public class GestorHabitaciones implements CRUD {
    ArrayList<Habitacion> habitaciones = new ArrayList<>();
    public static final String DB_HABITACIONES = "Data/habitaciones";

    public GestorHabitaciones(){}

    public GestorHabitaciones(ArrayList<Habitacion> habitaciones){
        this.habitaciones = habitaciones;
    }

    /**
     * Constructor para cargar la base de datos en el arraylist de habitaciones que se le pasa como parametro
     * @param habitaciones
     * @param cargarBD
     * @throws IOException
     */
    public GestorHabitaciones(ArrayList<Habitacion> habitaciones, boolean cargarBD) throws IOException{
        this.habitaciones = habitaciones;
        cargarBaseDeDatos();
    }
    public ArrayList<Habitacion> getHabitaciones() {
        return habitaciones;
    }
    public void setHabitaciones(ArrayList<Habitacion> habitaciones) {
        this.habitaciones = habitaciones;
    }


    /**
     * Agrega una habitación a la lista de habitaciones
     * @param habitacion
     */
    public void agregarHabitacion(Habitacion habitacion){
        habitaciones.add(habitacion);
    }

    /**
     * Carga el fichero "habitaciones.dat", donde se encuentran los registros de las habitaciones, a la lista de habitaciones
     * @throws IOException
     */
    @Override
    public void cargarBaseDeDatos() throws IOException {
        FileReader fr = new FileReader(DB_HABITACIONES);
        BufferedReader br = new BufferedReader(fr);

        String linea;
        String[] registro;

        while((linea=br.readLine())!=null){
            registro = linea.split(";");
            int id, num_camas, max_personas;
            String nombre, descripcion, fechasOcupadas;
            double precio;
            id = Integer.parseInt(registro[0]);
            nombre = registro[1];
            descripcion = registro[2];
            num_camas = Integer.parseInt(registro[3]);
            max_personas = Integer.parseInt(registro[4]);
            precio = Double.parseDouble(registro[5]);
            habitaciones.add(new Habitacion(id, nombre, descripcion, num_camas, max_personas, precio));
        }
    }

    /**
     * Almacena el contenido del arraylist de habitaciones en la BD
     * @throws IOException
     * @throws EmptyArrayListException
     */
    @Override
    public void guardarRegistros() throws IOException, EmptyArrayListException {
        FileWriter fw = new FileWriter(DB_HABITACIONES, false);
        if(habitaciones.size() > 0){
            for(Habitacion h : habitaciones){
                fw.write(h.formatearObjeto());
            }
        } else{
            throw new EmptyArrayListException("No se puede guardar el listado de habitaciones (no existen habitaciones)");
        }
        fw.close();
    }

    public void mostrarHabitaciones() throws EmptyArrayListException {
        if(habitaciones.size()<1){
            throw new EmptyArrayListException("No existen habitaciones en el listado");
        }
        for(Habitacion h : habitaciones){
            System.out.println("Nombre: "+h.getNombre());
            System.out.println("Descripción: "+h.getDescripcion());
            System.out.println("Número de camas: "+h.getNum_camas());
            System.out.println("Máximo de personas: "+h.getMax_personas());
        }
    }

    public void actualizarHabitacion() throws EmptyArrayListException, OptionOutOfRangeException {
        Scanner sc = new Scanner(System.in);
        int opcion = -1;
        do {
            mostrarHabitaciones();
            System.out.println("Seleccione un cliente: ");
            try {
                opcion = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Introduzca una opción numérica");
                opcion = -1;
            }
            if (opcion >= habitaciones.size() || opcion < 0) {
                throw new OptionOutOfRangeException("No se permite modificar habitaciones inexistentes (que me llamen loco)");
            }
        } while (opcion >= habitaciones.size() || opcion < 0);

        //Si el administrador quiere modificar varios valores, continuarEditando se volverá true
        boolean continuarEditando = false;

        do {
            Habitacion habitacion = habitaciones.get(opcion);
            habitacion.mostrarHabitacion();
            System.out.println("Seleccione un campo a modificar(formato numérico por dios, que me estoy cansando de los try catch)");
            try {
                opcion = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Menos mal que te he avisado de que no lo hagas");
                opcion = -1;
            }
            String campo;
            switch (opcion) {
                case 1:
                    System.out.println("Introduce un nuevo nombre para la habitacion");
                    campo = sc.nextLine();
                    try {
                        if(campo.length()<1){
                            System.out.println("No se puede dejar el nombre vacío");
                        }else{
                            habitacion.setNombre(campo);
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        System.out.println("No se puede modificar la habitación");
                    }
                case 2:
                    System.out.println("Introduce la nueva descripción de la habitación");
                    campo = sc.nextLine();
                    habitacion.setDescripcion(campo);
                    case 3:
                        System.out.println("Introduce el número de camas de la habitación");

                    try {
                        int nCamas = sc.nextInt();
                        habitacion.setNum_camas(nCamas);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        System.out.println("No se puede modificar el número de camas");
                    }
                    break;
                case 4:
                    System.out.println("Establece el máximo número de personas");
                    int max_personas = 0;
                    try {
                        max_personas = sc.nextInt();
                        habitacion.setMax_personas(max_personas);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        System.out.println("No se puede modificar el máximo de personas");
                    }
                case 5:
                    System.out.println("Establece el precio de la habitación");
                    int precio;
                    try {
                        precio = sc.nextInt();
                        habitacion.setPrecio(precio);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        System.out.println("No se puede modificar el precio");
                    }
                    break;
                default:
                    System.out.println("Opción inválida");
            }
            System.out.println("¿Desea seguir editando la habitacion(S/N)?: ");
            String opcionContinuarEditando = sc.nextLine();
            continuarEditando = opcionContinuarEditando.equals("S") || opcionContinuarEditando.equals("s");
        } while (opcion < 1 || opcion > 5 || continuarEditando);
    }
}
