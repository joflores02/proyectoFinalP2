package Modelo;

/**
 * Clase que representa un recorrido, definiendo un lugar de inicio y un lugar de destino.
 */
public class Recorrido {
    private String lugarInicio;
    private String lugarFinal;

    /**
     * Constructor para crear un recorrido con un lugar de inicio y un lugar de destino.
     *
     * @param lugarInicio El lugar de inicio del recorrido.
     * @param lugarFinal El lugar de destino del recorrido.
     */
    public Recorrido(String lugarInicio, String lugarFinal) {
        this.lugarInicio = lugarInicio;
        this.lugarFinal = lugarFinal;
    }

    /**
     * Obtiene el lugar de inicio del recorrido.
     *
     * @return Una cadena que representa el lugar de inicio.
     */
    public String getLugarInicio() {
        return lugarInicio;
    }

    /**
     * Obtiene el lugar de destino del recorrido.
     *
     * @return Una cadena que representa el lugar de destino.
     */
    public String getLugarFinal() {
        return lugarFinal;
    }

    /**
     * Devuelve una representación en cadena del recorrido, en el formato "lugarInicio - lugarFinal".
     *
     * @return Una cadena que representa el recorrido.
     */
    @Override
    public String toString() {
        return lugarInicio + " - " + lugarFinal;
    }
}
