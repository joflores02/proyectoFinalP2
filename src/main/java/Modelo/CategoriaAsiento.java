package Modelo;

public enum CategoriaAsiento {
    SEMI_CAMA(16800),
    SALON_CAMA(21500);

    private final int precio;

    CategoriaAsiento(int precio) {
        this.precio = precio;
    }

    public int getPrecio() {
        return precio;
    }
}
