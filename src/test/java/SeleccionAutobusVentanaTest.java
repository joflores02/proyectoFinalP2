import Modelo.Autobus;
import Modelo.AutobusFactory;
import Modelo.Horario;
import Vista.SeleccionAutobusVentana;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SeleccionAutobusVentanaTest {

    private SeleccionAutobusVentana ventana;
    private List<Autobus> autobuses;
    private List<String> lugaresInicio;
    private List<String> lugaresDestino;

    @BeforeEach
    void setUp() {
        ventana = new SeleccionAutobusVentana();
        autobuses = new ArrayList<>();
        lugaresInicio = new ArrayList<>();
        lugaresDestino = new ArrayList<>();

        lugaresInicio.add("Concepción");
        lugaresDestino.add("Los Ángeles");
        lugaresDestino.add("Santiago");
        lugaresDestino.add("Chillán");

        Horario horario1 = new Horario("08:00 AM", "11:30 PM");
        Horario horario2 = new Horario("02:00 PM", "08:30 PM");
        Horario horario3 = new Horario("09:00 AM", "11:00 PM");

        Autobus autobus1 = AutobusFactory.crearAutobus("A1", "Económico", 1, "Concepción", "Santiago", horario2);
        Autobus autobus2 = AutobusFactory.crearAutobus("B1", "Económico", 2, "Concepción", "Santiago", horario1);
        Autobus autobus3 = AutobusFactory.crearAutobus("A2", "Económico", 1, "Concepción", "Santiago", horario3);

        autobuses.add(autobus1);
        autobuses.add(autobus2);
        autobuses.add(autobus3);

        ventana.setAutobuses(autobuses);

        ventana.setLugaresInicio(lugaresInicio);
        ventana.setDestinos(lugaresDestino);
    }


    @Test
    void testFiltrarAutobusesConOrigenSinDestino() {
        ventana.obtenerLugarInicio(autobuses.get(0));
        ventana.obtenerDestino(autobuses.get(0));

        ventana.realizarBusqueda();

        List<Autobus> autobusesFiltrados = ventana.getAutobusesFiltrados();
        assertEquals(0, autobusesFiltrados.size());
    }

    @Test
    void testInicializacionDeAutobuses() {
        // Validar que la lista de autobuses inicial tiene 3 elementos
        assertEquals(3, ventana.getAutobuses().size());
    }
}