package Models;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * @author Antoniobox
 * @version 1.0
 * @since 18/02/2023
 */
public class Habitacion {
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
	public static ArrayList<Integer> getIdsListado(String ids){
		ArrayList<Integer> aIds = new ArrayList<>();
		int coma = ids.indexOf(',');
		aIds.add(Integer.parseInt(ids.substring(0, coma)));
		aIds.add(Integer.parseInt(ids.substring(coma+1, ids.length())));
		return aIds;
	}
	/**
	 * Genera 5 habitaciones por defecto y te las devuelve en un ArrayList
	 * @return habitaciones base
	 */
	public static ArrayList<Habitacion> generarHabitacionesBase(){
		ArrayList<Habitacion> habitaciones = new ArrayList<>();
		habitaciones.add(new Habitacion((int)(Math.random()*1000+1), "A1", "habitacion con vistas a la ciudad", 4, 8, 80.50));
		habitaciones.add(new Habitacion((int)(Math.random()*1000+1), "A2", "habitacion con vistas a la piscina", 2, 3, 40.75));
		habitaciones.add(new Habitacion((int)(Math.random()*1000+1), "A3", "habitacion con vistas al campo", 1, 1, 70.00));
		habitaciones.add(new Habitacion((int)(Math.random()*1000+1), "A4", "habitacion con vistas a un descampado", 2, 4, 75.00));
		habitaciones.add(new Habitacion((int)(Math.random()*1000+1), "A5", "habitacion con vistas a la montaña", 3, 5, 60.30));
		return habitaciones;
	}

	/**
	 * Comprueba si una habitación está disponible en cierta fecha
	 * @param fechaEntrada
	 * @param fechaSalida
	 * @return devuelve tru si la habitación está disponible con base en las fechas que se indican
	 */
	public boolean comprobarDisponibilidadHabitacion(LocalDate fechaEntrada, LocalDate fechaSalida){
		if(fechaEntrada.isAfter(LocalDate.now())){
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
}
