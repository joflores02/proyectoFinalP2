package Modelo;

public enum CategoriaAsiento {
    SEMI_CAMA(16800),
    SALON_CAMA(21500);

    // Método estático para convertir desde un String
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

    CategoriaAsiento(int precio) {
        this.precio = precio;
    }

    public int getPrecio() {
        return precio;
    }
}
