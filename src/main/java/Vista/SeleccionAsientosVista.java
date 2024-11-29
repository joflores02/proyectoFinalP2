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
        JPanel EstructuraAutobusSubPanel = new JPanel() {
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
                int rectWidth2 = 90; // Ancho del segundo rectángulo
                int rectHeight2 = 12; // Alto del segundo rectángulo

                // Especificar las coordenadas para el segundo rectángulo
                int x2 = 581;  // Coordenada X del segundo rectángulo
                int y2 = 366;   // Coordenada Y del segundo rectángulo


                g.setColor(Color.white); // Establecer el color de relleno
                g.fillRect(x2, y2, rectWidth2, rectHeight2); // Rellenar el rectángulo con el color seleccionado
                // Dibujar el segundo rectángulo con borde de color morado
                g.setColor(new Color(135, 66, 255)); // Establecer el color morado para el borde
                g.drawRect(x2, y2, rectWidth2, rectHeight2);

             //   -------------Rectangulo escaleras--------------------

                int rectWidth3 = 75; // Ancho del segundo rectángulo
                int rectHeight3 = 120; // Alto del segundo rectángulo

                int x3 = 588;
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

    //Asientos frente a puerta---------------------------
                g.setColor(new Color(0xFDED40));

                int asientoWidth = 80;
                int asientoHeight = 60;

                // Generar asientos en filas
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 10; j++) {
                        int xAsiento = 46 + (asientoWidth + 10) * j;
                        int yAsiento = 20 + (asientoHeight + 10) * i;
                        g.fillRect(xAsiento, yAsiento, asientoWidth, asientoHeight);
                    }
                }
                //Asientos lado puerta---------------------------

                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 10; j++) {
                        // Si j == 6, dejamos espacio para las escaleras
                        if (j == 6) {
                            // Saltar espacio entre asientos
                            j += 1;
                        }

                        int xAsiento = 46 + (asientoWidth + 10) * j;
                        int yAsiento = 235 + (asientoHeight + 10) * i;
                        g.fillRect(xAsiento, yAsiento, asientoWidth, asientoHeight);
                    }
                }
            }

        };

        EstructuraAutobusSubPanel.setBackground(Color.white); // Fondo del subpanel
        EstructuraAutobusSubPanel.setBounds(60, 50, 980, 380);

        JPanel Simbologia = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Obtener las dimensiones del subpanel
                int subPanelWidth = getWidth();
                int subPanelHeight = getHeight();

                // Dimensiones del rectángulo
                int rectWidth = 305;
                int rectHeight = 145;

                // Calcular las coordenadas para centrar el rectángulo
                int x = (subPanelWidth - rectWidth) / 2;
                int y = (subPanelHeight - rectHeight) / 2;


                g.setColor(new Color(129, 122, 122)); // Establecer el color púrpura para el borde
                g.drawRect(x, y, rectWidth, rectHeight); // Dibujar el rectángulo centrado

                g.setColor(new Color(0, 0, 0));
                g.setFont(new Font("Arial", Font.BOLD, 14));
                g.drawString("Simbología", 115,22);

                // Dibujar un rectángulo asiento disponible
                g.setColor(new Color(0xA89B0F));
                g.drawRect(15, 30, 50, 30);
                g.setColor(new Color(0xFDED40));
                g.fillRect(16, 31, 49, 29);

                g.setColor(new Color(54, 54, 54));
                g.setFont(new Font("Arial", Font.PLAIN, 14));
                g.drawString("Asiento disponible", 75,51);

                // Dibujar un rectángulo asiento seleccionado
                g.setColor(new Color(0xC27C20));
                g.drawRect(15, 70, 50, 30);
                g.setColor(new Color(0xEF9827));
                g.fillRect(16, 71, 49, 29);

                g.setColor(new Color(54, 54, 54));
                g.setFont(new Font("Arial", Font.PLAIN, 14));
                g.drawString("Asiento seleccionado", 75,91);

                // Dibujar un rectángulo asiento ocupado
                g.setColor(new Color(0x575757));
                g.drawRect(15, 110, 50, 30);
                g.setColor(new Color(0x666769));
                g.fillRect(16, 111, 49, 29);

                g.setColor(new Color(54, 54, 54));
                g.setFont(new Font("Arial", Font.PLAIN, 14));
                g.drawString("Asiento ocupado", 75,131);

            }
        };

        Simbologia.setBackground(new Color(0xFFFFFF));
        Simbologia.setBounds(65, 440, 310, 150);

        JPanel AsientosSeleccionados = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Obtener las dimensiones del subpanel
                int subPanelWidth = getWidth();
                int subPanelHeight = getHeight();

                // Dimensiones del rectángulo
                int rectWidth = 305;
                int rectHeight = 145;

                // Calcular las coordenadas para centrar el rectángulo
                int x = (subPanelWidth - rectWidth) / 2;
                int y = (subPanelHeight - rectHeight) / 2;

                // Dibujar un rectángulo con borde color púrpura que representa al autobús
                g.setColor(new Color(129, 122, 122)); // Establecer el color púrpura para el borde
                g.drawRect(x, y, rectWidth, rectHeight); // Dibujar el rectángulo centrado

            }
        };
        AsientosSeleccionados.setBackground(new Color(0xFFFFFF));
        AsientosSeleccionados.setBounds(395, 440, 310, 150);

        JPanel MontoTotal = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Obtener las dimensiones del subpanel
                int subPanelWidth = getWidth();
                int subPanelHeight = getHeight();

                // Dimensiones del rectángulo
                int rectWidth = 305;
                int rectHeight = 145;

                // Calcular las coordenadas para centrar el rectángulo
                int x = (subPanelWidth - rectWidth) / 2;
                int y = (subPanelHeight - rectHeight) / 2;

                // Dibujar un rectángulo con borde color púrpura que representa al autobús
                g.setColor(new Color(129, 122, 122)); // Establecer el color púrpura para el borde
                g.drawRect(x, y, rectWidth, rectHeight); // Dibujar el rectángulo centrado
            }
        };
        MontoTotal.setBackground(new Color(0xFFFFFF));
        MontoTotal.setBounds(725, 440, 310, 150);

        // Agregar el subpanel al panel principal
        panelPrincipal.add(EstructuraAutobusSubPanel);
        panelPrincipal.add(Simbologia);
        panelPrincipal.add(AsientosSeleccionados);
        panelPrincipal.add(MontoTotal);

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
