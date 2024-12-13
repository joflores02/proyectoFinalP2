package Modelo;

/**
 * Clase que representa un asiento en el sistema de reserva.
 * Contiene información sobre el número, estado de reserva, ocupación, categoría, precio y posición del asiento.
 */
public class Asiento {
    private int numero;
    private boolean estadoReserva;
    private boolean ocupado;
    private boolean seleccionado;
    private CategoriaAsiento categoria;
    private double precio;
    private int x;
    private int y;

    /**
     * Constructor de la clase Asiento.
     *
     * @param numero Número identificador del asiento.
     * @param categoria Categoría del asiento, que define el precio y tipo.
     * @param x Coordenada horizontal de la posición del asiento.
     * @param y Coordenada vertical de la posición del asiento.
     */
    public Asiento(int numero, CategoriaAsiento categoria, int x, int y) {
        this.numero = numero;
        this.estadoReserva = false;
        this.categoria = categoria;
        this.precio = categoria.getPrecio();
        this.x = x;
        this.y = y;
    }

    /**
     * Obtiene el número identificador del asiento.
     *
     * @return El número del asiento.
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Verifica si el asiento está reservado.
     *
     * @return {true} si el asiento está reservado, de lo contrario {false}.
     */
    public boolean estadoReserva() {
        return estadoReserva;
    }

    /**
     * Obtiene la categoría del asiento.
     *
     * @return La categoría del asiento.
     */
    public CategoriaAsiento getCategoria() {
        return categoria;
    }

    /**
     * Obtiene el precio del asiento.
     *
     * @return El precio del asiento.
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Verifica si el asiento está disponible para ser reservado.
     *
     * @return {true} si el asiento no está reservado, de lo contrario {false}.
     */
    public boolean disponibilidadAsiento() {
        return !estadoReserva;
    }

    /**
     * Intenta reservar el asiento si está disponible.
     *
     * @return {true} si la reserva fue exitosa, de lo contrario {false}.
     */
    public boolean reservar() {
        if (disponibilidadAsiento()) {
            estadoReserva = true;
            return true;
        }
        return false;
    }

    /**
     * Actualiza la posición del asiento.
     *
     * @param x Nueva coordenada horizontal.
     * @param y Nueva coordenada vertical.
     */
    public void posicionAsiento(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Obtiene la coordenada horizontal de la posición del asiento.
     *
     * @return La coordenada horizontal.
     */
    public int getX() {
        return this.x;
    }

    /**
     * Obtiene la coordenada vertical de la posición del asiento.
     *
     * @return La coordenada vertical.
     */
    public int getY() {
        return this.y;
    }

    /**
     * Establece el estado de ocupación del asiento.
     *
     * @param ocupado {true} si el asiento está ocupado, de lo contrario {false}.
     */
    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    /**
     * Verifica si el asiento está ocupado.
     *
     * @return {true} si el asiento está ocupado, de lo contrario {false}.
     */
    public boolean isOcupado() {
        return ocupado;
    }

    /**
     * Verifica si el asiento está seleccionado.
     *
     * @return {true} si el asiento está seleccionado, de lo contrario {false}.
     */
    public boolean isSeleccionado() {
        return seleccionado;
    }

    /**
     * Establece el estado de selección del asiento.
     *
     * @param seleccionado {true} para marcar el asiento como seleccionado, de lo contrario {false}.
     */
    public void setSeleccionado(boolean seleccionado) {
        this.seleccionado = seleccionado;
    }


    /**
     * Devuelve una representación en cadena del asiento, incluyendo su número,
     * categoría, precio y estado de reserva.
     *
     * @return Una representación en cadena del asiento.
     */

    @Override
    public String toString() {
        return "Asiento " + numero + " (" + categoria + ", $" + precio + ") - " +
                (estadoReserva ? "Reservado" : "Disponible");
    }
}
