package Controllers;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import Exceptions.EmptyArrayListException;
import Interfaces.CRUD;
import Exceptions.ClientAddedBeforeException;
import Models.Cliente;
import Models.Habitacion;

public class GestorClientes implements CRUD {
	public static final String DB_CLIENTES="Data/clientes.dat";
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
	 * Muestra todos los clientes del listado (la información básica)
	 */
	public void mostrarClientes(){
		for(Cliente cliente : listadoClientes){
			cliente.infoBasica();
		}
	}

	/**
	 * Comprueba en base al email y al codigo secreto de un cliente si existe en el listado.
	 * @return cliente encontrado en caso de que coincidan en un registro, y devuelve null en caso de que no encuentre alguno
	 */
	public Cliente comprobarSiClienteExiste(String email, String codigo){
		for(Cliente c : listadoClientes){
			if(c.equals(email, codigo)){
				return c;
			}
		}
		return null;
	}

	/**
	 * Busca un cliente en el listado de los clientes
	 * @param cliente_a_buscar
	 * @return cliente encontrado en el listado
	 */
	public boolean buscarCliente(Cliente cliente_a_buscar){
		for(Cliente cliente : listadoClientes){
			if(cliente_a_buscar.equals(cliente)) return true;
		}
		return false;
	}

	/**
	 * Comprueba que no existe el cliente en el listado de clientes, y una vez confirmado, lo añade
	 * @param cliente cliente que se quiere agregar al listado
	 * @throws ClientAddedBeforeException El cliente ya existia en el listado
	 */
	public void agregarCliente(Cliente cliente) throws ClientAddedBeforeException{
		for(Cliente c : listadoClientes){
			if(c.equals(cliente)){
				throw new ClientAddedBeforeException("El cliente ya se encuentra en el listado");
			}
		}
		listadoClientes.add(cliente);
	}

	@Override
	public void cargarBaseDeDatos() throws IOException {
		FileReader fr = new FileReader(DB_CLIENTES);
		BufferedReader br = new BufferedReader(fr);

		String linea;
		String[] registro;

		while((linea=br.readLine())!=null){
			registro = linea.split(";");
			String nombre, apellidos, email, telefono, dni, fechaNacimiento, codigoAcceso;
			nombre = registro[0];
			apellidos = registro[1];
			email = registro[2];
			telefono = registro[3];
			dni = registro[4];
			fechaNacimiento = registro[5];
			codigoAcceso = registro[6];
			listadoClientes.add(new Cliente(nombre, apellidos, email, telefono, dni, fechaNacimiento, codigoAcceso));
		}
	}

	@Override
	public void guardarRegistros() throws IOException, EmptyArrayListException {
		FileWriter fw = new FileWriter(DB_CLIENTES, false);
		if(listadoClientes.size() > 0){
			for(Cliente c : listadoClientes){
				fw.write(c.formatearObjeto());
			}
		} else{
			throw new EmptyArrayListException("No se puede guardar el listado de clientes (no existen clientes)");
		}
		fw.close();
	}
}
