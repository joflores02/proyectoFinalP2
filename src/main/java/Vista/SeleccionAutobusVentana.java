package Vista;

import javax.swing.*;

public class SeleccionAutobusVentana {

    public SeleccionAutobusVentana() {
        JFrame ventana = new JFrame("Selección de Autobús");
        ventana.setSize(1120, 652);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }

    public static void main(String[] args) {
        new SeleccionAutobusVentana();
    }
}
