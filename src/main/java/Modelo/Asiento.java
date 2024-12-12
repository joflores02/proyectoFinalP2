package Modelo;

public class Asiento {
    private int numero;
    private boolean estadoReserva;
    private boolean ocupado;
    private boolean seleccionado;
    private CategoriaAsiento categoria;
    private double precio;
    private int x;
    private int y;


    public Asiento(int numero, CategoriaAsiento categoria, int x, int y) {
        this.numero = numero;
        this.estadoReserva = false;
        this.categoria = categoria;
        this.precio = categoria.getPrecio();
        this.x = x;
        this.y = y;
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

    // Método para asignar o actualizar la posición
    public void posicionAsiento(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Método para obtener las coordenadas
    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    public boolean isOcupado() {
        if (estadoReserva) {
            return ocupado;
        }
        return false;
    }

    public boolean isSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(boolean seleccionado) {
        this.seleccionado = seleccionado;
    }

    @Override
    public String toString() {
        return "Asiento " + numero + " (" + categoria + ", $" + precio + ") - " +
                (estadoReserva ? "Reservado" : "Disponible");
    }
}
