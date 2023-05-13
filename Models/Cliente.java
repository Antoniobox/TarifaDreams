package Models;
/**
 * @author Antoniobox
 * @version 1.0
 * @since 18/02/2023
 */
public class Cliente extends Usuario{
	private String nombre;
	private String apellidos;
	private String email;
	private String telefono;
	private String dni;
	private String fechaNacimiento;
	private String codigoAcceso;

	public Cliente() {
	}

	public Cliente(String nombre, String apellidos, String email, String telefono, String dni, String fechaNacimiento, String codigoAcceso) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.telefono = telefono;
		this.dni = dni;
		this.fechaNacimiento = fechaNacimiento;
		this.codigoAcceso = codigoAcceso;
	}

	public Cliente(String nombre, String apellidos, String dni) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getCodigoAcceso() {
		return codigoAcceso;
	}

	public void setCodigoAcceso(String codigoAcceso) {
		this.codigoAcceso = codigoAcceso;
	}

	@Override
	public String toString() {
		return "Cliente{" +
				"nombre='" + nombre + '\'' +
				", apellidos='" + apellidos + '\'' +
				", email='" + email + '\'' +
				", telefono='" + telefono + '\'' +
				", dni='" + dni + '\'' +
				", fechaNacimiento='" + fechaNacimiento + '\'' +
				", codigoAcceso='" + codigoAcceso + '\'' +
				'}';
	}

	/**
	 * Muestra la información básica del cliente
	 */
	public void infoBasica(){
		System.out.println("Nombre: "+nombre);
		System.out.println("Apellidos: "+apellidos);
		System.out.println("Email: "+email);
		System.out.println("DNI: "+dni);
	}

	/**
	 * Compara dos clientes en base a los atributos codigoAcceso, Email y dni
	 * @param obj Cliente a comparar
	 * @return Clientes idénticos
	 */
	@Override
	public boolean equals(Object obj){
		if(obj.getClass() != this.getClass() || obj == null){
			return false;
		}
		Cliente cliente = (Cliente) obj;
		return cliente.getEmail().equals(this.getEmail()) && cliente.getCodigoAcceso().equals(this.getCodigoAcceso()) && cliente.getDni().equals(this.getDni());
	}

	/**
	 * Comprueba que mediante los parametros que se requiren para iniciar sesión, el cliente coincide
	 * @param email
	 * @param codigo
	 * @return true cliente con campos coincidentes, false si no coincide alguno de los campos
	 */
	public boolean equals(String email, String codigo){
		return email.equals(this.getEmail()) && codigo.equals(this.getCodigoAcceso());
	}
}
