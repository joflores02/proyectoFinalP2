package Modelo;

import java.util.List;

public class GestorReservas {

    private List<Asiento> asientos;

    // Constructor que recibe la lista de asientos ya existentes
    public GestorReservas(List<Asiento> asientos) {
        this.asientos = asientos;
    }

    // Método para verificar si un asiento está disponible
    public boolean estaDisponible(int numeroAsiento) {
        Asiento asiento = buscarAsiento(numeroAsiento);
        return asiento != null && asiento.disponibilidadAsiento();
    }

    // Método para hacer una reserva de asiento
    public boolean reservarAsiento(int numeroAsiento) {
        Asiento asiento = buscarAsiento(numeroAsiento);
        if (asiento != null && asiento.disponibilidadAsiento()) {
            return asiento.reservar(); // Marca el asiento como reservado si está disponible
        }
        return false; // No disponible para reservar
    }

    /* Método para cancelar una reserva
    public boolean cancelarReserva(int numeroAsiento) {
        Asiento asiento = buscarAsiento(numeroAsiento);
        if (asiento != null && !asiento.disponibilidadAsiento()) {
            asiento.reservar(); // Anula la reserva
            return true; // Cancelación exitosa
        }
        return false; // El asiento no estaba reservado
    }

     */

    // Método para buscar un asiento por su número
    private Asiento buscarAsiento(int numeroAsiento) {
        for (Asiento asiento : asientos) {
            if (asiento.getNumero() == numeroAsiento) {
                return asiento;
            }
        }
        return null; // Si no se encuentra el asiento
    }
}
