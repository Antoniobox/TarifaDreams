package Utils;
import Exceptions.*;
import org.junit.Ignore;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Clase con métodos que permiten validar los datos de un cliente
 * @author Antoniobox
 * @since 18/02/2023
 * @version 2.0
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
		String patron = "\\d{9}";
		Pattern pattern = Pattern.compile(patron);
		Matcher matcher = pattern.matcher(telefono);
		if(!matcher.matches()){
			throw new InvalidFormatNumberException("Número inválido");
		}
		if(!(telefono.charAt(0) == '9' || telefono.charAt(0) == '8' || telefono.charAt(0) == '6' || telefono.charAt(0) == '7')) {
			throw new NumberNotAllowedException("No se acepta ese número de teléfono");
		}
	}

	/**
	 * Comprueba que la fecha es correcta
	 * @param date
	 * @param validarSintaxis true si no se quiere comprobar la edad del usuario, false si se quiere comprobar que es mayor de edad
	 * @throws InvalidDateFormatException
	 * @throws AgeOfUserNotAllowedException
	 */

	public static void fecha(String date, boolean validarSintaxis) throws InvalidDateFormatException, AgeOfUserNotAllowedException{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate fecha;
		LocalDate fechaActual = LocalDate.now();
		try {
			fecha = LocalDate.parse(date, formatter);
		} catch (DateTimeParseException e) {
			throw new InvalidDateFormatException("Fecha inválida");
		}
		if(!validarSintaxis){
			Period edadUsuario = Period.between(fecha, fechaActual);
			if(edadUsuario.getYears()<18){
				throw new AgeOfUserNotAllowedException("Usuario menor de edad");
			}
		}
	}

	/**
	 * Valida la tarjeta usando el algoritmo de Luhn
	 * @param numero
	 * @return
	 */
	public static boolean validarTarjeta(String numero) {
		if(numero.length() > 18 || numero.length() < 13){
			System.out.println("Longitud inválida");
			return false;
		}
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
	public static void dni(String dni) throws InvalidDNIFormatException{
		//la longitud de un DNI siempre es de 9 caracteres
		if(dni.length()!=9){
			throw new InvalidDNIFormatException("La longitud del DNI no es correcta");
		}
		//Comprueba que los 8 primeros digitos son numeros
		for(int i=0; i<dni.length()-1;i++){
			if(dni.charAt(i)<'0' ||dni.charAt(i)>'9'){
				throw new InvalidDNIFormatException("No se aceptan caracteres que no sean numeros");
			}
		}
		//Se comprueba la letra del final
		if(dni.charAt(dni.length()-1)<'A'|| dni.charAt(dni.length()-1)>'Z'){
			throw new InvalidDNIFormatException("No se ha encontrado la letra del DNI");
		}
		else{
			int resultadoCalculo = Integer.parseInt(dni.substring(0, dni.length()-1))%23;
			String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
			if(!(dni.charAt(8)==letras.charAt(resultadoCalculo))){
				throw new InvalidDNIFormatException("Letra incorrecta en el DNI");
			}
		}
	}

	/**
	 * Retorna un código que se genera en base a una frase que se le pasa, la cual debe de:
	 * - Estar compuesta por 4 palabras separadas por 1 espacio
	 * - Puede estar compuesta por cualquier tipo de caracter
	 * @param frase
	 * @return codigo
	 */
	public static String control(String frase) throws InvalidStringForControlException {
		if(frase.length() < 8){

		}
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
			throw new InvalidStringForControlException("No se permite ese formato");
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

	/**
	 * Método para validar el nombre de usuario
	 * @param nombreUsuario
	 */
	public static void nombreUsuario(String nombreUsuario) throws InvalidUsernameException{
		//La única comprobación que se ha de realizar es que cumpla con la longitud adecuada y que no existan espacios en blanco
		if(nombreUsuario.length()<4){
			throw new InvalidUsernameException("Longitud inválida");
		}
		for(int i=0; i<nombreUsuario.length();i++){
			if(nombreUsuario.charAt(i)==' '){
				throw new InvalidUsernameException("No se admiten espacios en blanco");
			}
		}
	}
}
