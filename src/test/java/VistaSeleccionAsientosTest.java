import Controlador.SistemaDeReserva;
import Modelo.*;
import Vista.VistaSeleccionAsientos;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import javax.swing.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class VistaSeleccionAsientosTest {

    private SistemaDeReserva sistemaDeReserva;
    private List<Autobus> autobuses;
    private VistaSeleccionAsientos vista;

    @BeforeEach
    public void setUp() {
        // Crear horarios
        Horario horario1 = new Horario("08:00 AM", "11:30 PM");
        Horario horario2 = new Horario("02:00 PM", "08:30 PM");
        Horario horario3 = new Horario("09:00 AM", "11:00 PM");
        Horario horario4 = new Horario("10:00 AM", "06:30 PM");

        // Crear autobuses
        Autobus autobus1 = AutobusFactory.crearAutobus("A1", "Económico", 1, "Concepción", "Santiago", horario2);
        Autobus autobus2 = AutobusFactory.crearAutobus("B1", "Económico", 2, "Concepción", "Santiago", horario1);
        Autobus autobus3 = AutobusFactory.crearAutobus("A2", "Económico", 1, "Concepción", "Santiago", horario3);
        Autobus autobus4 = AutobusFactory.crearAutobus("A3", "Premium", 1, "Concepción", "Santiago", horario4);
        Autobus autobus5 = AutobusFactory.crearAutobus("A4", "Económico", 1, "Concepción", "Chillán", horario1);
        Autobus autobus6 = AutobusFactory.crearAutobus("A5", "Económico", 1, "Concepción", "Los Ángeles", horario3);
        Autobus autobus7 = AutobusFactory.crearAutobus("B2", "Premium", 2, "Concepción", "Santiago", horario4);
        Autobus autobus8 = AutobusFactory.crearAutobus("A6", "Premium", 1, "Concepción", "Chillán", horario3);
        Autobus autobus9 = AutobusFactory.crearAutobus("B3", "Económico", 2, "Concepción", "Los Ángeles", horario1);

        // Crear lista de autobuses
        autobuses = new ArrayList<>();
        autobuses.add(autobus1);
        autobuses.add(autobus2);
        autobuses.add(autobus3);
        autobuses.add(autobus4);
        autobuses.add(autobus5);
        autobuses.add(autobus6);
        autobuses.add(autobus7);
        autobuses.add(autobus8);
        autobuses.add(autobus9);

        // Crear instancia del sistema de reserva
        JFrame frame = new JFrame();
        sistemaDeReserva = new SistemaDeReserva(frame);
    }

    @Test
    public void testCambioDePiso() {
        Autobus autobus1 = autobuses.get(1);
        VistaSeleccionAsientos vista = new VistaSeleccionAsientos(autobus1);
        int pisoInicial = vista.getPisoActual();

        vista.cambiarPiso();

        assertNotEquals(pisoInicial, vista.getPisoActual());
    }

    @Test
    public void testVentanaInicializada() {
        Autobus autobus1 = autobuses.get(1);
        vista = new VistaSeleccionAsientos(autobus1);
        assertNotNull(vista);
        assertEquals("Selección de Asientos", vista.getTitle());
        assertNotNull(vista.getContentPane());
        assertTrue(vista.isVisible());
    }

    @Test
    public void testSeleccionAsiento() {
        Autobus autobus = autobuses.get(1);
        VistaSeleccionAsientos vista = new VistaSeleccionAsientos(autobus);
        JPanel panelAsientos = (JPanel) vista.getContentPane().getComponent(0);

        // Inicialmente, un asiento debería estar disponible
        Asiento asiento = autobus.getAsientosPorPiso(0).get(0);
        assertTrue(asiento.isDisponible());

        // Coordenadas de selección del asiento
        int x = 60;
        int y = 60;

        // Realizar selección de asiento
        vista.manejarSeleccionAsiento(x, y, panelAsientos);

        // Asegurarse de que el estado del asiento haya cambiado
        assertTrue(asiento.isSeleccionado());
    }

}
