package Modelo;
import java.util.ArrayList;
import java.util.List;

public class Recorrido {
    private String lugarInicio;
    private String lugarFinal;


    public Recorrido(String lugarInicio, String lugarFinal) {
        this.lugarInicio = lugarInicio;
        this.lugarFinal = lugarFinal;
    }


    public String getLugarInicio() {
        return lugarInicio;
    }

    public String getLugarFinal() {
        return lugarFinal;
    }

    @Override
    public String toString() {
        return lugarInicio + " - " + lugarFinal;
    }
}
