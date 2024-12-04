package Controlador;

import Vista.SeleccionAutobusVentana;
import Vista.VentanaIngresoSistema;
import Modelo.Recorrido;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import Modelo.Autobus;

public class SistemaDeReserva {
    private JFrame frame;
    private VentanaIngresoSistema ventanaInicial;
    private SeleccionAutobusVentana ventanaSeleccionAutobus;
    private List<String> lugaresInicio;
    private List<String> lugaresDestino;
    private List<Autobus> autobuses;

    public SistemaDeReserva(JFrame frame) {
        this.frame = frame;
        this.ventanaInicial = new VentanaIngresoSistema();
        this.ventanaSeleccionAutobus = new SeleccionAutobusVentana();


        // Inicializamos las listas de lugares de inicio y destino
        lugaresInicio = new ArrayList<>();
        lugaresDestino = new ArrayList<>();

        //Datos para los lugares disponibles
        lugaresInicio.add("Concepción");
        lugaresDestino.add("Los Ángeles");
        lugaresDestino.add("Santiago");
        lugaresDestino.add("Chillán");

        // Inicializamos la lista de autobuses
        autobuses = new ArrayList<>();

        // Crear autobuses
        Autobus autobus1 = Autobus.Factory.crearAutobus("A1", 1, "Concepción", "Santiago");
        Autobus autobus2 = Autobus.Factory.crearAutobus("A2", 1, "Concepción", "Chillán");
        Autobus autobus3 = Autobus.Factory.crearAutobus("A3", 1, "Concepción", "Los Ángeles");


        // Pasamos estas listas de datos a la ventana de selección de autobús para poder seleccionar los lugares
        ventanaSeleccionAutobus.setLugaresInicio(lugaresInicio);
        ventanaSeleccionAutobus.setDestinos(lugaresDestino);

        ventanaInicial.getBotonIngresar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cambiarASeleccionAsientos();
            }
        });

        ventanaSeleccionAutobus.getIrAtras().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cambiarAVentanaInicial();
            }
        });

        //Muestra la ventana principal al iniciar
        frame.setContentPane(ventanaInicial);
        frame.revalidate();
        frame.repaint();
    }

    // Método para cambiar a la ventana de ingreso
    public void cambiarAVentanaInicial() {
        frame.setContentPane(ventanaInicial);
        frame.revalidate();
        frame.repaint();
    }

    //Método para avanzar a selección de asientos
    private void cambiarASeleccionAsientos() {
        frame.setContentPane(ventanaSeleccionAutobus);
        frame.revalidate();
        frame.repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Sistema de Reserva");
        frame.setSize(1120, 652);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        new SistemaDeReserva(frame);
        frame.setVisible(true);
    }
}
