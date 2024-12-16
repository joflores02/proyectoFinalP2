import Modelo.Asiento;
import Modelo.CategoriaAsiento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AsientoTest {

    private Asiento asiento;

    @BeforeEach
    void setUp() {
        // Inicializa un asiento antes de cada prueba
        asiento = new Asiento(1, 2, CategoriaAsiento.SALON_CAMA);
    }

    @Test
    void testConstructor() {
        // Verifica que el asiento se inicializa correctamente
        assertEquals(1, asiento.getFila());
        assertEquals(2, asiento.getColumna());
        assertTrue(asiento.isDisponible());
        assertFalse(asiento.isSeleccionado());
        assertFalse(asiento.isReservado());
        assertEquals(CategoriaAsiento.SALON_CAMA, asiento.getCategoriaAsiento());
    }

    @Test
    void testSeleccionarAsientoDisponible() {
        // Verifica que un asiento disponible se pueda seleccionar
        asiento.seleccionar();
        assertTrue(asiento.isSeleccionado());
        assertTrue(asiento.isDisponible());
        assertFalse(asiento.isReservado());
    }

    @Test
    void testSeleccionarAsientoReservado() {
        // Verifica que un asiento reservado no se pueda seleccionar
        asiento.reservar();
        asiento.seleccionar();
        assertFalse(asiento.isSeleccionado());
    }

    @Test
    void testDeseleccionarAsiento() {
        // Verifica que se pueda deseleccionar un asiento
        asiento.seleccionar();
        asiento.deseleccionar();
        assertFalse(asiento.isSeleccionado());
    }

    @Test
    void testReservarAsientoDisponible() {
        // Verifica que un asiento disponible se pueda reservar
        asiento.reservar();
        assertTrue(asiento.isReservado());
        assertFalse(asiento.isDisponible());
        assertFalse(asiento.isSeleccionado());
    }

    @Test
    void testReservarAsientoYaReservado() {
        // Verifica que no cambie el estado al intentar reservar un asiento ya reservado
        asiento.reservar();
        asiento.reservar();
        assertTrue(asiento.isReservado());
        assertFalse(asiento.isDisponible());
    }
}