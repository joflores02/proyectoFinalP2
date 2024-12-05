package Vista;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.List;

public class SeleccionAutobusVentana extends JPanel {
    private JButton irAtras;

    public SeleccionAutobusVentana() {
        setLayout(null);
        setBackground(Color.WHITE);

        irAtras = new JButton("Ingresar") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Fondo del botón
                if (getModel().isPressed()) {
                    g2.setColor(new Color(150, 150, 255)); // Color al hacer clic
                } else if (getModel().isRollover()) {
                    g2.setColor(new Color(180, 180, 255)); // Color al pasar el mouse
                } else {
                    g2.setColor(new Color(135, 66, 255, 50)); // Color predeterminado
                }
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30); // Fondo redondeado

                // Texto del botón
                String texto = "ATRÁS";
                g2.setColor(new Color(0x3B0193));
                g2.setFont(new Font("Arial", Font.BOLD, 16));
                FontMetrics fm = g2.getFontMetrics();
                int x = (getWidth() - fm.stringWidth(texto)) / 2;
                int y = (getHeight() + fm.getAscent()) / 2 - fm.getDescent() + 2;
                g2.drawString(texto, x, y);
            }
        };

        irAtras.setBounds(25, 20, 100, 30);
        irAtras.setContentAreaFilled(false);
        irAtras.setFocusPainted(false);
        irAtras.setBorderPainted(false);

        // Agregar el botón al panel
        add(irAtras);
    }
    // Getter para el botón "ATRÁS"
    public JButton getIrAtras() {
        return irAtras;
    }

    public void setLugaresInicio(List<String> lugaresInicio) {

    }

    public void setDestinos(List<String> lugaresDestino) {
    }
}
