package Modelo;
import Modelo.Asiento;
import Modelo.Horario;
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
    private Horario horario;

    private Autobus(String id, int numAsientos, String tipo, int numPisos, String lugarDeInicio, String lugarDeDestino, Horario horario) {
        this.id = id;
        this.numAsientos = numAsientos;
        this.tipo = tipo;
        this.numPisos = numPisos;
        this.primerPiso = new ArrayList<>();
        this.segundoPiso = new ArrayList<>();
        this.lugarDeInicio = lugarDeInicio;
        this.lugarDeDestino = lugarDeDestino;
        this.horario = horario;

    }

    public void inicializarAsientos(CategoriaAsiento categoriaPrimerPiso, CategoriaAsiento categoriaSegundoPiso) {
        int asientosPorPiso = numAsientos / numPisos;
        inicializarAsientosPorPiso(primerPiso, asientosPorPiso, categoriaPrimerPiso);

        if (numPisos == 2) {
            inicializarAsientosPorPiso(segundoPiso, asientosPorPiso, categoriaSegundoPiso);
        }
    }

    private void inicializarAsientosPorPiso(List<Asiento> piso, int asientosPorPiso, CategoriaAsiento categoria) {
        for (int i = 1; i <= asientosPorPiso; i++) {
            piso.add(new Asiento(i, categoria));
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

    public Horario getHorario(){
        return horario;
    }

    // Métodos para verificar disponibilidad y reservas
    public boolean verificarDisponibilidad(int piso) {
        return (piso == 1 ? primerPiso : segundoPiso).stream().anyMatch(Asiento::disponibilidadAsiento);
    }

    public boolean reservarAsiento(int numeroAsiento, int piso) {
        List<Asiento> asientos = (piso == 1) ? primerPiso : segundoPiso;

        for (Asiento asiento : asientos) {
            if (asiento.getNumero() == numeroAsiento) {
                if (asiento.disponibilidadAsiento()) {
                    asiento.reservar();
                    return true;
                } else {
                    return false;
                }
            }
        }

        throw new IllegalArgumentException("El asiento no existe en el piso " + piso + ".");
    }

    public String getTipoAsiento() {
        // Retorna el tipo de asiento basado en el tipo de autobús y el número de pisos
        if (numPisos == 1) {
            return "Semi-Cama";  // Asientos semi-cama en autobús de un piso
        } else if (numPisos == 2 && tipo.equals("Premium")) {
            return "Salón-Cama";  // Asientos salón-cama en autobús premium de dos pisos
        } else {
            return "Semi-Cama";  // Si es un autobús económico de dos pisos
        }
    }

    // Clase Factory para crear instancias de Autobus

    public static class Factory {
        public static Autobus crearAutobus(String id, int numPisos, String lugarDeInicio, String lugarDeDestino, Horario horario, String categoria) {
            if (numPisos == 1) {
                // Si la categoría es "Premium", se crea un autobús premium de 1 piso
                if (categoria.equals("Premium")) {
                    return new Autobus(id, 38, "Premium", 1, lugarDeInicio, lugarDeDestino, horario);
                } else if (categoria.equals("Económico")) {
                    return new Autobus(id, 38, "Económico", 1, lugarDeInicio, lugarDeDestino, horario);
                } else {
                    throw new IllegalArgumentException("Categoría no soportada para autobuses de 1 piso: " + categoria);
                }
            } else if (numPisos == 2) {
                if (categoria.equals("Económico")) {
                    return new Autobus(id, 76, "Económico", 2, lugarDeInicio, lugarDeDestino, horario);
                } else if (categoria.equals("Premium")) {
                    return new Autobus(id, 76, "Premium", 2, lugarDeInicio, lugarDeDestino, horario);
                } else {
                    throw new IllegalArgumentException("Categoría no soportada para autobuses de 2 pisos: " + categoria);
                }
            } else {
                throw new IllegalArgumentException("Número de pisos no soportado: " + numPisos);
            }
        }
    }

}
