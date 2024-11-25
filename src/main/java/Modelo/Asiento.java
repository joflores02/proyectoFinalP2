package Modelo;

public class Asiento {
    private int numero;
    private boolean estadoReserva;
    private String categoria;
    private double precio;


    public Asiento(int numero, String categoria, double precio) {
        this.numero = numero;
        this.estadoReserva = false;
        this.categoria = categoria;
        this.precio = precio;
    }


    public int getNumero() {
        return numero;
    }

    public boolean estadoReserva() {
        return estadoReserva;
    }

    public String getCategoria() {
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
