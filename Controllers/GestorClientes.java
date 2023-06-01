package Controllers;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import Exceptions.EmptyArrayListException;
import Exceptions.InvalidNumberOfFieldsException;
import Exceptions.OptionOutOfRangeException;
import Interfaces.CRUD;
import Exceptions.ClientAddedBeforeException;
import Models.Cliente;
import Models.Habitacion;
import Utils.Validaciones;

import javax.swing.text.html.Option;

public class GestorClientes implements CRUD {
	public static final String DB_CLIENTES = "Data/clientes.dat";
	private ArrayList<Cliente> listadoClientes = new ArrayList<>();

	public GestorClientes() {
	}

	public GestorClientes(ArrayList<Cliente> listadoClientes) {
		this.listadoClientes = listadoClientes;
	}

	public ArrayList<Cliente> getListadoClientes() {
		return listadoClientes;
	}

	public void setListadoClientes(ArrayList<Cliente> listadoClientes) {
		this.listadoClientes = listadoClientes;
	}


	/**
	 * Muestra todos los clientes del listado
	 */
	public void mostrarClientes() throws EmptyArrayListException {
		if (listadoClientes.size() < 1) {
			throw new EmptyArrayListException("No existen clientes");
		}
		for (int i = 0; i < listadoClientes.size(); i++) {
			Cliente cliente = listadoClientes.get(i);
			System.out.println(
					i + ". DNI:" + cliente.getDni() + "\n" +
							". Nombre:" + cliente.getNombre() + "\n" +
							". Apellidos:" + cliente.getDni() + "\n" +
							". Email:" + cliente.getEmail() + "\n" +
							". Teléfono:" + cliente.getTelefono() + "\n" +
							". Fecha de nacimiento:" + cliente.getFechaNacimiento() + "\n" +
							"Nombre de usuario:" + cliente.getNombreUsuario() + "\n"
			);
		}
	}

	/**
	 * Muestra un cliente
	 */
	public void mostrarClientes(Cliente cliente) throws EmptyArrayListException {
		if (listadoClientes.size() < 1) {
			throw new EmptyArrayListException("No existen clientes");
		}
		System.out.println(
				"1. Nombre:" + cliente.getNombre() + "\n" +
						"2. Apellidos:" + cliente.getDni() + "\n" +
						"3. Email:" + cliente.getEmail() + "\n" +
						"4. Teléfono:" + cliente.getTelefono() + "\n" +
						"5. Fecha de nacimiento:" + cliente.getFechaNacimiento() + "\n" +
						"6. Nombre de usuario:" + cliente.getNombreUsuario() + "\n"
		);
	}

	/**
	 * Comprueba en base al email y al codigo secreto de un cliente si existe en el listado.
	 *
	 * @return cliente encontrado en caso de que coincidan en un registro, y devuelve null en caso de que no encuentre alguno
	 */
	public Cliente comprobarSiClienteExiste(String email, String codigo) {
		for (Cliente c : listadoClientes) {
			if (c.equals(email, codigo)) {
				return c;
			}
		}
		return null;
	}

	/**
	 * Busca un cliente en el listado de los clientes
	 *
	 * @param cliente_a_buscar
	 * @return cliente encontrado en el listado
	 */
	public boolean buscarCliente(Cliente cliente_a_buscar) {
		for (Cliente cliente : listadoClientes) {
			if (cliente_a_buscar.equals(cliente)) return true;
		}
		return false;
	}

	public boolean buscarCliente(String email, String dni) {
		for (Cliente cliente : listadoClientes) {
			if (email.equals(cliente.getEmail()) || dni.equals(cliente.getDni())) return true;
		}
		return false;
	}

	/**
	 * Comprueba que no existe el cliente en el listado de clientes, y una vez confirmado, lo añade
	 *
	 * @param cliente cliente que se quiere agregar al listado
	 * @throws ClientAddedBeforeException El cliente ya existia en el listado
	 */
	public void agregarCliente(Cliente cliente) throws ClientAddedBeforeException {
		for (Cliente c : listadoClientes) {
			if (c.equals(cliente)) {
				throw new ClientAddedBeforeException("El cliente ya se encuentra en el listado");
			}
		}
		listadoClientes.add(cliente);
	}

	@Override
	public void cargarBaseDeDatos() throws IOException, InvalidNumberOfFieldsException {
		FileReader fr = new FileReader(DB_CLIENTES);
		BufferedReader br = new BufferedReader(fr);

		String linea;
		String[] registro;

		while ((linea = br.readLine()) != null) {
			registro = linea.split(";");
			String nombre, apellidos, email, telefono, dni, fechaNacimiento, nombreUsuario, codigoAcceso;
			if (registro.length == 8) {
				nombre = registro[0];
				apellidos = registro[1];
				email = registro[2];
				telefono = registro[3];
				dni = registro[4];
				fechaNacimiento = registro[5];
				nombreUsuario = registro[6];
				codigoAcceso = registro[7];
				listadoClientes.add(new Cliente(nombre, apellidos, email, telefono, dni, fechaNacimiento, nombreUsuario, codigoAcceso));
			} else {
				throw new InvalidNumberOfFieldsException("Se ha detectado un error al intentar cargar el cliente");
			}
		}
	}

	@Override
	public void guardarRegistros() throws IOException, EmptyArrayListException {
		FileWriter fw = new FileWriter(DB_CLIENTES, false);
		if (listadoClientes.size() > 0) {
			for (Cliente c : listadoClientes) {
				fw.write(c.formatearObjeto());
			}
		} else {
			throw new EmptyArrayListException("No se puede guardar el listado de clientes (no existen clientes)");
		}
		fw.close();
	}

	public void consultarCliente() {
		try {
			mostrarClientes();
		} catch (EmptyArrayListException e) {
			System.out.println("No existen clientes en el listado, inserte clientes si desea visualizarlos");
		}
	}

	/**
	 * Permite a un administrador actualizar los campos de un cliente determinado
	 * @throws EmptyArrayListException
	 * @throws OptionOutOfRangeException
	 */
	public void actualizarCliente() throws EmptyArrayListException, OptionOutOfRangeException {
		Scanner sc = new Scanner(System.in);
		int opcion = -1;
		do {
			mostrarClientes();
			System.out.println("Seleccione un cliente: ");
			try {
				opcion = sc.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Introduzca una opción numérica");
				opcion = -1;
			}
			if (opcion >= listadoClientes.size() || opcion < 0) {
				throw new OptionOutOfRangeException("No se permite modificar clientes inexistentes (que me llamen loco)");
			}
		} while (opcion >= listadoClientes.size() || opcion < 0);

		//Si el administrador quiere modificar varios valores, continuarEditando se volverá true
		boolean continuarEditando = false;

		do {
			Cliente cliente = listadoClientes.get(opcion);
			mostrarClientes(cliente);
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
					System.out.println("Introduce un nuevo nombre para el usuario");
					campo = sc.nextLine();
					try {
						Validaciones.nombreYApellidos(campo, false);
						cliente.setNombre(campo);
					} catch (Exception e) {
						System.out.println(e.getMessage());
						System.out.println("No se puede modificar el usuario");
					}
				case 2:
					System.out.println("Introduce los nuevos apellidos del cliente");
					campo = sc.nextLine();
					try {
						Validaciones.nombreYApellidos(campo, true);
						cliente.setApellidos(campo);
					} catch (Exception e) {
						System.out.println(e.getMessage());
						System.out.println("No se puede modificar el usuario");
					}
					break;
				case 3:
					System.out.println("Introduce el nuevo email del cliente");
					campo = sc.nextLine();
					try {
						Validaciones.email(campo);
						cliente.setEmail(campo);
					} catch (Exception e) {
						System.out.println(e.getMessage());
						System.out.println("No se puede modificar el usuario");
					}
					break;
				case 4:
					System.out.println("Introduce un nuevo telefono para el usuario");
					campo = sc.nextLine();
					try {
						Validaciones.telefono(campo);
						cliente.setTelefono(campo);
					} catch (Exception e) {
						System.out.println(e.getMessage());
						System.out.println("No se puede modificar el usuario");
					}
				case 5:
					System.out.println("Introduce la nueva fecha de nacimiento del cliente");
					campo = sc.nextLine();
					try {
						Validaciones.fecha(campo, false);
						cliente.setFechaNacimiento(campo);
					} catch (Exception e) {
						System.out.println(e.getMessage());
						System.out.println("No se puede modificar el usuario");
					}
					break;
				case 6:
					System.out.println("Introduce los nuevos apellidos del cliente");
					campo = sc.nextLine();
					try {
						Validaciones.nombreUsuario(campo);
						cliente.setNombreUsuario(campo);
					} catch (Exception e) {
						System.out.println(e.getMessage());
						System.out.println("No se puede modificar el usuario");
					}
					break;
				default:
					System.out.println("Opción inválida");
			}
			System.out.println("¿Desea seguir editando el cliente(S/N)?: ");
			String opcionContinuarEditando = sc.nextLine();
			continuarEditando = opcionContinuarEditando.equals("S") || opcionContinuarEditando.equals("s");
		} while (opcion < 1 || opcion > 7 || continuarEditando);
	}

	public void eliminarCliente() throws OptionOutOfRangeException{
		int opcion = -1;
		Scanner sc = new Scanner(System.in);
		do{
			try{
				mostrarClientes();

				System.out.println("Seleccione un cliente: ");
				try {
					opcion = sc.nextInt();
				} catch (InputMismatchException e) {
					System.out.println("Introduzca una opción numérica");
					opcion = -1;
				}
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
			if (opcion >= listadoClientes.size() || opcion < 0) {
				throw new OptionOutOfRangeException("No se permite modificar clientes inexistentes (que me llamen loco)");
			}
		}while(opcion < 0 || opcion >= listadoClientes.size());
		listadoClientes.remove(opcion);
		System.out.println("Cliente eliminado exitosamente");
	}
}
