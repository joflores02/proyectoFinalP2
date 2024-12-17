import Controlador.SistemaDeReserva;
import Modelo.*;
import Modelo.CategoriaAsiento;
import Vista.VistaSeleccionAsientos;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import javax.swing.*;

import java.util.ArrayList;
import java.util.List;

class GestionReservasTest {
    private GestionReservas gestionReservas;
    private List<Asiento> asientos;

    @BeforeEach
    void setUp() {
        // Crear asientos de prueba
        asientos = new ArrayList<>();
        asientos.add(new Asiento(1, 1, CategoriaAsiento.SALON_CAMA));
        asientos.add(new Asiento(2, 2, CategoriaAsiento.SALON_CAMA));
        asientos.add(new Asiento(3, 3, CategoriaAsiento.SEMI_CAMA));

        // Inicializar GestionReservas con la lista de asientos
        gestionReservas = new GestionReservas(asientos);
    }

    @Test
    void testSeleccionarAsientoDisponibleYNoReservado() {
        Asiento asiento = asientos.get(0);
        gestionReservas.seleccionarAsiento(asiento);
        assertTrue(asiento.isSeleccionado());
    }

    @Test
    void testSeleccionarAsientoNoDisponible() {
        Asiento asiento = asientos.get(1);
        gestionReservas.reservarAsiento(asiento);
        assertFalse(asiento.isSeleccionado());
    }


    @Test
    void testDeseleccionarAsiento() {
        Asiento asiento = asientos.get(0);
        gestionReservas.seleccionarAsiento(asiento);
        gestionReservas.deseleccionarAsiento(asiento);
        assertFalse(asiento.isSeleccionado());
    }

    @Test
    void testDisponibilidadAsiento() {
        Asiento asientoDisponible = asientos.get(0); // Disponible
        Asiento asientoNoDisponible = asientos.get(1); // No disponible
        gestionReservas.reservarAsiento(asientoNoDisponible);

        assertTrue(gestionReservas.disponibilidadAsiento(asientoDisponible));
        assertFalse(gestionReservas.disponibilidadAsiento(asientoNoDisponible));
    }

    @Test
    void testReservarAsientoDisponible() {
        Asiento asiento = asientos.get(0); // Disponible y no reservado
        gestionReservas.reservarAsiento(asiento);
        assertTrue(asiento.isReservado());
        assertFalse(asiento.isDisponible());
    }

    @Test
    void testReservarAsientoNoDisponible() {
        Asiento asiento = asientos.get(1); // No disponible
        gestionReservas.reservarAsiento(asiento);
        assertTrue(asiento.isReservado());
    }

    @Test
    void testReservarAsientoYaReservado() {
        Asiento asiento = asientos.get(2); // Ya reservado
        gestionReservas.reservarAsiento(asiento);
        assertTrue(asiento.isReservado()); // Sigue reservado
    }

    @Test
    void testGetAsientos() {
        List<Asiento> asientosGestionados = gestionReservas.getAsientos();
        assertEquals(asientos.size(), asientosGestionados.size());
        assertIterableEquals(asientos, asientosGestionados);
    }
}
