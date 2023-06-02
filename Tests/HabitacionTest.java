package Tests;

import Models.Habitacion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class HabitacionTest {

    public Habitacion h1;

    @BeforeEach
    void setUp() {
        h1 = new Habitacion(12, "Doble", "Habitacion muy comoda", 2, 2, 23.43);
        LocalDate fechaEntrada = LocalDate.of(2023, 05, 27);
        LocalDate fechaSalida = LocalDate.of(2023, 05, 30);
        h1.setFechasOcupadas(fechaEntrada, fechaSalida);
    }

    @Test
    void getIdsListado() {
    }

    @Test
    void comprobarDisponibilidadHabitacion() {
        assertFalse(h1.comprobarDisponibilidadHabitacion(LocalDate.of(2023, 5, 24), LocalDate.of(2023, 05,27)));
        assertFalse(h1.comprobarDisponibilidadHabitacion(LocalDate.of(2023, 5, 23), LocalDate.of(2023, 05,27)));
        assertTrue(h1.comprobarDisponibilidadHabitacion(LocalDate.of(2023, 06, 6), LocalDate.of(2023, 06,27)));
    }
}