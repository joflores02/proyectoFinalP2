package Vista;

import Modelo.Asiento;

import javax.swing.*;
import java.awt.*;

/**
 * Ventana de confirmación de reserva de asientos en el autobús.
 * Esta ventana muestra los detalles de la compra, incluyendo los asientos reservados,
 * el precio de cada uno y el total de la compra. También incluye un botón para confirmar
 * la reserva.
 *
 */
public class ConfirmarReservaVentana extends JDialog {
    /**
     * Valor total de la reserva.
     */
    double valor_total = 0.0;

    /**
     * Constructor que inicializa la ventana de confirmación de reserva.
     *
     * @param parent El marco principal de la aplicación.
     */
    public ConfirmarReservaVentana(JFrame parent) {
        super(parent, "Confirmar Reserva", true);
        setSize(400, 300);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        // Panel principal
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Márgenes

        // Título
        JLabel titulo = new JLabel("Detalles Compra:");
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        panelPrincipal.add(titulo);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 10))); // Espacio entre elementos

        // Panel para los detalles de los asientos (en base a asientos reservados)
        JPanel panelDetalles = new JPanel();
        panelDetalles.setLayout(new GridLayout(SeleccionAsientosVentana.asientosReservados.size(), 1, 5, 5)); // Una fila por cada asiento
        for (Asiento asiento : SeleccionAsientosVentana.asientosReservados) {
            JLabel detalle = new JLabel("Asiento " + asiento.getNumero() + " " + asiento.getCategoria() + "    " + asiento.getPrecio());
            valor_total += asiento.getPrecio();
            panelDetalles.add(detalle);
        }

        panelPrincipal.add(panelDetalles);

        // Espacio entre el total y los asientos
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 10)));

        // Total
        JLabel total = new JLabel("Total:    $" + valor_total);
        total.setFont(new Font("Arial", Font.BOLD, 14));
        panelPrincipal.add(total);

        // Espacio antes del botón
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 20)));

        // Botón Confirmar
        JButton botonConfirmar = new JButton("CONFIRMAR");
        botonConfirmar.setBackground(new Color(180, 167, 232)); // Color lila
        botonConfirmar.setForeground(Color.BLACK);
        botonConfirmar.setFocusPainted(false);
        botonConfirmar.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Reserva confirmada!");
            dispose(); // Cerrar el diálogo
        });

        panelPrincipal.add(botonConfirmar);

        // Agregar el panel principal al diálogo
        add(panelPrincipal, BorderLayout.CENTER);
    }

    /**
     * Método principal para mostrar la ventana de confirmación de reserva.
     *
     * @param args Argumentos de línea de comandos.
     */
    public static void main(String[] args) {
        // Crear el frame principal
        JFrame mainFrame = new JFrame();
        mainFrame.setSize(400, 300);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);

        // Crear y mostrar el diálogo
        ConfirmarReservaVentana dialogo = new ConfirmarReservaVentana(mainFrame);
        dialogo.setVisible(true);
    }
}
