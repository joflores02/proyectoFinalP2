package Controlador;

import Vista.SeleccionAutobusVentana;
import Vista.VentanaIngresoSistema;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class SistemaDeReserva {
    private JFrame frame;
    private VentanaIngresoSistema ventanaInicial;
    private SeleccionAutobusVentana ventanaSeleccionAutobus;

    public SistemaDeReserva(JFrame frame) {
        this.frame = frame;
        this.ventanaInicial = new VentanaIngresoSistema();
        this.ventanaSeleccionAutobus = new SeleccionAutobusVentana();

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
