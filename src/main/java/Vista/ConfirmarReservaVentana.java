package Vista;

import javax.swing.*;

public class ConfirmarReservaVentana extends JDialog {
    public ConfirmarReservaVentana(JFrame parent) {
        super(parent, "Confirmar Reserva", true);
        setSize(400, 250);
        setLocationRelativeTo(parent);
    }

    public static void main(String[] args) {
        JFrame mainFrame = new JFrame();
        mainFrame.setSize(400, 300);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);

        ConfirmarReservaVentana dialogo = new ConfirmarReservaVentana(mainFrame);

        dialogo.setVisible(true);

    }
}
