package Modelo;

import java.util.List;

/**
 * Clase encargada de gestionar las reservas de asientos en el sistema de autobuses.
 */
public class GestionReservas {
    /**
     * Lista de asientos disponibles en el sistema.
     */
    private List<Asiento> asientos;

    /**
     * Constructor de la clase GestionReservas.
     *
     * @param asientos Lista de asientos que se gestionarán en el sistema.
     */
    public GestionReservas(List<Asiento> asientos) {
        this.asientos = asientos;
    }

    /**
     * Selecciona un asiento si está disponible y no ha sido reservado.
     *
     * @param asiento El asiento a seleccionar.
     */
    public void seleccionarAsiento(Asiento asiento) {
        if (asiento.isDisponible() && !asiento.isReservado()) {
            asiento.seleccionar();
        }
    }

    /**
     * Deselecciona un asiento previamente seleccionado.
     *
     * @param asiento El asiento a deseleccionar.
     */
    public void deseleccionarAsiento(Asiento asiento) {
        asiento.deseleccionar();
    }

    /**
     * Verifica la disponibilidad de un asiento.
     *
     * @param asiento El asiento  al que se quiere verificar la disponibilidad.
     * @return true si el asiento está disponible, false en caso contrario.
     */
    public boolean disponibilidadAsiento(Asiento asiento) {
        if (asiento.isDisponible()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Reserva un asiento si está disponible.
     *
     * @param asiento El asiento a reservar.
     */
    public void reservarAsiento(Asiento asiento) {
        if (asiento.isDisponible()) {
            asiento.reservar();
        }
    }

    /**
     * Obtiene la lista de asientos gestionados por el sistema.
     *
     * @return La lista de asientos.
     */
    public List<Asiento> getAsientos() {
        return asientos;
    }
}