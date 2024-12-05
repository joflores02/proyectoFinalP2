package Controlador;

import Modelo.Horario;
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

        // Pasamos estas listas de datos a la ventana de selección de autobús para poder seleccionar los lugares
        ventanaSeleccionAutobus.setLugaresInicio(lugaresInicio);
        ventanaSeleccionAutobus.setDestinos(lugaresDestino);

        // Crear horarios
        Horario horario1 = new Horario("08:00 AM", "12:00 PM");
        Horario horario2 = new Horario("02:00 PM", "06:00 PM");

        // Crear autobuses
        Autobus autobus1 = Autobus.Factory.crearAutobus("A1", 1, "Concepción", "Santiago", horario1);
        Autobus autobus2 = Autobus.Factory.crearAutobus("A2", 1, "Concepción", "Chillán", horario1);
        Autobus autobus3 = Autobus.Factory.crearAutobus("A3", 1, "Concepción", "Los Ángeles", horario2);

        // Inicializamos la lista de autobuses
        autobuses = new ArrayList<>();

        //Manejar la navegación entre dos ventanas
        //Se aprieta el botón "Ingresar" y se cambia a la vista para seleccionar el recorrido del bus
        ventanaInicial.getBotonIngresar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cambiarASeleccionAutobus();
            }
        });
        //Se aprieta el botón "Atras" y se devuelve a la ventana inicial.
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
    private void cambiarASeleccionAutobus() {
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
