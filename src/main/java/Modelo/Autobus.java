package Modelo;
import Modelo.Asiento;

import java.util.ArrayList;
import java.util.List;

public class Autobus {
    private String id;
    private int numAsientos;
    private String tipo;
    private int numPisos;
    private List<Asiento> primerPiso;
    private List<Asiento> segundoPiso;
    private String lugarDeInicio;  // Lugar de partida
    private String lugarDeDestino; // Destino

    private Autobus(String id, int numAsientos, String tipo, int numPisos, String lugarDeInicio, String lugarDeDestino) {
        this.id = id;
        this.numAsientos = numAsientos;
        this.tipo = tipo;
        this.numPisos = numPisos;
        this.primerPiso = new ArrayList<>();
        this.segundoPiso = new ArrayList<>();
        this.lugarDeInicio = lugarDeInicio;
        this.lugarDeDestino = lugarDeDestino;
    }

    public void inicializarAsientos(CategoriaAsiento categoriaPrimerPiso, CategoriaAsiento categoriaSegundoPiso) {
        int asientosPorPiso = numAsientos / numPisos;

        // Inicializar asientos del primer piso
        for (int i = 1; i <= asientosPorPiso; i++) {
            primerPiso.add(new Asiento(i, categoriaPrimerPiso));
        }

        // Inicializar asientos del segundo piso si corresponde
        if (numPisos == 2) {
            for (int i = 1; i <= asientosPorPiso; i++) {
                segundoPiso.add(new Asiento(i, categoriaSegundoPiso));
            }
        }
    }

    // Métodos para obtener información del autobús
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

    public String getLugarDeInicio() {
        return lugarDeInicio;
    }

    public String getLugarDeDestino() {
        return lugarDeDestino;
    }

    // Métodos para verificar disponibilidad y reservar asientos
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
    //herencia por cada tipo de autobus(que pasa si tengo un bus de 1 piso?)
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



    // Clase Factory para crear instancias de Autobus
main
    public static class Factory {
        public static Autobus crearAutobus(String id, int numPisos, String lugarDeInicio, String lugarDeDestino) {
            if (numPisos == 1) {
                return new Autobus(id, 40, "Económico", 1, lugarDeInicio, lugarDeDestino);
            } else if (numPisos == 2) {
                return new Autobus(id, 60, "Premium", 2, lugarDeInicio, lugarDeDestino);
            } else {
                throw new IllegalArgumentException("Número de pisos no soportado: " + numPisos);
            }
        }
    }
}
