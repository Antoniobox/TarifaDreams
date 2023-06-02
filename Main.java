import Controllers.GestorHabitaciones;
import Controllers.GestorReservas;
import Exceptions.OptionOutOfRangeException;
import Views.AdminView;
import Views.UserView;

import Controllers.GestorClientes;
import Models.*;

public class Main {

	public static void main(String[] args){
		GestorClientes gc = new GestorClientes();
		GestorHabitaciones gh = new GestorHabitaciones();
		GestorReservas gr = new GestorReservas();
		gc.cargarBaseDeDatos();
		gh.cargarBaseDeDatos();
		gr.cargarBaseDeDatos();
		try{
			gc.cargarBaseDeDatos();
		}catch(Exception e){
			System.out.println(e.getMessage());
			System.exit(2);
		}
		try{
			gc.cargarBaseDeDatos();
		}catch(Exception e){
			System.out.println("Se ha producido un error crítico, vuelva a intentarlo más tarde. Apruébame la práctica ya que estamos");
		}
		int opcionMenuInicio;
		do{
			opcionMenuInicio = UserView.menuInicio();
			if(opcionMenuInicio==1){
				try{
					Cliente cliente = UserView.menuRegistroCliente(gc);
					gc.agregarCliente(cliente);
					gc.guardarRegistros();
				}catch(Exception e){
					System.out.println("Ha habido un problema al insertar al cliente. Si sigue teniendo problemas, consulte con el administrador y apruébele");
				}
			}else if(opcionMenuInicio==2){
				Cliente c = UserView.menuInicioSesion(gc);
				if(c!=null && !c.isRol()){
					int opcionMenuCliente=-1;
					do{
						try{
							opcionMenuCliente = UserView.menuCliente();
							if(opcionMenuCliente==1){
								Reservas reserva = UserView.menuReservarHabitacion(c.getDni());
								int pago = UserView.menuSeleccionPago();
								boolean pagoRealizado = false;
								if(pago==1){
									pagoRealizado = UserView.pagoConTarjeta();
								}else if(pago==2){
									pagoRealizado = UserView.pagoBizum();
								}

								if(pagoRealizado){
									UserView.imprimirFactura(reserva, c);
								}
							}
							else if(opcionMenuCliente==2){
								UserView.menuFAQS();
							}
						}catch(OptionOutOfRangeException e){
							System.out.println("No se acepta esa opción, asi que apruébame");
						}
					}while(opcionMenuCliente!=0);
				}else if(c!=null && c.isRol()){
					int opcionAdmin = AdminView.menuAdmin();
					switch (opcionAdmin){
						case 1:
							AdminView.menuGestionClientes(gc);
							gc.guardarRegistros();
							break;
						case 2:
							AdminView.menuGestionHabitaciones(gh);
							gh.guardarRegistros();
							break;
						case 3:
							AdminView.menuGestionReservas(gr);
							gr.guardarRegistros();
						case 4:
							System.out.println("Saliendo atras...");
							break;
						case 0:
							System.out.println("Saliendo del programa. Aprueba por Dios");
							System.exit(-1);
							break;
					}
				}else{
					System.out.println("No se ha podido iniciar sesión, pero aprobar me podrías aprobar si quieres");
				}
			}else if(opcionMenuInicio==3){
				System.out.println("Un placer, apruébame la práctica");
			}
		}while(opcionMenuInicio!=0);
	}
}
