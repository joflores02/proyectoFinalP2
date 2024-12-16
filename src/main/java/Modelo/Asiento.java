package Modelo;

/**
 * Clase que representa un asiento en el autobús. Cada asiento tiene una fila, columna, piso,
 * y un estado que indica si está disponible, seleccionado o reservado. Además, cada asiento
 * tiene una categoría que determina el tipo de asiento (por ejemplo, salón-cama o semi-cama).
 */
public class Asiento {

    private int fila;
    private int columna;
    private int piso;
    private boolean disponible;
    private boolean seleccionado;
    private boolean reservado;
    private CategoriaAsiento categoriaAsiento;

    /**
     * Constructor de la clase Asiento.
     *
     * @param fila La fila en la que se encuentra el asiento.
     * @param columna La columna en la que se encuentra el asiento.
     * @param categoriaAsiento La categoría del asiento (salón-cama, semi-cama).
     */
    public Asiento(int fila, int columna, CategoriaAsiento categoriaAsiento) {
        this.fila = fila;
        this.columna = columna;
        this.piso = piso;
        this.disponible = true;
        this.seleccionado = false;
        this.reservado = false;
        this.categoriaAsiento = categoriaAsiento;
    }

    /**
     * Verifica si el asiento está disponible.
     *
     * @return true si el asiento está disponible, false si está reservado.
     */
    public boolean isDisponible() {
        return disponible;
    }

    /**
     * Verifica si el asiento está seleccionado.
     *
     * @return true si el asiento está seleccionado, false si no lo está.
     */
    public boolean isSeleccionado() {
        return seleccionado;
    }

    /**
     * Verifica si el asiento está reservado.
     *
     * @return true si el asiento está reservado, false si no lo está.
     */
    public boolean isReservado() {
        return reservado;
    }

    /**
     * Obtiene la fila en la que se encuentra el asiento.
     *
     * @return El número de fila del asiento.
     */
    public int getFila() {
        return fila;
    }

    /**
     * Obtiene la columna en la que se encuentra el asiento.
     *
     * @return El número de columna del asiento.
     */
    public int getColumna() {
        return columna;
    }

    /**
     * Obtiene el piso en el que se encuentra el asiento.
     *
     * @return El número de piso del asiento.
     */
    public int getPiso() {
        return piso;
    }

    /**
     * Marca el asiento como seleccionado si está disponible y no ha sido reservado.
     */
    public void seleccionar() {
        if (disponible && !reservado) {
            seleccionado = true;
        }
    }

    /**
     * Deselecciona el asiento.
     */
    public void deseleccionar() {
        seleccionado = false;
    }

    /**
     * Reserva el asiento si está disponible y no ha sido reservado.
     * Cambia el estado del asiento a no disponible y no seleccionado.
     */
    public void reservar() {
        if (disponible && !reservado) {
            reservado = true;
            disponible = false;
            seleccionado = false;
        }
    }

    /**
     * Obtiene la categoría del asiento (salón-cama, semi-cama).
     *
     * @return La categoría del asiento.
     */
    public CategoriaAsiento getCategoriaAsiento() {
        return categoriaAsiento;
    }

    /**
     * Representación en formato de cadena de caracteres del asiento, con información sobre su piso,
     * fila, columna, disponibilidad, selección y reserva.
     *
     * @return Una cadena con la información del asiento.
     */
    @Override
    public String toString() {
        return "Asiento [Piso: " + piso + ", Fila: " + fila + ", Columna: " + columna +
                ", Disponible: " + disponible + ", Seleccionado: " + seleccionado +
                ", Reservado: " + reservado + "]";
    }
}
