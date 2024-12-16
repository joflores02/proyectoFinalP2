import static org.junit.jupiter.api.Assertions.*;
import javax.swing.*;
import Controlador.SistemaDeReserva;
import Vista.VentanaIngresoSistema;
import org.junit.jupiter.api.*;

class SistemaDeReservaTest {

    private JFrame frame;
    private SistemaDeReserva sistemaDeReserva;

    @BeforeEach
    void setUp() {
        frame = new JFrame("Test Sistema de Reserva");
        sistemaDeReserva = new SistemaDeReserva(frame);
        frame.setVisible(true);
    }

    @AfterEach
    void tearDown() {
        frame.dispose();
    }

    @Test
    void testInicializaConVentanaIngreso() {
        // Verifica que la ventana inicial mostrada es VentanaIngresoSistema
        assertTrue(frame.getContentPane() instanceof VentanaIngresoSistema,
                "La ventana inicial debería ser VentanaIngresoSistema");
    }
}
