import Modelo.*;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AutobusTest {
    @Test
    public void testCrearAutobus() {
        Horario horario = new Horario("2024-12-15 08:00", "2024-12-15 10:00");
        Autobus autobus = new Autobus("A123", 32, "Económico", 1, "Santiago", "Valparaíso", horario, 4, 8);

        assertEquals("A123", autobus.getId());
        assertEquals(32, autobus.getNumAsientos());
        assertEquals("Económico", autobus.getTipo());
        assertEquals(1, autobus.getNumPisos());
        assertEquals("Santiago", autobus.getLugarDeInicio());
        assertEquals("Valparaíso", autobus.getLugarDeDestino());
        assertEquals(horario, autobus.getHorario());
    }

    @Test
    public void testObtenerAsientosPorPiso() {
        Horario horario = new Horario("2024-12-15 08:00", "2024-12-15 10:00");
        Autobus autobus = new Autobus("A123", 40, "Económico", 2, "Santiago", "Valparaíso", horario, 10, 4);

        List<Asiento> asientosPiso1 = autobus.getAsientosPorPiso(1);
        assertNotNull(asientosPiso1);
        assertEquals(40, asientosPiso1.size());
    }

    @Test
    public void testObtenerAsiento() {
        Horario horario = new Horario("2024-12-15 08:00", "2024-12-15 10:00");
        Autobus autobus = new Autobus("A123", 40, "Económico", 2, "Santiago", "Valparaíso", horario, 10, 4);

        Asiento asiento = autobus.obtenerAsiento(0, 0, 0);
        assertNotNull(asiento);
        assertEquals(0, asiento.getFila());
        assertEquals(0, asiento.getColumna());
    }

    @Test
    public void testObtenerPrecio() {
        Horario horario = new Horario("2024-12-15 08:00", "2024-12-15 10:00");
        Autobus autobusEconómico = new Autobus("A123", 40, "Económico", 2, "Santiago", "Valparaíso", horario, 10, 4);
        Autobus autobusPremium = new Autobus("B456", 40, "Premium", 2, "Santiago", "Valparaíso", horario, 10, 4);

        assertEquals(CategoriaAsiento.SEMI_CAMA.getPrecio(), autobusEconómico.obtenerPrecio());
        assertEquals(CategoriaAsiento.SALON_CAMA.getPrecio(), autobusPremium.obtenerPrecio());
    }

    @Test
    public void testObtenerAsientosSeleccionados() {
        Horario horario = new Horario("2024-12-15 08:00", "2024-12-15 10:00");
        Autobus autobus = new Autobus("A123", 40, "Económico", 2, "Santiago", "Valparaíso", horario, 10, 4);

        Asiento asiento1 = autobus.obtenerAsiento(0, 0, 0);
        Asiento asiento2 = autobus.obtenerAsiento(1, 1, 1);
        asiento1.seleccionar();
        asiento2.seleccionar();

        List<Asiento> asientosSeleccionados = autobus.obtenerAsientosSeleccionados();
        assertEquals(2, asientosSeleccionados.size());
        assertTrue(asientosSeleccionados.contains(asiento1));
        assertTrue(asientosSeleccionados.contains(asiento2));
    }

    @Test
    public void testIteradorAsientos() {
        Horario horario = new Horario("2024-12-15 08:00", "2024-12-15 10:00");
        Autobus autobus = new Autobus("A123", 32, "Económico", 1, "Santiago", "Valparaíso", horario, 4, 8);

        Iterator<Asiento> iterador = autobus.iterator();
        int contador = 0;

        while (iterador.hasNext()) {
            iterador.next();
            contador++;
        }

        assertEquals(32, contador);
    }

}