package Vista;

import javax.swing.*;
import java.awt.*;

public class SeleccionAsientosVista extends JFrame {

    public SeleccionAsientosVista() {
        // Configuración básica de la ventana
        setTitle("Seleccion de Asientos");
        setSize(1120, 652);  // Tamaño de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cerrar cuando se presione la X
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla

        //Se crea un panel principal que cubre toda la ventana
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setBackground(new Color(0xF2F2F6));
        panelPrincipal.setLayout(null);

        // Crear el primer subpanel con medidas 980x380
        JPanel subPanel = new JPanel() {
            // Sobrescribir el método paintComponent para dibujar en el subpanel
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Obtener las dimensiones del subpanel
                int subPanelWidth = getWidth();
                int subPanelHeight = getHeight();

                // Dimensiones del rectángulo
                int rectWidth = 970;
                int rectHeight = 370;

                // Calcular las coordenadas para centrar el rectángulo
                int x = (subPanelWidth - rectWidth) / 2;
                int y = (subPanelHeight - rectHeight) / 2;

                // Dibujar un rectángulo con borde color púrpura que representa al autobús
                g.setColor(new Color(135, 66, 255)); // Establecer el color púrpura para el borde
                g.drawRect(x, y, rectWidth, rectHeight); // Dibujar el rectángulo centrado

//---------------------------------------------------------------------
                // Rectángulo puerta
                int rectWidth2 = 96; // Ancho del segundo rectángulo
                int rectHeight2 = 12; // Alto del segundo rectángulo

                // Especificar las coordenadas para el segundo rectángulo
                int x2 = 580;  // Coordenada X del segundo rectángulo
                int y2 = 366;   // Coordenada Y del segundo rectángulo


                g.setColor(Color.white); // Establecer el color de relleno
                g.fillRect(x2, y2, rectWidth2, rectHeight2); // Rellenar el rectángulo con el color seleccionado
                // Dibujar el segundo rectángulo con borde de color morado
                g.setColor(new Color(135, 66, 255)); // Establecer el color morado para el borde
                g.drawRect(x2, y2, rectWidth2, rectHeight2);

             //   -------------Rectangulo escaleras--------------------

                int rectWidth3 = 80; // Ancho del segundo rectángulo
                int rectHeight3 = 120; // Alto del segundo rectángulo

                int x3 = 586;
                int y3 = 235;

                g.setColor(Color.white);
                g.fillRect(x3, y3, rectWidth3, rectHeight3);

                g.setColor(new Color(135, 66, 255));
                g.drawRect(x3, y3, rectWidth3, rectHeight3);

                //---------------Lineas que representan el pasillo------------------
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(new Color(135, 66, 255));
                int inicioX = 5;
                int inicioY = 220;
                int finX = 975;
                int finY = 220;

                g2d.drawLine(inicioX, inicioY, finX, finY);
                g2d.drawLine(inicioX, inicioY + 5, finX, finY + 5);
                g2d.drawLine(inicioX, inicioY-60, finX, finY - 60);
                g2d.drawLine(inicioX, inicioY - 55, finX, finY - 55);

            }

        };

        subPanel.setBackground(Color.white); // Fondo del subpanel
        subPanel.setBounds(60, 50, 980, 380);

        // Agregar el subpanel al panel principal
        panelPrincipal.add(subPanel);

        // Agregar el panel principal al JFrame
        add(panelPrincipal);
    }

    public static void main(String[] args) {
        // Crear la ventana y hacerla visible
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                SeleccionAsientosVista ventana = new SeleccionAsientosVista();
                ventana.setVisible(true);
            }
        });
    }
}
