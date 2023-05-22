package Controllers;

import Exceptions.EmptyArrayListException;
import Interfaces.CRUD;
import Models.Habitacion;

import java.io.*;
import java.util.ArrayList;

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
     * Muestra las habitaciones que contiene el atributo habitaciones
     */
    public void mostrarHabitaciones(){
        for(Habitacion habitacion : habitaciones){
            System.out.println(habitacion.toString());
        }
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
}
