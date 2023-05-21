package Controllers;
import java.util.ArrayList;

import Exceptions.ClientAddedBeforeException;
import Models.Cliente;

public class GestorClientes {
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
}
