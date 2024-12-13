import Modelo.Asiento;
import Modelo.CategoriaAsiento;
import Vista.AutobusRenderer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.*;

public class AutobusRendererTest {

    @Test
    public void testDeterminarColorAsiento() {
        AutobusRenderer renderer = new AutobusRenderer();

        // Crear asiento no ocupado de categoría "Semi-Cama"
        Asiento asiento1 = new Asiento(1, CategoriaAsiento.SEMI_CAMA, 50, 50);
        asiento1.setOcupado(false);
        assertEquals(Color.YELLOW, renderer.determinarColorAsiento(asiento1));

        // Crear asiento no ocupado de categoría desconocida
        Asiento asiento2 = new Asiento(2, CategoriaAsiento.SALON_CAMA, 150, 150);
        asiento2.setOcupado(false);
        assertEquals(Color.PINK, renderer.determinarColorAsiento(asiento2));

        // Crear asiento ocupado de categoría "Salón-Cama"
        Asiento asiento3 = new Asiento(3, CategoriaAsiento.SALON_CAMA, 100, 100);
        asiento3.setOcupado(true); // Marca ocupado
        assertEquals(Color.BLACK, renderer.determinarColorAsiento(asiento3));

    }
}

