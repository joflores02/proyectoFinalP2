package Modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Representa un autobús con un conjunto de asientos organizados por pisos.
 * Cada autobús tiene un identificador, número de asientos, tipo (económico o premium),
 * número de pisos, lugar de inicio, lugar de destino y horario.
 * Los asientos del autobús se organizan en una matriz donde cada piso tiene filas y columnas de asientos.
 */
public class Autobus implements Iterable<Asiento> {
    private Asiento[][][] asientos;
    private String id;
    private int numAsientos;
    private String tipo;
    private int numPisos;
    private String lugarDeInicio;
    private String lugarDeDestino;
    private Horario horario;

    /**
     * Crea un nuevo autobús con los parámetros especificados.
     *
     * @param id El identificador del autobús.
     * @param numAsientos El número total de asientos del autobús.
     * @param tipo El tipo de autobús.
     * @param numPisos El número de pisos del autobús.
     * @param lugarDeInicio El lugar de inicio del autobús.
     * @param lugarDeDestino El lugar de destino del autobús.
     * @param horario El horario de salida y llegada del autobús.
     * @param filasPorPiso El número de filas de asientos por piso.
     * @param columnasPorPiso El número de columnas de asientos por piso.
     */
    public Autobus(String id, int numAsientos, String tipo, int numPisos, String lugarDeInicio, String lugarDeDestino, Horario horario, int filasPorPiso, int columnasPorPiso) {
        this.id = id;
        this.numAsientos = numAsientos;
        this.tipo = tipo;
        this.numPisos = numPisos;
        this.lugarDeInicio = lugarDeInicio;
        this.lugarDeDestino = lugarDeDestino;
        this.horario = horario;
        this.asientos = new Asiento[numPisos][filasPorPiso][columnasPorPiso];

        CategoriaAsiento categoriaAsiento = (tipo.equals("Económico")) ? CategoriaAsiento.SEMI_CAMA : CategoriaAsiento.SALON_CAMA;

        for (int piso = 0; piso < numPisos; piso++) {
            for (int fila = 0; fila < filasPorPiso; fila++) {
                for (int columna = 0; columna < columnasPorPiso; columna++) {
                    asientos[piso][fila][columna] = new Asiento(fila, columna, categoriaAsiento);
                }
            }
        }
    }

    /**
     * Obtiene el identificador del autobús.
     * @return El identificador del autobús.
     */
    public String getId() {
        return id;
    }

    /**
     * Establece el identificador del autobús.
     *
     * @param id El nuevo identificador del autobús.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtiene el número total de asientos en el autobús.
     *
     * @return El número de asientos en el autobús.
     */
    public int getNumAsientos() {
        return numAsientos;
    }

    /**
     * Establece el número total de asientos en el autobús.
     *
     * @param numAsientos El nuevo número de asientos en el autobús.
     */
    public void setNumAsientos(int numAsientos) {
        this.numAsientos = numAsientos;
    }

    /**
     * Obtiene el tipo de autobús (económico o premium).
     *
     * @return El tipo de autobús.
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Establece el tipo de autobús.
     *
     * @param tipo El nuevo tipo de autobús.
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Obtiene el número de pisos del autobús.
     *
     * @return El número de pisos del autobús.
     */
    public int getNumPisos() {
        return numPisos;
    }

    /**
     * Establece el número de pisos del autobús.
     *
     * @param numPisos El nuevo número de pisos del autobús.
     */
    public void setNumPisos(int numPisos) {
        this.numPisos = numPisos;
    }

    /**
     * Obtiene el lugar de inicio del autobús.
     *
     * @return El lugar de inicio del autobús.
     */
    public String getLugarDeInicio() {
        return lugarDeInicio;
    }

    /**
     * Establece el lugar de inicio del autobús.
     *
     * @param lugarDeInicio El nuevo lugar de inicio del autobús.
     */
    public void setLugarDeInicio(String lugarDeInicio) {
        this.lugarDeInicio = lugarDeInicio;
    }

    /**
     * Obtiene el lugar de destino del autobús.
     *
     * @return El lugar de destino del autobús.
     */
    public String getLugarDeDestino() {
        return lugarDeDestino;
    }

    /**
     * Establece el lugar de destino del autobús.
     *
     * @param lugarDeDestino El nuevo lugar de destino del autobús.
     */
    public void setLugarDeDestino(String lugarDeDestino) {
        this.lugarDeDestino = lugarDeDestino;
    }

    /**
     * Obtiene el horario del autobús.
     *
     * @return El horario del autobús.
     */
    public Horario getHorario() {
        return horario;
    }

    /**
     * Establece el horario del autobús.
     *
     * @param horario El nuevo horario del autobús.
     */
    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    /**
     * Obtiene una lista de todos los asientos del autobús.
     *
     * @return Una lista de todos los asientos del autobús.
     */
    public List<Asiento> getAsientos() {
        List<Asiento> listaAsientos = new ArrayList<>();
        for (Asiento asiento : this) {
            listaAsientos.add(asiento);
        }
        return listaAsientos;
    }

    /**
     * Obtiene una lista de los asientos de un piso específico.
     *
     * @param piso El número de piso para obtener los asientos.
     * @return Una lista de los asientos del piso especificado.
     */
    public List<Asiento> getAsientosPorPiso(int piso) {
        List<Asiento> listaAsientos = new ArrayList<>();
        if (piso >= 0 && piso < numPisos) {
            for (int fila = 0; fila < asientos[piso].length; fila++) {
                for (int columna = 0; columna < asientos[piso][fila].length; columna++) {
                    listaAsientos.add(asientos[piso][fila][columna]);
                }
            }
        }
        return listaAsientos;
    }

    /**
     * Obtiene un asiento específico según su piso, fila y columna.
     *
     * @param piso El número de piso.
     * @param fila El número de fila.
     * @param columna El número de columna.
     * @return El asiento especificado, o null si no existe.
     */
    public Asiento obtenerAsiento(int piso, int fila, int columna) {
        if (piso >= 0 && piso < numPisos && fila >= 0 && fila < asientos[piso].length && columna >= 0 && columna < asientos[piso][fila].length) {
            return asientos[piso][fila][columna];
        }
        return null;
    }

    /**
     * Obtiene el precio de un asiento según el tipo de autobús.
     *
     * @return El precio del asiento en el autobús.
     */
    public int obtenerPrecio() {
        return (tipo.equals("Económico")) ? CategoriaAsiento.SEMI_CAMA.getPrecio() : CategoriaAsiento.SALON_CAMA.getPrecio();
    }

    /**
     * Obtiene una lista de los asientos seleccionados por el usuario.
     *
     * @return Una lista de los asientos seleccionados.
     */
    public List<Asiento> obtenerAsientosSeleccionados() {
        List<Asiento> asientosSeleccionados = new ArrayList<>();
        for (Asiento asiento : this) {
            if (asiento.isSeleccionado()) {
                asientosSeleccionados.add(asiento);
            }
        }
        return asientosSeleccionados;
    }

    /**
     * Iterador para recorrer los asientos del autobús.
     *
     * @return Un iterador para los asientos del autobús.
     */
    @Override
    public Iterator<Asiento> iterator() {
        return new AsientoIterator();
    }

    private class AsientoIterator implements Iterator<Asiento> {
        private int piso = 0;
        private int fila = 0;
        private int columna = 0;

        @Override
        public boolean hasNext() {
            return piso < numPisos && fila < asientos[piso].length && columna < asientos[piso][fila].length;
        }

        @Override
        public Asiento next() {
            Asiento asiento = asientos[piso][fila][columna];
            columna++;
            if (columna == asientos[piso][fila].length) {
                columna = 0;
                fila++;
                if (fila == asientos[piso].length) {
                    fila = 0;
                    piso++;
                }
            }
            return asiento;
        }
    }
}
