package Controllers;

import Models.Habitacion;

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


}
