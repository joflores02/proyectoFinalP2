package Modelo;

public class Asiento {
    private int numero;
    private boolean estadoReserva;
    private CategoriaAsiento categoria;
    private double precio;


    public Asiento(int numero, CategoriaAsiento categoria) {
        this.numero = numero;
        this.estadoReserva = false;
        this.categoria = categoria;
        this.precio = categoria.getPrecio();
    }


    public int getNumero() {
        return numero;
    }

    public boolean estadoReserva() {
        return estadoReserva;
    }

    public CategoriaAsiento getCategoria() {
        return categoria;
    }

    public double getPrecio() {
        return precio;
    }


    public boolean disponibilidadAsiento() {
        return !estadoReserva;
    }

    public boolean reservar() {
        if (disponibilidadAsiento()) {
            estadoReserva = true;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Asiento " + numero + " (" + categoria + ", $" + precio + ") - " +
                (estadoReserva ? "Reservado" : "Disponible");
    }
}
