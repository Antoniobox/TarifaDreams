package Controllers;

import Exceptions.EmptyArrayListException;
import Interfaces.CRUD;
import Models.Cliente;
import Models.Reservas;

import java.io.*;
import java.util.ArrayList;


/**
 * - Ver listado de reservas.
 * - Cancelar reserva (Esto mandaría un mensaje al usuario para indicarle que su reserva ha sido cancelada, que aparezca un mensaje indicando esta funcionalidad aunque no se implemente).
 * @author Antoniobox
 * @since 2023-06-01
 * @version 1.0
 */
public class GestorReservas implements CRUD {
    private ArrayList<Reservas> reservas = new ArrayList<>();
    public static final String RUTA_DB = "Data/reservas.dat";

    public void agregarReserva(Reservas reserva){
        reservas.add(reserva);
    }

    public ArrayList<Reservas> getReservas() {
        return reservas;
    }

    public void setReservas(ArrayList<Reservas> reservas) {
        this.reservas = reservas;
    }

    public void mostrarReservas() throws EmptyArrayListException{
        if(reservas.size()<1){
            throw new EmptyArrayListException("No existen reservas en la lista");
        }
        for(Reservas reserva : reservas){
            reserva.mostrarDatos();
        }
    }

    public void cancelarReserva(int cod, Cliente cliente) throws EmptyArrayListException {
        if(reservas.size()<1){
            throw new EmptyArrayListException("No existen reservas en la lista");
        }
        for (int i = 0; i < reservas.size(); i++) {
            if(reservas.get(i).getCod()==cod){
                cliente.setNotificaciones("Se ha eliminado la reserva");
                reservas.remove(i);
            }
        }
    }

    /**
     * Guarda el arraylist de reservas en el fichero
     */
    @Override
    public void cargarBaseDeDatos(){
        try {
            FileOutputStream fileOut = new FileOutputStream(RUTA_DB);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(reservas);
            out.close();
            fileOut.close();
            System.out.println("Los registros de reservas se han guardado exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar los registros de reservas: " + e.getMessage());
        }
    }

    /**
     * Guarda los registros en la base de datos
     */
    @Override
    public void guardarRegistros() {
        try {
            FileOutputStream fileOut = new FileOutputStream(RUTA_DB);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(reservas);
            out.close();
            fileOut.close();
            System.out.println("Los registros de reservas se han guardado exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar los registros de reservas: " + e.getMessage());
        }
    }
}
