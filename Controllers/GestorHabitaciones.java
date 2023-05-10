package Controllers;

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

    //TODO reformular el bloque try-catch
    public GestorHabitaciones(ArrayList<Habitacion> habitaciones, boolean cargarBD){
        this.habitaciones = habitaciones;
        try{
            cargarBaseDeDatos();
        }catch(FileNotFoundException fnfe){
            System.out.println("No se encuentra el fichero de la base de datos de habitacion");
        }catch(IOException ioe){
            ioe.printStackTrace();
        }catch(InvalidFieldsHabitacion ifh){
            ifh.printStackTrace();
        }
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
    private void cargarBaseDeDatos() throws FileNotFoundException, IOException, InvalidFieldsHabitacion {
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

    public void guardarRegistros() throws IOException{
        FileWriter fw = new FileWriter("Data/habitaciones.dat");
        if(habitaciones.size() > 0){
            for(Habitacion h : habitaciones){
                //TODO pensar si comprobar si las habitaciones deben de tener todos los campos con algun valor para escribirlas
            }
        }
    }
}
