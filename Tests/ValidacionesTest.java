package Tests;

import Exceptions.*;
import org.junit.Test;

import Utils.Validaciones;

import static org.junit.Assert.*;

public class ValidacionesTest {
    @Test
    public void testControl() {

    }

    @Test
    public void testDni() {

    }

    @Test
    public void testEmail() {

    }

    @Test
    public void testEsUnNumero() {
        assertFalse(Validaciones.esUnNumero("a", true));
        assertFalse(Validaciones.esUnNumero("56", false));
        assertTrue(Validaciones.esUnNumero("5", true));
    }

    @Test
    public void testEstaVacio() {

    }

    @Test
    public void testFecha() {
        assertThrows(InvalidDateFormatException.class, ()->{
            Validaciones.fecha("07/02/200a", false);
            Validaciones.fecha("32/12/2004", false);
            //2005 no es bisiesto
            Validaciones.fecha("29/02/2005", false);
            Validaciones.fecha("32/12/204", false);
            Validaciones.fecha("32/12/2004", false);
        });

        assertThrows(AgeOfUserNotAllowedException.class, ()->{
            Validaciones.fecha("07/04/2006", false);
        });
    }

    @Test
    public void testNombreYApellidos() {
        assertThrows(InvalidCharacterInNameException.class, () -> {
            Validaciones.nombreYApellidos("9", false);
            Validaciones.nombreYApellidos("-", false);
        });

        assertThrows(EmptyStringException.class, () -> {
            Validaciones.nombreYApellidos("", false);
        });
    }

    @Test
    public void testTelefono() {
        assertThrows(InvalidFormatNumberException.class, () -> {
            Validaciones.telefono("69381072");
            Validaciones.telefono("aa");
            Validaciones.telefono("");
            Validaciones.telefono(null);
            Validaciones.telefono("6938106262");
            Validaciones.telefono("69381072A");
        });

        assertThrows(NumberNotAllowedException.class, () -> {
            Validaciones.telefono("123456789");
        });
    }

    @Test
    public void testValidarTarjeta() {

    }
}
