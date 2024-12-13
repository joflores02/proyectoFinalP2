package Modelo;

import Modelo.Asiento;
import Modelo.Horario;
import java.util.ArrayList;
import java.util.List;

/**
 * La clase Autobus representa un autobús con varios pisos y asientos.
 * Permite gestionar la reserva de asientos, la visualización de los asientos disponibles
 * y las rutas entre dos lugares.
 */
public class Autobus {

    private String id;
    private int numAsientos;
    private String tipo;
    private int numPisos;
    private List<Asiento> primerPiso;
    private List<Asiento> segundoPiso;
    private List<Asiento> asientosReservados;
    private List<Asiento> asientos;
    private String lugarDeInicio;  // Lugar de partida
    private String lugarDeDestino; // Destino
    private Horario horario;

    /**
     * Constructor privado para crear una nueva instancia de Autobus.
     * @param id El identificador único del autobús.
     * @param numAsientos El número total de asientos en el autobús.
     * @param tipo El tipo de autobús (Económico o Premium).
     * @param numPisos El número de pisos que tiene el autobús.
     * @param lugarDeInicio El lugar de inicio del recorrido.
     * @param lugarDeDestino El lugar de destino del recorrido.
     * @param horario El horario de salida del autobús.
     */
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

    /**
     * Inicializa los asientos del autobús para cada piso, permitiendo asignar diferentes categorías de asiento
     * y especificando las coordenadas para su ubicación.
     * @param categoriaPrimerPiso La categoría de los asientos en el primer piso (Semi-Cama o Salón-Cama).
     * @param categoriaSegundoPiso La categoría de los asientos en el segundo piso (Semi-Cama o Salón-Cama).
     * @param filas El número de filas de asientos en cada piso.
     * @param columnas El número de columnas de asientos en cada piso.
     * @param inicioX Coordenada X inicial para la ubicación de los asientos.
     * @param inicioY Coordenada Y inicial para la ubicación de los asientos.
     * @param ancho El ancho de cada asiento.
     * @param alto El alto de cada asiento.
     * @param espacio El espacio entre los asientos.
     */
    public void inicializarAsientos(CategoriaAsiento categoriaPrimerPiso, CategoriaAsiento categoriaSegundoPiso, int filas, int columnas, int inicioX, int inicioY, int ancho, int alto, int espacio) {
        int asientosPorPiso = numAsientos / numPisos;

        inicializarAsientosPorPiso(primerPiso, asientosPorPiso, filas, columnas, inicioX, inicioY, ancho, alto, espacio, categoriaPrimerPiso);

        if (numPisos == 2) {
            inicializarAsientosPorPiso(segundoPiso, asientosPorPiso, filas, columnas, inicioX, inicioY, ancho, alto, espacio, categoriaSegundoPiso);
        }
    }

    /**
     * Inicializa los asientos de un solo piso del autobús, asignando un número de asiento y ubicaciones específicas.
     * @param piso La lista de asientos para el piso (primer o segundo).
     * @param asientosPorPiso El número de asientos para este piso.
     * @param filas El número de filas de asientos.
     * @param columnas El número de columnas de asientos.
     * @param inicioX La coordenada X inicial para la ubicación de los asientos.
     * @param inicioY La coordenada Y inicial para la ubicación de los asientos.
     * @param ancho El ancho de cada asiento.
     * @param alto El alto de cada asiento.
     * @param espacio El espacio entre los asientos.
     * @param categoria La categoría de asiento (Semi-Cama o Salón-Cama).
     */
    private void inicializarAsientosPorPiso(List<Asiento> piso, int asientosPorPiso, int filas, int columnas, int inicioX, int inicioY, int ancho, int alto, int espacio, CategoriaAsiento categoria) {
        int numeroAsiento = 1;

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (numeroAsiento > asientosPorPiso) {
                    return; // Detener si se alcanzó el número total de asientos para este piso
                }

                int x = inicioX + j * (ancho + espacio);
                int y = inicioY + i * (alto + espacio);

                // Crear y agregar el asiento
                piso.add(new Asiento(numeroAsiento, categoria, x, y));
                numeroAsiento++;
            }
        }
        asientos.addAll(piso);
        if (numPisos == 2) {
            asientos.addAll(segundoPiso);
        }
    }


    /**
     * Obtiene el ID del autobús.
     * @return El ID del autobús.
     */
    public String getId() {
        return id;
    }

    /**
     * Obtiene el número total de asientos en el autobús.
     * @return El número de asientos.
     */
    public int getNumAsientos() {
        return numAsientos;
    }

    /**
     * Obtiene el tipo de autobús (Económico o Premium).
     * @return El tipo de autobús.
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Obtiene el número de pisos del autobús.
     * @return El número de pisos.
     */
    public int getNumPisos() {
        return numPisos;
    }

    /**
     * Obtiene la lista de asientos del primer piso.
     * @return La lista de asientos del primer piso.
     */
    public List<Asiento> getPrimerPiso() {
        return primerPiso;
    }

    /**
     * Obtiene la lista de asientos del segundo piso.
     * @return La lista de asientos del segundo piso.
     */
    public List<Asiento> getSegundoPiso() {
        return segundoPiso;
    }

    /**
     * Obtiene la lista de todos los asientos del autobús.
     * @return La lista de todos los asientos.
     */
    public List<Asiento> getAsientos() {
        return asientos;
    }

    /**
     * Obtiene el lugar de inicio del autobús.
     * @return El lugar de inicio.
     */
    public String getLugarDeInicio() {
        return lugarDeInicio;
    }

    /**
     * Obtiene el lugar de destino del autobús.
     * @return El lugar de destino.
     */
    public String getLugarDeDestino() {
        return lugarDeDestino;
    }

    /**
     * Obtiene el horario del autobús.
     * @return El horario del autobús.
     */
    public Horario getHorario() {
        return horario;
    }

    // Métodos para verificar disponibilidad y reservas

    /**
     * Verifica si hay asientos disponibles en el piso indicado.
     * @param piso El número de piso (1 o 2).
     * @return true si hay asientos disponibles, false en caso contrario.
     */
    public boolean verificarDisponibilidad(int piso) {
        return (piso == 1 ? primerPiso : segundoPiso).stream().anyMatch(Asiento::disponibilidadAsiento);
    }

    /**
     * Reserva un asiento específico en el piso indicado.
     * @param numeroAsiento El número del asiento a reservar.
     * @param piso El número de piso (1 o 2).
     * @return true si la reserva fue exitosa, false si el asiento ya está ocupado.
     * @throws IllegalArgumentException Si el asiento no existe en el piso indicado.
     */
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

    /**
     * Obtiene el tipo de asiento disponible según el tipo de autobús y número de pisos.
     * @return El tipo de asiento (Semi-Cama o Salón-Cama).
     */
    public String getTipoAsiento() {
        // Retorna el tipo de asiento basado en el tipo de autobús y el número de pisos
        if (numPisos == 1) {
            return "Semi-Cama"; // Para un piso, asientos semi-cama
        } else {
            return tipo.equals("Económico") ? "Semi-Cama" : "Salón-Cama"; // Dependiendo del tipo de autobús
        }
    }

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
