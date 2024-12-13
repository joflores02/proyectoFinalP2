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
     * Convierte un texto a la categoría de asiento correspondiente.
     *
     * @param tipo La cadena que representa el tipo de asiento (por ejemplo, "Semi-Cama" o "Salón-Cama").
     * @return La categoría de asiento correspondiente.
     * @throws IllegalArgumentException Si el tipo de asiento no coincide con una categoría válida.
     */
    public static CategoriaAsiento fromString(String tipo) {
        switch (tipo) {
            case "Semi-Cama":
                return SEMI_CAMA;
            case "Salón-Cama":
                return SALON_CAMA;
            default:
                throw new IllegalArgumentException("Tipo de asiento desconocido: " + tipo);
        }}


    private final int precio;


    /**
     * Constructor para asignar el precio a cada categoría de asiento.
     *
     * @param precio El precio asociado a la categoría de asiento.
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
