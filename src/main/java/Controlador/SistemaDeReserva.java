package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

import Modelo.Autobus;
import Modelo.AutobusFactory;
import Modelo.Horario;
import Vista.SeleccionAutobusVentana;
import Vista.VentanaIngresoSistema;

/**
 * Esta clase representa el sistema de reserva que gestiona la interacción con el usuario para seleccionar autobuses,
 * así como las ventanas de inicio y selección de autobús.
 */
public class SistemaDeReserva {

    private JFrame frame;
    private VentanaIngresoSistema ventanaInicial;
    private SeleccionAutobusVentana ventanaSeleccionAutobus;
    private List<String> lugaresInicio;
    private List<String> lugaresDestino;
    private List<Autobus> autobuses;

    /**
     * Constructor de la clase SistemaDeReserva que inicializa las ventanas y configura los datos
     * de los lugares de inicio, destinos y autobuses disponibles.
     *
     * @param frame El marco de la ventana principal del sistema.
     */
    public SistemaDeReserva(JFrame frame) {
        this.frame = frame;
        this.ventanaInicial = new VentanaIngresoSistema();
        this.ventanaSeleccionAutobus = new SeleccionAutobusVentana();

        lugaresInicio = new ArrayList<>();
        lugaresDestino = new ArrayList<>();

        lugaresInicio.add("Concepción");
        lugaresDestino.add("Los Ángeles");
        lugaresDestino.add("Santiago");
        lugaresDestino.add("Chillán");

        ventanaSeleccionAutobus.setLugaresInicio(lugaresInicio);
        ventanaSeleccionAutobus.setDestinos(lugaresDestino);

        Horario horario1 = new Horario("08:00 AM", "11:30 PM");
        Horario horario2 = new Horario("02:00 PM", "08:30 PM");
        Horario horario3 = new Horario("09:00 AM", "11:00 PM");
        Horario horario4 = new Horario("10:00 AM", "06:30 PM");

        Autobus autobus1 = AutobusFactory.crearAutobus("A1", "Económico", 1, "Concepción", "Santiago", horario2);
        Autobus autobus2 = AutobusFactory.crearAutobus("B1", "Económico", 2, "Concepción", "Santiago", horario1);
        Autobus autobus3 = AutobusFactory.crearAutobus("A2", "Económico", 1, "Concepción", "Santiago", horario3);
        Autobus autobus4 = AutobusFactory.crearAutobus("A3", "Premium", 1, "Concepción", "Santiago", horario4);
        Autobus autobus5 = AutobusFactory.crearAutobus("A4", "Económico", 1, "Concepción", "Chillán", horario1);
        Autobus autobus6 = AutobusFactory.crearAutobus("A5", "Económico", 1, "Concepción", "Los Ángeles", horario3);
        Autobus autobus7 = AutobusFactory.crearAutobus("B2", "Premium", 2, "Concepción", "Santiago", horario4);
        Autobus autobus8 = AutobusFactory.crearAutobus("A6", "Premium", 1, "Concepción", "Chillán", horario3);
        Autobus autobus9 = AutobusFactory.crearAutobus("B3", "Económico", 2, "Concepción", "Los Ángeles", horario1);

        autobuses = new ArrayList<>();

        autobuses.add(autobus1);
        autobuses.add(autobus2);
        autobuses.add(autobus3);
        autobuses.add(autobus4);
        autobuses.add(autobus5);
        autobuses.add(autobus6);
        autobuses.add(autobus7);
        autobuses.add(autobus8);
        autobuses.add(autobus9);

        ventanaSeleccionAutobus.setAutobuses(autobuses);

        ventanaInicial.getBotonIngresar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cambiarASeleccionAutobus();
            }
        });

        ventanaSeleccionAutobus.getIrAtras().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cambiarAVentanaInicial();
            }
        });

        frame.setContentPane(ventanaInicial);
        frame.revalidate();
        frame.repaint();
    }

    /**
     * Cambia la vista actual a la ventana de inicio.
     */
    public void cambiarAVentanaInicial() {
        frame.setContentPane(ventanaInicial);
        frame.revalidate();
        frame.repaint();
    }

    /**
     * Cambia la vista actual a la ventana de selección de autobuses.
     */
    private void cambiarASeleccionAutobus() {
        frame.setContentPane(ventanaSeleccionAutobus);
        frame.revalidate();
        frame.repaint();
    }

    /**
     * Método principal que inicia el sistema de reserva, creando la ventana principal y mostrando el sistema.
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Sistema de Reserva");
        frame.setSize(1120, 652);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        new SistemaDeReserva(frame);
        frame.setVisible(true);
    }
}
