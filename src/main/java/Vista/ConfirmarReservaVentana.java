package Vista;

import Modelo.CategoriaAsiento;
import Modelo.Asiento;

import javax.swing.*;
import java.awt.*;

public class ConfirmarReservaVentana extends JDialog {
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
        panelDetalles.setLayout(new GridLayout(3, 1, 5, 5)); // 3 filas para los asientos
        //for asiento in asientosconfirmados
        for (int i = 1; i <= 3; i++) {
            JLabel detalle = new JLabel("Asiento " + i + " - Categoría    $");
            panelDetalles.add(detalle);
        }
        panelPrincipal.add(panelDetalles);

        // Espacio entre el total y los asientos
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 10)));

        // Total
        JLabel total = new JLabel("Total:    $--------");
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