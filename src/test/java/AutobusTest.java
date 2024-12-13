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
}
