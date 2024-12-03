package Modelo;public class Horario {
    //Aquí asignamos día y hora, de partida y llegada de cada bus
    //También, si es que el usuario elige cierto día,
    // deberían aparecer en pantalla todos los buses disponibles ese día (pero eso se ve en interfaz)

    private int dia;
    private int horas;
    private int minutos;


    public class Dia{}
    public void Hora(int horas, int minutos){
        this.minutos = minutos;
    }


    public void agregarHorario(Horario horario) {
        horarios.add(horario);
    }

    public List<Horario> getHorarios() {
        return horarios;
    }

}
