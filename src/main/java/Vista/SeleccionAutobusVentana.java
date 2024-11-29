package Vista;

import javax.swing.*;
import java.awt.*;

public class SeleccionAutobusVentana extends JPanel {

    public SeleccionAutobusVentana() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        JLabel label = new JLabel("Selección de Autobús");
        add(label, BorderLayout.CENTER);
    }
}
