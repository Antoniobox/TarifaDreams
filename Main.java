import java.util.ArrayList;
import java.util.Scanner;

import Views.ClienteView;

import Controllers.GestorClientes;
import Models.*;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String opcion="";
		int hola = sc.nextInt();
		//Views.ClienteView.menuCliente();
		ArrayList<Cliente> clientes = new ArrayList<>();
		clientes.add(new Cliente("Antonio", "Box Sanchez", "antonioboxsanchez@gmail.com", "693810626", "29594264A", "07/04/2004", "PPPP78"));
		Cliente cliente = new Cliente();

		boolean usuarioLogueado=false;
		ClienteView.menuRegistroCliente().getNombre();

		System.out.println("Bienvenido a Antonio DREAMS");
		//TODO trasladar los menús a métodos en sus clases respectivas
		ClienteView.menuInicio();
		if(opcion.equals("1")){

			System.out.println("¿Deseas iniciar sesión?(S/N): ");
			opcion=sc.nextLine();
			if(opcion.equals("S")||opcion.equals("s")) opcion="2";
			else System.out.println("Finalizando...");
		}


		while(opcion.equals("2")){

			//TODO seguir implementando en el view
			ArrayList<Cliente> clientes = gc.getListadoClientes();
			for(Cliente cliente_ : clientes){
				if(cliente_.getEmail().equals(email) && cliente_.getCodigoAcceso().equals(frase)){
					cliente = cliente_;
				}
			}
			for(Cliente c : clientes){
				if(c.getEmail().equals(email) && c.getCodigoAcceso().equals(frase)){
					System.out.println("Bienvenido, "+c.getNombre());
					usuarioLogueado=true;
					opcion="";
				}
			}
			if(!usuarioLogueado){
				System.out.println("No se ha encontrado ningún usuario que coincida, ¿desea seguir intentándolo?(S/N): ");
				opcion = sc.nextLine();
				if(opcion.equals("S") || opcion.equals("s")) opcion="2";
				else{
					opcion="";
					System.out.println("Saliendo...");
				}
			}
		}
		if(usuarioLogueado) {
			String personasReserva = "", fechaEntrada = "", fechaSalida = "";
			String opcionHabitacion = "";
			do {

				opcionHabitacion = sc.nextLine();
			} while (!opcionHabitacion.equals("1") && !opcionHabitacion.equals("2") && !opcionHabitacion.equals("0"));

			if (opcionHabitacion.equals("1")) {

				boolean pagoRealizado = false;
				if(habitacionCorrecta){
					String metodoPago = "";

					if(metodoPago.equals("1")){

					}
					else if(metodoPago.equals("2")){

					}
				}
				if(pagoRealizado){
					Reservas reserva = new Reservas((int)(Math.random()*1000+1), cliente.getDni(), Habitacion.getIdsListado(opcionesHabitacion.get(Integer.parseInt(opcionHabitacion))), fechaEntrada, fechaSalida);
					cliente.infoBasica();
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
					LocalDateTime now = LocalDateTime.now();
					System.out.println("Fecha de facturación: "+dtf.format(now));
					double precioTotalReserva = 0;
					for(int x : reserva.getId_habitacion()){
						for(Habitacion h : habitaciones){
							if(h.getId()==x){
								System.out.println("Habitacion "+h.getNombre()+" para "+h.getMax_personas());
								h.setFechasOcupadas(fechaEntrada+":"+fechaSalida);
								precioTotalReserva+=h.getPrecio();
							}
						}
					}
					System.out.println("Precio total: "+(precioTotalReserva+precioTotalReserva*0.21));
				}
			}
			else if(opcionHabitacion.equals("2")){

			}
		}
	}
}
