
import Modelo.Asiento;
import Modelo.Autobus;
import Modelo.CategoriaAsiento;
import Modelo.Horario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AutobusTest {
    private Autobus autobus;

    @BeforeEach
    public void setUp() {
        // Crear un autobús de prueba con 2 pisos, 76 asientos, y diferentes categorías
        Horario horario = new Horario("02:00 PM", "08:30 PM");
        autobus = Autobus.Factory.crearAutobus("A1", 2, "Concepción", "Santiago", horario, "Premium");
    }


    @Test
    public void testReservaDeAsientos() {
        // Reservar un asiento del primer piso
        Asiento asientoPrimerPiso = autobus.getPrimerPiso().get(0);
        asientoPrimerPiso.setOcupado(true);

        // Verificar que el asiento esté reservado
        assertTrue(asientoPrimerPiso.isOcupado(), "El asiento del primer piso debería estar reservado");

        // Liberar el asiento
        asientoPrimerPiso.setOcupado(false);

        // Verificar que el asiento esté libre
        assertFalse(asientoPrimerPiso.isOcupado(), "El asiento del primer piso debería estar libre");
    }

    @Test
    public void testCategoriasDeAsientos() {
        // Verificar que todos los asientos del primer piso tienen la categoría correcta
        for (Asiento asiento : autobus.getPrimerPiso()) {
            assertEquals(CategoriaAsiento.SEMI_CAMA, asiento.getCategoria(), "El asiento del primer piso debería ser Semi-Cama");
        }

        // Verificar que todos los asientos del segundo piso tienen la categoría correcta
        for (Asiento asiento : autobus.getSegundoPiso()) {
            assertEquals(CategoriaAsiento.SALON_CAMA, asiento.getCategoria(), "El asiento del segundo piso debería ser Salón-Cama");
        }
    }

    @Test
    void testCrearAutobus() {
        Horario horario = new Horario("08:00 AM", "11:30 PM");
        Horario horario2 = new Horario("02:00 PM", "08:30 PM");
        Horario horario3 = new Horario("09:00 AM", "11:00 PM");
        Horario horario4 = new Horario("10:00 AM", "06:30 PM");
        Autobus autobus = Autobus.Factory.crearAutobus("A1", 1, "Santiago", "Valparaíso", horario, "Económico");

        // Verificar que los atributos del autobús sean correctos
        assertEquals("A1", autobus.getId());
        assertEquals(38, autobus.getNumAsientos());
        assertEquals("Económico", autobus.getTipo());
        assertEquals(1, autobus.getNumPisos());
        assertEquals("Santiago", autobus.getLugarDeInicio());
        assertEquals("Valparaíso", autobus.getLugarDeDestino());
        assertEquals(horario, autobus.getHorario());
    }
    @Test
    void testGetTipoAsiento() {
        Horario horario = new Horario("08:00 AM", "11:30 PM");
        Autobus autobus = Autobus.Factory.crearAutobus("A5", 1, "Santiago", "Valparaíso", horario, "Premium");

        // Obtener el tipo de asiento basado en el tipo de autobús
        assertEquals("Semi-Cama", autobus.getTipoAsiento()); // Autobús de 1 piso y tipo Premium

        Autobus autobus2 = Autobus.Factory.crearAutobus("A6", 2, "Santiago", "Valparaíso", horario, "Económico");
        assertEquals("Semi-Cama", autobus2.getTipoAsiento()); // Autobús de 2 pisos y tipo Económico
    }




}
