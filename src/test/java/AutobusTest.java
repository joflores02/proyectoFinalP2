import Modelo.Asiento;
import Modelo.Autobus;
import Modelo.CategoriaAsiento;
import Modelo.Horario;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AutobusTest {
    Horario horario1 = new Horario("08:00 AM", "12:00 PM");
    @Test
    public void CrearAutobus1Piso() {
        Autobus autobus = Autobus.Factory.crearAutobus("A1", 1,"Concepción", "Chillán", horario1);
        assertNotNull(autobus);
        assertEquals(40, autobus.getNumAsientos());
        assertEquals("Económico", autobus.getTipo());
        assertEquals(1, autobus.getNumPisos());
    }

    @Test
    public void CrearAutobus2Pisos() {
        Autobus autobus = Autobus.Factory.crearAutobus("A2", 2,"Concepción", "Chillán", horario1);
        assertNotNull(autobus);
        assertEquals(60, autobus.getNumAsientos());
        assertEquals("Premium", autobus.getTipo());
        assertEquals(2, autobus.getNumPisos());
    }

    @Test
    public void InicializaAsientosPiso1() {
        Autobus autobus = Autobus.Factory.crearAutobus("A1", 1,"Concepción", "Chillán", horario1);
        autobus.inicializarAsientos(CategoriaAsiento.SEMI_CAMA, CategoriaAsiento.SEMI_CAMA);

        assertEquals(40, autobus.getPrimerPiso().size());
        assertEquals(0, autobus.getSegundoPiso().size());
    }

    @Test
    public void InicializaAsientosPiso2() {
        Autobus autobus = Autobus.Factory.crearAutobus("A2", 2,"Concepción", "Chillán", horario1);
        autobus.inicializarAsientos(CategoriaAsiento.SEMI_CAMA, CategoriaAsiento.SALON_CAMA);

        assertEquals(30, autobus.getPrimerPiso().size());
        assertEquals(30, autobus.getSegundoPiso().size());
    }

    @Test
    public void VerificarCategoríaAsientos() {
        Autobus autobus = Autobus.Factory.crearAutobus("A2", 2,"Concepción", "Chillán", horario1);
        autobus.inicializarAsientos(CategoriaAsiento.SEMI_CAMA, CategoriaAsiento.SALON_CAMA);

        for (Asiento asiento : autobus.getPrimerPiso()) {
            assertEquals(CategoriaAsiento.SEMI_CAMA.name(), asiento.getCategoria().name());
        }

        for (Asiento asiento : autobus.getSegundoPiso()) {
            assertEquals(CategoriaAsiento.SALON_CAMA.name(), asiento.getCategoria().name());
        }
    }


    @Test
    public void DisponibilidadAsientosPiso1() {
        Autobus autobus = Autobus.Factory.crearAutobus("A1", 1,"Concepción", "Chillán", horario1);
        autobus.inicializarAsientos(CategoriaAsiento.SEMI_CAMA, CategoriaAsiento.SEMI_CAMA);

        assertTrue(autobus.disponibilidadPrimerPiso());
    }

    @Test
    public void DisponibilidadAsientosPiso2() {
        Autobus autobus = Autobus.Factory.crearAutobus("A2", 2,"Concepción", "Chillán", horario1);
        autobus.inicializarAsientos(CategoriaAsiento.SEMI_CAMA, CategoriaAsiento.SALON_CAMA);

        assertTrue(autobus.disponibilidadSegundoPiso());
    }

    @Test
    public void TodosLosAsientosOcupados() {
        Autobus autobus = Autobus.Factory.crearAutobus("A2", 2,"Concepción", "Chillán", horario1);
        autobus.inicializarAsientos(CategoriaAsiento.SEMI_CAMA, CategoriaAsiento.SALON_CAMA);

        for (Asiento asiento : autobus.getPrimerPiso()) {
            asiento.reservar();
        }
        for (Asiento asiento : autobus.getSegundoPiso()) {
            asiento.reservar();
        }

        assertFalse(autobus.disponibilidadPrimerPiso());
        assertFalse(autobus.disponibilidadSegundoPiso());
    }

    @Test
    public void reservaAsiento() {
        Autobus autobus = Autobus.Factory.crearAutobus("A2", 2,"Concepción", "Chillán", horario1);
        autobus.inicializarAsientos(CategoriaAsiento.SEMI_CAMA, CategoriaAsiento.SALON_CAMA);

        boolean resultado = autobus.reservarAsientoSegundoPiso(1);
        assertTrue(resultado); //
    }

    @Test
    public void ReservarAsientoInexistente() {
        Autobus autobus = Autobus.Factory.crearAutobus("A2", 2,"Concepción", "Chillán", horario1);
        autobus.inicializarAsientos(CategoriaAsiento.SEMI_CAMA, CategoriaAsiento.SALON_CAMA);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            autobus.reservarAsientoSegundoPiso(999); // Intentamos reservar un asiento inexistente
        });
        assertEquals("El asiento no existe en el segundo piso.", exception.getMessage());
    }


}
