import Modelo.Asiento;
import Modelo.CategoriaAsiento;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AsientoTest {

    @Test
    public void DisponibilidadInicial() {
        Asiento asiento = new Asiento(1, CategoriaAsiento.SEMI_CAMA);
        assertTrue(asiento.disponibilidadAsiento());
    }

    @Test
    public void ReservarAsientoDisponible() {
        Asiento asiento = new Asiento(1, CategoriaAsiento.SEMI_CAMA);

        boolean resultado = asiento.reservar();
        assertTrue(resultado);
        assertFalse(asiento.disponibilidadAsiento());
    }

    @Test
    public void ReservarAsientoOcupado() {
        Asiento asiento = new Asiento(1, CategoriaAsiento.SEMI_CAMA);

        asiento.reservar();
        boolean resultado = asiento.reservar();
        assertFalse(resultado);
    }

}
