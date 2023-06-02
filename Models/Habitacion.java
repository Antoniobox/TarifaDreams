package Models;
import Controllers.GestorHabitaciones;
import Exceptions.InvalidDateFormatException;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * @author Antoniobox
 * @version 1.0
 * @since 18/02/2023
 */
public class Habitacion implements Serializable {
	private int id;
	private String nombre;
	private String descripcion;
	private int num_camas;
	private int max_personas;
	private ArrayList<LocalDate[]> fechasOcupadas;


	private double precio;

	public Habitacion() {
	}

	public Habitacion(int id, String nombre, String descripcion, int num_camas, int max_personas, double precio) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.num_camas = num_camas;
		this.max_personas = max_personas;
		this.precio = precio;
		fechasOcupadas = new ArrayList<>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getNum_camas() {
		return num_camas;
	}

	public void setNum_camas(int num_camas) {
		this.num_camas = num_camas;
	}

	public int getMax_personas() {
		return max_personas;
	}

	public void setMax_personas(int max_personas) {
		this.max_personas = max_personas;
	}


	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public ArrayList<LocalDate[]> getFechasOcupadas() {
		return fechasOcupadas;
	}

	public void setFechasOcupadas(LocalDate fechaEntrada, LocalDate fechaSalida) {
		LocalDate[] rangoFechas = {fechaEntrada, fechaSalida};
		fechasOcupadas.add(rangoFechas);
	}

	/**
	 * Comprueba si una habitacion existe en el listado de habitaciones
	 * @param id
	 * @param gh
	 * @return false si habitacion esta en el listado, true si habitacion no esta en el listado
	 */
	public static boolean comprobarHabitacion(int id, GestorHabitaciones gh){
		for(Habitacion h : gh.getHabitaciones()){
			if(h.getId()==id){
				return false;
			}
		}
		return true;
	}

	public void mostrarHabitacion(){
		System.out.println("1. Nombre: "+nombre);
		System.out.println("2. Descripcion: "+descripcion);
		System.out.println("3. Número de camas: "+num_camas);
		System.out.println("4. Número máximo de personas"+max_personas);
		System.out.println("5. Precio: "+precio);
	}

	@Override
	public String toString() {
		return "Habitacion{" +
				"id=" + id +
				", nombre='" + nombre + '\'' +
				", descripcion='" + descripcion + '\'' +
				", num_camas=" + num_camas +
				", max_personas=" + max_personas +
				", fechasOcupadas='" + fechasOcupadas + '\'' +
				", precio=" + precio +
				'}';
	}

	/**
	 * Devuelve un String que contiene todos los atributos del objeto separados por ;
	 * @return String con los atributos separados por ;
	 */
	public String formatearObjeto(){
		return id + ";"+ nombre + ";" + descripcion + ";" + num_camas + ";" + max_personas + ";" + precio + "\n";
	}

	/**
	 * Devuelve un ArrayList con los ID's que contiene la opción que ha elegido el usuario
	 * @param ids String con los ids de las habitaciones usando como separador el ;
	 * @return ArrayList con los ID's
	 */
	public static String[] getIdsListado(String ids){
		String[] aids = ids.split(";");
		return aids;
	}

	/**
	 * Comprueba si una habitación está disponible en cierta fecha
	 * @param fechaEntrada
	 * @param fechaSalida
	 * @return devuelve tru si la habitación está disponible con base en las fechas que se indican
	 */
	public boolean comprobarDisponibilidadHabitacion(LocalDate fechaEntrada, LocalDate fechaSalida){
		if(fechaEntrada.isBefore(LocalDate.now())){
			return false;
		}
		for(LocalDate[] fechas : fechasOcupadas){
			LocalDate fechaInicio = fechas[0];
			LocalDate fechaFin = fechas[1];
			if (!fechaEntrada.isAfter(fechaInicio) && !fechaSalida.isBefore(fechaFin)) {
				return false;
			}
		}
		return true;
	}

	public static LocalDate formatearFecha(String fecha) throws InvalidDateFormatException {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate fechaFinal;
		try {
			fechaFinal = LocalDate.parse(fecha, formatter);
		} catch (DateTimeParseException e) {
			throw new InvalidDateFormatException("Fecha inválida");
		}
		return fechaFinal;
	}
}
