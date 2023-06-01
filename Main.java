import Exceptions.ClientAddedBeforeException;
import Exceptions.InvalidUserException;
import Exceptions.OptionOutOfRangeException;
import Views.AdminView;
import Views.UserView;

import Controllers.GestorClientes;
import Models.*;

public class Main {

	public static void main(String[] args) {
		GestorClientes gc = new GestorClientes();
		try{
			gc.cargarBaseDeDatos();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}

		try{
			gc.cargarBaseDeDatos();
		}catch(Exception e){
			System.out.println("Se ha producido un error crítico, vuelva a intentarlo más tarde");
		}
		int opcionMenuInicio=-1;
		do{
			opcionMenuInicio = UserView.menuInicio();
			if(opcionMenuInicio==1){
				try{
					Cliente cliente = UserView.menuRegistroCliente(gc);
					gc.agregarCliente(cliente);
				}catch(InvalidUserException | ClientAddedBeforeException e){
					System.out.println("Ha habido un problema al insertar al cliente. Si sigue teniendo problemas, consulte con el administrador");
				}
			}else if(opcionMenuInicio==2){
				Cliente c = UserView.menuInicioSesion(gc);
				if(c!=null && !c.isRol()){
					int opcionMenuCliente=-1;
					do{
						try{
							opcionMenuCliente = UserView.menuCliente();
						}catch(OptionOutOfRangeException e){
							System.out.println("No se acepta esa opción");
						}
					}while(opcionMenuCliente!=0);
				}else if(c!=null && c.isRol()){
					int opcionAdmin = AdminView.menuAdmin();
					switch (opcionAdmin){
						case 1:
							AdminView.menuGestionClientes(gc);
							break;
						case 2:
							//Gestionar habitaciones
							break;
						case 3:
							//Gestionar reservas
						case 4:
							//Volver al menú anterior
						case 0:
							//Salir del programa
					}
				}else{
					System.out.println("No se ha podido iniciar sesión");
				}
			}else if(opcionMenuInicio==3){
				System.out.println("Un placer");
			}
		}while(opcionMenuInicio!=0);
	}
}
