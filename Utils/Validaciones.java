package Utils;
import Exceptions.*;

import java.util.Calendar;

/**
 * Clase con métodos que permiten validar datos básicos de un cliente, como pueden ser:
 * - Nombre y sus apellidos
 * - Teléfono
 * - Correo electrónico
 *
 * @author Antoniobox
 * @since 18/02/2023
 * @version 1.0
 */
public class Validaciones {

	/**
	 * Método que comprueba en base a un String que el contenido es un número
	 * @param numero String a comprobar que es un número
	 * @param varios poner true en caso de que el String deban ser varios números, false en caso de que solo sea uno
	 * @return es un número
	 */
	public static boolean esUnNumero(String numero, boolean varios){
		if(!varios) 
			return !(numero.charAt(0)<'0' || numero.charAt(0)>'9') && numero.length()==1;
		else{
			for(int i=0; i<numero.length();i++){
				if(numero.charAt(i) < '0' || numero.charAt(i) > '9') return false;
			}
			return true;
		}
	}

	/**
	 * Comprueba que la cadena que se le pasa como parámetro no esté vacía
	 * @param string
	 * @return cadena vacía
	 */
	public static boolean estaVacio(String string){
		return string.length()==0;
	}

	/**
	 * Comprueba que el nombre o los apellidos son válidos, dependiendo del parametro booleano que se le pase.
	 *
	 * Hace comprobaciones tales como:
	 * - Letras de la A a la Z
	 * - Acentos
	 * - Tanto las letras Ñ como Ç
	 * @param frase que sea o el nombre o los apellidos
	 * @param sonApellidos
	 * @return nombre o apellidos válidos
	 */
	public static void nombreYApellidos(String frase, boolean sonApellidos) throws InvalidCharacterInNameException, EmptyStringException{
		frase = frase.trim();
		frase = frase.toUpperCase();
		int contadorEspacios = 0;
		if(estaVacio(frase)){
			throw new EmptyStringException("Error: no se puede dejar vacío");
		}
		for(int i = 0; i < frase.length(); i++){
			if(frase.charAt(i) < 'Á' || frase.charAt(i) > 'Ú'){
				if((frase.charAt(i) != 'Ç' || frase.charAt(i) != 'Ñ')){
					if(frase.charAt(i) < 'A' || frase.charAt(i) > 'Z' ){
						if(frase.charAt(i)==' '){
							contadorEspacios+=1;
						}
						else{
							throw new InvalidCharacterInNameException(sonApellidos ? "Caracteres inválidos en apellidos" : "Caracteres inválidos en nombre");
						}
					}
				}
			}
		}
		if(contadorEspacios==frase.length()){
			throw new InvalidCharacterInNameException("No se pueden dejar espacios en blanco únicamente");
		}
	}
	/**
	 * Comprueba que el email es válido siguiendo las siguientes comprobaciones:
	 * - No existen espacios en el correo
	 * - No se acepta el email en blanco
	 * - No se aceptan emails como @a.com o a@com
	 * - Se permiten las siguientes extensiones: com, es, org, online
	 * - Comprueba algunos caracteres no permitidos como la ñ
	 *
	 * @param email
	 * @return email válido
	 */
	public static void email(String email) throws EmptyStringException, InvalidEmailFormatException{
		String mensajeEmailInvalido = "No se acepta el formato de correo";
		email = email.trim();
		for(int i = 0; i < email.length(); i++){
			if(email.charAt(i)==' ') {
				throw new EmptyStringException("No se pueden dejar espacios en el email");
			}
		}
		int contA = 0;
		for(int i = 0; i < email.length(); i++){
			if(email.charAt(i)=='@'){
				contA++;
			}
		}
		//El email no se puede dejar en blanco
		if(estaVacio(email)){
			throw new EmptyStringException("No se puede dejar vacio el email");
		}
		//Debe de haber únicamente un @ en el correo para ser válido
		if(contA != 1){
			throw new InvalidEmailFormatException(mensajeEmailInvalido);
		}
		int posPunto;
		int pos_a = email.indexOf('@');
		//El arroba no puede estar al final del string u ocupar menos de 3 espacios (Recordemos que hay que comprobar las extensiones)
		if(pos_a==0 || pos_a==-1){
			throw new InvalidEmailFormatException(mensajeEmailInvalido);
		}
		String subEmail = email.substring(pos_a);
		//Vemos si hay un punto en algun sitio despues del @
		if(subEmail.indexOf('.')==-1){
			throw new InvalidEmailFormatException(mensajeEmailInvalido);
		}
		if(email.charAt(pos_a+1)=='.'){
			throw new InvalidEmailFormatException(mensajeEmailInvalido);
		}
		posPunto = subEmail.indexOf('.');
		String extension = subEmail.substring(posPunto+1);
		//Extensiones admitidas (com, es, org, online)
		if(!(extension.equals("com") || extension.equals("es") || extension.equals("org") || extension.equals("online"))){
			throw new InvalidEmailFormatException("Extension inválida del correo electrónico");
		}
	}

	/**
	 * Comprueba que el número de teléfono es válido mediante las siguientes comprobaciones:
	 * - El número de teléfono debe de estar compuesto por hasta 9 números
	 * - No puede estar en blanco
	 * - Debe de ser un número móvil o fijo
	 *
	 * @param telefono
	 * @return telefono válido
	 */
	public static void telefono(String telefono) throws InvalidFormatNumberException, NumberNotAllowedException{
		//Eliminamos cualquier tipo de espacio existente
		telefono = telefono.replaceAll("\\s", "");

		if(telefono != null && telefono.length()==9){
			for(int i=0; i<telefono.length(); i++){
				if(telefono.charAt(i) < '0' ||telefono.charAt(i) > '9'){
					throw new InvalidFormatNumberException("No se aceptan esos caracteres");
				}
			}
		}else{
			throw new InvalidFormatNumberException("String incompleto");
		}
		if(!(telefono.charAt(0) == '9' || telefono.charAt(0) == '8' || telefono.charAt(0) == '6' || telefono.charAt(0) == '7')) {
			throw new NumberNotAllowedException("No se acepta ese número de teléfono");
		}
	}

	/**
	 * Comprueba que la fecha de nacimiento es correcta en base a los siguientes criterios
	 * - El dia, el mes, y el año deben de o estar separados por '/' o por '-'.
	 * - Debe de respetar los dias, meses y años del calendario gregoriano
	 * - Deben de ser números
	 * - La longitud de la fecha ha de ser de 10 caracteres
	 * @param date
	 * @return fecha válida
	 */

	//TODO continuar reestructurando las validaciones para su posterior uso con excepciones
	public static void fecha(String date, boolean validarSintaxis) throws InvalidDateFormatException, AgeOfUserNotAllowedException{
		int mes, anyo, dias;
		int anyoActual = Calendar.getInstance().get(Calendar.YEAR);
		boolean bisiesto = false;
		date = date.trim();
		if(date.length()!=10){
			throw new InvalidDateFormatException("Longitud no permitida");
		}
		if((date.charAt(2)!='/' || date.charAt(5)!='/') && (date.charAt(2)!='-' || date.charAt(5)!='-')) {
			throw new InvalidDateFormatException("FORMATO INVALIDO");
		}

		//Comprueba que los dias, meses y años se encuentran en formato numérico
		for(int i = 0; i < 10; i++){
			if(i!=2 && i!=5){
				if(date.charAt(i) < '0' || date.charAt(i) > '9' ){
					throw new InvalidDateFormatException("Formato de la fecha incorrecto (solo se permiten números)");
				}
			}
		}
		dias = Integer.parseInt(date.substring(0, 2));
		mes = Integer.parseInt(date.substring(3, 5));
		anyo = Integer.parseInt(date.substring(6, 10));

		if((anyo%4==0 && anyo%100!=0) || anyo%400==0)
			bisiesto = true;
		if(mes==1 || mes==3 || mes==5 || mes==7 || mes==8 || mes==10 || mes==12){
			if(dias > 31 || dias <= 0){
				throw new InvalidDateFormatException("Error con los dias");
			}
		}
		else if(mes==4||mes==6||mes==9||mes==11){
			if(dias>30 || dias <= 0){
				throw new InvalidDateFormatException("Error con los meses");
			}
		}
		else if(mes==2) {
			if (bisiesto) {
				if (dias > 29 || dias <= 0) {
					throw new InvalidDateFormatException("Has superado el número de días permitido (febrero no tiene más de 29)");
				}
			} else {
				if (dias > 28 || dias <= 0) {
					throw new InvalidDateFormatException("Has superado el número de días permitido (febrero no tiene más de 28)");
				}
			}
		}
		//Rango de años permitidos
		if(!validarSintaxis){
			if(anyo<1950 ||anyo>anyoActual){
				throw new InvalidDateFormatException("Año no permitido");
			}
			if(anyoActual-anyo < 18){
				throw new AgeOfUserNotAllowedException("Usuario menor de edad, denegado el permiso para registrarse");
			}
		}
		else{
			if(anyo<anyoActual){
				throw new InvalidDateFormatException("Año inferior al año actual");
			}
		}
		if(mes < 0 || mes > 12){
			throw new InvalidDateFormatException("El mes debe de ser del 1-12");
		}
		if(!(dias>0 && mes>0)){
			throw new InvalidDateFormatException("No se acepta el valor 0");
		}
	}

	/**
	 * Valida la tarjeta usando el algoritmo de Luhn
	 * @param numero
	 * @return
	 */
	public static boolean validarTarjeta(String numero) {
		int suma = 0;
		boolean alternar = false;
		for (int i = numero.length() - 1; i >= 0; i--) {
			int n = Integer.parseInt(numero.substring(i, i + 1));
			if (alternar) {
				n *= 2;
				if (n > 9) {
					n = (n % 10) + 1;
				}
			}
			suma += n;
			alternar = !alternar;
		}
		return (suma % 10 == 0);
	}

	/**
	 * Comprueba que el dni es válido usando las siguientes validaciones:
	 * - Longitud de 9 caracteres
	 * - 8 primeros caracteres han de ser numeros y el ultimo una letra en mayuscula
	 * @param dni
	 * @return dni valido
	 */
	public static boolean dni(String dni){
		//la longitud de un DNI siempre es de 9 caracteres
		if(dni.length()!=9){
			System.out.println("longitud inválida");
			return false;
		}
		//Comprueba que los 8 primeros digitos son numeros
		for(int i=0; i<dni.length()-1;i++){
			if(dni.charAt(i)<'0' ||dni.charAt(i)>'9'){
				System.out.println("No se aceptan caracteres que no sean numeros");
				return false;
			}
		}
		//Se comprueba la letra del final
		if(dni.charAt(dni.length()-1)<'A'|| dni.charAt(dni.length()-1)>'Z'){
			System.out.println("Falta la letra final");
			return false;
		}
		else{
			int resultadoCalculo = Integer.parseInt(dni.substring(0, dni.length()-1))%23;
			if(resultadoCalculo==0) return dni.charAt(dni.length()-1)=='T';
			else if(resultadoCalculo==1) return dni.charAt(dni.length()-1)=='R';
			else if(resultadoCalculo==2) return dni.charAt(dni.length()-1)=='W';
			else if(resultadoCalculo==3) return dni.charAt(dni.length()-1)=='A';
			else if(resultadoCalculo==4) return dni.charAt(dni.length()-1)=='G';
			else if(resultadoCalculo==5) return dni.charAt(dni.length()-1)=='M';
			else if(resultadoCalculo==6) return dni.charAt(dni.length()-1)=='Y';
			else if(resultadoCalculo==7) return dni.charAt(dni.length()-1)=='F';
			else if(resultadoCalculo==8) return dni.charAt(dni.length()-1)=='P';
			else if(resultadoCalculo==9) return dni.charAt(dni.length()-1)=='D';
			else if(resultadoCalculo==10) return dni.charAt(dni.length()-1)=='X';
			else if(resultadoCalculo==11) return dni.charAt(dni.length()-1)=='B';
			else if(resultadoCalculo==12) return dni.charAt(dni.length()-1)=='N';
			else if(resultadoCalculo==13) return dni.charAt(dni.length()-1)=='J';
			else if(resultadoCalculo==14) return dni.charAt(dni.length()-1)=='Z';
			else if(resultadoCalculo==15) return dni.charAt(dni.length()-1)=='S';
			else if(resultadoCalculo==16) return dni.charAt(dni.length()-1)=='Q';
			else if(resultadoCalculo==17) return dni.charAt(dni.length()-1)=='V';
			else if(resultadoCalculo==18) return dni.charAt(dni.length()-1)=='H';
			else if(resultadoCalculo==19) return dni.charAt(dni.length()-1)=='L';
			else if(resultadoCalculo==20) return dni.charAt(dni.length()-1)=='C';
			else if(resultadoCalculo==21) return dni.charAt(dni.length()-1)=='K';
			else if(resultadoCalculo==23) return dni.charAt(dni.length()-1)=='E';
		}
		return true;
	}

	/**
	 * Retorna un código que se genera en base a una frase que se le pasa, la cual debe de:
	 * - Estar compuesta por 4 palabras separadas por 1 espacio
	 * - Puede estar compuesta por cualquier tipo de caracter
	 * @param frase
	 * @return codigo
	 */
	public static String control(String frase){
		//Máximo tres espacios
		int contadorEspacios = 0;
		String mayusculas = "";
		String ultimasLetras = "";
		int codigoNum = 0;
		String codigo;
		frase = frase.trim();
		//Le agrego un espacio al final para facilitar el check
		for(int i = 0; i < frase.length(); i++){
			if(frase.charAt(i)==' ')
				contadorEspacios++;
		}
		if(contadorEspacios!=3){
			System.out.println("NO PERMITIDO");
			return "ERROR";
		}
		mayusculas+=frase.charAt(0);
		for(int i = 0; i < frase.length(); i++){
			if(frase.charAt(i)==' '){
				ultimasLetras+=""+frase.charAt(i-1);
				if(!(i+1==frase.length())){
					mayusculas+=""+frase.charAt(i+1);
				}
			}
		}
		for(int i = 0; i<ultimasLetras.length(); i++){
			codigoNum += ultimasLetras.charAt(i);
		}
		codigo = mayusculas+(codigoNum/4 - codigoNum%4);
		codigo = codigo.toUpperCase();
		return codigo;
	}
}
