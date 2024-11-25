package Modelo;


import java.util.ArrayList;
import java.util.List;

public class Autobus {
    private String id;
    private int numAsientos;
    private String tipo;
    private int numPisos;
    private List<Asiento> primerPiso;
    private List<Asiento> segundoPiso;

    private Autobus(String id, int numAsientos, String tipo, int numPisos) {
        this.id = id;
        this.numAsientos = numAsientos;
        this.tipo = tipo;
        this.numPisos = numPisos;
        this.primerPiso = new ArrayList<>();
        this.segundoPiso = new ArrayList<>();
    }

    public void inicializarAsientos(CategoriaAsiento categoriaPrimerPiso, CategoriaAsiento categoriaSegundoPiso) {
        int asientosPorPiso = numAsientos / numPisos;

        // Inicializar asientos del primer piso
        for (int i = 1; i <= asientosPorPiso; i++) {
            primerPiso.add(new Asiento(i, categoriaPrimerPiso.name(), categoriaPrimerPiso.getPrecio()));
        }

        // Inicializar asientos del segundo piso si corresponde
        if (numPisos == 2) {
            for (int i = 1; i <= asientosPorPiso; i++) {
                segundoPiso.add(new Asiento(i, categoriaSegundoPiso.name(), categoriaSegundoPiso.getPrecio()));
            }
        }
    }


    public String getId() {
        return id;
    }

    public int getNumAsientos() {
        return numAsientos;
    }

    public String getTipo() {
        return tipo;
    }

    public int getNumPisos() {
        return numPisos;
    }

    public List<Asiento> getPrimerPiso() {
        return primerPiso;
    }

    public List<Asiento> getSegundoPiso() {
        return segundoPiso;
    }

    public boolean disponibilidadPrimerPiso() {
        for (Asiento asiento : primerPiso) {
            if (asiento.disponibilidadAsiento()) {
                return true;
            }
        }
        return false;
    }

    public boolean disponibilidadSegundoPiso() {
        if (numPisos == 2) {
            for (Asiento asiento : segundoPiso) {
                if (asiento.disponibilidadAsiento()) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean reservarAsientoPrimerPiso(int numeroAsiento) {

        for (Asiento asiento : primerPiso) {
            if (asiento.getNumero() == numeroAsiento) {
                if (asiento.disponibilidadAsiento()) {
                    asiento.reservar();
                    return true;
                } else {
                    return false;
                }
            }
        }
        throw new IllegalArgumentException("El asiento no existe en el primer piso.");
    }

    public boolean reservarAsientoSegundoPiso(int numeroAsiento) {
        if (numPisos == 1) {
            throw new UnsupportedOperationException("Este autobús no tiene segundo piso.");
        }


        for (Asiento asiento : segundoPiso) {
            if (asiento.getNumero() == numeroAsiento) {
                if (asiento.disponibilidadAsiento()) {
                    asiento.reservar();
                    return true;
                } else {
                    return false;
                }
            }
        }
        throw new IllegalArgumentException("El asiento no existe en el segundo piso.");
    }


    public static class Factory {
        public static Autobus crearAutobus(String id, int numPisos) {
            if (numPisos == 1) {
                // Autobús de 1 piso: todos los asientos semi-cama
                return new Autobus(id, 40, "Económico", 1);
            } else if (numPisos == 2) {
                // Autobús de 2 pisos: semi-cama en el primer piso y salón cama en el segundo
                return new Autobus(id, 60, "Premium", 2);
            } else {
                throw new IllegalArgumentException("Número de pisos no soportado: " + numPisos);
            }
        }
    }
}
