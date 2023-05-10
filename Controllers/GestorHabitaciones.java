package Controllers;

import Exceptions.EmptyArrayListHabitacionesException;
import Exceptions.InvalidFieldsHabitacion;
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
public class GestorHabitaciones {
    ArrayList<Habitacion> habitaciones = new ArrayList<>();

    public GestorHabitaciones(){}

    public GestorHabitaciones(ArrayList<Habitacion> habitaciones){
        this.habitaciones = habitaciones;
    }

    /**
     * Constructor para cargar la base de datos en el arraylist de habitaciones que se le pasa como parametro
     * @param habitaciones
     * @param cargarBD
     * @throws IOException
     * @throws InvalidFieldsHabitacion
     */
    public GestorHabitaciones(ArrayList<Habitacion> habitaciones, boolean cargarBD) throws IOException, InvalidFieldsHabitacion{
        this.habitaciones = habitaciones;
        cargarBaseDeDatos();
        //TODO pensar si se debería de guardar los registros antes de cargarlos
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
     * @throws FileNotFoundException
     * @throws IOException
     * @throws InvalidFieldsHabitacion
     */
    private void cargarBaseDeDatos() throws IOException, InvalidFieldsHabitacion {
        FileReader fr = new FileReader("Data/habitaciones");
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
            try{
                num_camas = Integer.parseInt(registro[3]);
                max_personas = Integer.parseInt(registro[4]);
                precio = Double.parseDouble(registro[5]);
            }catch(NumberFormatException e){
                throw new InvalidFieldsHabitacion("Existen campos incorrectos en el registro");
            }
            habitaciones.add(new Habitacion(id, nombre, descripcion, num_camas, max_personas, precio));
        }
    }

    /**
     * Almacena el contenido del arraylist de habitaciones en la BD
     * @throws IOException
     * @throws EmptyArrayListHabitacionesException
     */
    public void guardarRegistros() throws IOException, EmptyArrayListHabitacionesException{
        FileWriter fw = new FileWriter("Data/habitaciones.dat", false);
        if(habitaciones.size() > 0){
            for(Habitacion h : habitaciones){
                fw.write(h.formatearObjeto());
            }
        } else{
            throw new EmptyArrayListHabitacionesException("No se puede guardar el listado de habitaciones (no existen habitaciones)");
        }
        fw.close();
    }
}
