package Modelo;

import java.util.List;

public class Horario {
    //Aquí asignamos día y hora, de partida y llegada de cada bus
    //También, si es que el usuario elige cierto día,
    // deberían aparecer en pantalla todos los buses disponibles ese día (pero eso se ve en interfaz)

    private String horaLlegada;
    private String horaSalida;
    private List<Horario> horarios;

    private String dia;

    public Horario(String horaSalida, String horaLlegada) {
        this.horaSalida = horaSalida;
        this.horaLlegada = horaLlegada;
    }

    public void agregarHorario(Horario horario) {
        horarios.add(horario);
    }

    public List<Horario> getHorarios() {
        return horarios;
    }

    @Override
    public String toString() {
        return horaSalida + " - " + horaLlegada;
    }
}
