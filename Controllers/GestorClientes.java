package Controllers;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import Exceptions.EmptyArrayListException;
import Interfaces.CRUD;
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
	 * Añade un cliente al listado de clientes del gestor
	 * @param cliente
	 */
	public void agregarCliente(Cliente cliente){
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
