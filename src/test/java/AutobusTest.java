import Modelo.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AutobusTest {

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
