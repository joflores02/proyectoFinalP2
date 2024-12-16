package Modelo;

/**
 * Enumeración que representa las diferentes categorías de asientos disponibles en el sistema de reserva.
 * Cada categoría tiene un precio asociado.
 */
public enum CategoriaAsiento {
    /**
     * Categoría Semi-Cama, con un precio de 16,800 CLP.
     */
    SEMI_CAMA(16800),
    /**
     * Categoría Salón-Cama, con un precio de 21,500 CLP.
     */
    SALON_CAMA(21500);

    /**
     * El precio asociado a la categoría de asiento.
     */
    private final int precio;

    /**
     * Constructor del enum CategoriaAsiento que asigna el precio a cada categoría.
     * @param precio El precio del asiento correspondiente a la categoría.
     */
    CategoriaAsiento(int precio) {
        this.precio = precio;
    }

    /**
     * Obtiene el precio asociado a la categoría de asiento.
     *
     * @return El precio de la categoría de asiento.
     */
    public int getPrecio() {
        return precio;
    }
}
