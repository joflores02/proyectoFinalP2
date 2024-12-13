import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Modelo.Asiento;
import Modelo.Autobus;
import Modelo.Horario;
import Vista.SeleccionAsientosVentana;

import java.util.List;

public class SeleccionAsientosVentanaTest {

    private SeleccionAsientosVentana ventana;
    private Autobus autobus;

    @BeforeEach
    public void setUp() {
        // Crear un horario y un autobús de prueba
        Horario horario = new Horario("02:00 PM", "08:30 PM");
        autobus = Autobus.Factory.crearAutobus("A1", 2, "Concepción", "Santiago", horario, "Premium");
        ventana = new SeleccionAsientosVentana(autobus);
    }

    @Test
    public void testManejarClicReservaLiberacion() {
        // Obtener el primer asiento del primer piso
        Asiento asiento = autobus.getPrimerPiso().get(0);

        // Verificar que el asiento esté libre inicialmente
        assertFalse(asiento.isOcupado(), "El asiento debería estar libre al principio");

        // Simular un clic en el asiento
        ventana.manejarClic(asiento.getX() + 25, asiento.getY() + 25);

        // Verificar que el asiento esté reservado
        assertTrue(asiento.isOcupado(), "El asiento debería estar reservado después del clic");

        // Verificar que el asiento esté en el Set de asientos reservados
        assertTrue(SeleccionAsientosVentana.asientosReservados.contains(asiento), "El asiento debería estar en el Set de reservados");

        // Simular otro clic para liberar el asiento
        ventana.manejarClic(asiento.getX() + 25, asiento.getY() + 25);

        // Verificar que el asiento esté libre nuevamente
        assertFalse(asiento.isOcupado(), "El asiento debería estar libre nuevamente");

        // Verificar que el asiento ya no esté en el Set de asientos reservados
        assertFalse(SeleccionAsientosVentana.asientosReservados.contains(asiento), "El asiento no debería estar en el Set de reservados");
    }
}
