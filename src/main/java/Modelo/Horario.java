package Modelo;

import java.util.List;

/**
 * Clase que representa un horario, incluyendo la hora de salida y llegada de un autobús,
 * así como una lista de horarios disponibles.
 */
public class Horario {
    private String horaLlegada;
    private String horaSalida;
    private List<Horario> horarios;
    private String dia;

    /**
     * Constructor para crear un horario con la hora de salida y llegada.
     *
     * @param horaSalida La hora de salida del autobús.
     * @param horaLlegada La hora de llegada del autobús.
     */
    public Horario(String horaSalida, String horaLlegada) {
        this.horaSalida = horaSalida;
        this.horaLlegada = horaLlegada;
    }

    /**
     * Agrega un horario a la lista de horarios disponibles.
     *
     * @param horario El horario a agregar.
     */
    public void agregarHorario(Horario horario) {
        horarios.add(horario);
    }

    /**
     * Obtiene la lista de horarios disponibles.
     *
     * @return Una lista de horarios.
     */
    public List<Horario> getHorarios() {
        return horarios;
    }

    /**
     * Devuelve una representación en cadena del horario, incluyendo la hora de salida y llegada.
     *
     * @return Una cadena que representa el horario en el formato "horaSalida - horaLlegada".
     */
    @Override
    public String toString() {
        return horaSalida + " - " + horaLlegada;
    }
}
