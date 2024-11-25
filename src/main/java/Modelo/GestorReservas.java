package Modelo;
import java.util.ArrayList;
import java.util.List;
public class GestorReservas {
    private static GestorReservas instancia;
    private List<Autobus> listaAutobuses;


    private GestorReservas() {
        listaAutobuses = new ArrayList<>();
    }


    public static GestorReservas getInstance() {
        if (instancia == null) {
            instancia = new GestorReservas();
        }
        return instancia;
    }


    public void agregarAutobus(Autobus autobus) {
        listaAutobuses.add(autobus);
    }


    public List<Autobus> getListaAutobuses() {
        return listaAutobuses;
    }


    public boolean verificarDisponibilidad(String idAutobus) {
        for (Autobus autobus : listaAutobuses) {
            if (autobus.getId().equals(idAutobus)) {
                return autobus.disponibilidadPrimerPiso() || autobus.disponibilidadSegundoPiso();
            }
        }
        return false;
    }


    public boolean reservarAsiento(String idAutobus, int numeroAsiento, int piso) {
        for (Autobus autobus : listaAutobuses) {
            if (autobus.getId().equals(idAutobus)) {
                if (piso == 1) {
                    return autobus.reservarAsientoPrimerPiso(numeroAsiento);
                } else if (piso == 2) {
                    return autobus.reservarAsientoSegundoPiso(numeroAsiento);
                }
            }
        }
        return false;
    }
}

