package Vista;

import javax.swing.*;
import java.awt.*;

public class SeleccionAsientosVista extends JFrame {
    private JButton pagar;
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


        // Crear el primer subpanel con medidas 980x380 el cual representa el diseño del bus
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

        //Recuadro que contiene la simbología de los colores de los asientos.
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


                g.setColor(new Color(129, 122, 122));
                g.drawRect(x, y, rectWidth, rectHeight); // Dibujar el rectángulo centrado

                g.setColor(new Color(0, 0, 0));
                g.setFont(new Font("Arial", Font.BOLD, 14));
                g.drawString("Simbología", 20,24);

                // Dibujar un rectángulo asiento disponible
                g.setColor(new Color(0xA89B0F));
                g.drawRect(15, 32, 48, 30);
                g.setColor(new Color(0xFDED40));
                g.fillRect(16, 33, 47, 29);

                g.setColor(new Color(54, 54, 54));
                g.setFont(new Font("Arial", Font.BOLD, 14));
                g.drawString("Asiento disponible", 75,51);

                // Dibujar un rectángulo asiento seleccionado
                g.setColor(new Color(0xC27C20));
                g.drawRect(15, 70, 48, 30);
                g.setColor(new Color(0xEF9827));
                g.fillRect(16, 71, 47, 29);

                g.setColor(new Color(54, 54, 54));
                g.setFont(new Font("Arial", Font.BOLD, 14));
                g.drawString("Asiento seleccionado", 75,91);

                // Dibujar un rectángulo asiento ocupado
                g.setColor(new Color(0x575757));
                g.drawRect(15, 108, 48, 30);
                g.setColor(new Color(0x666769));
                g.fillRect(16, 109, 47, 29);

                g.setColor(new Color(54, 54, 54));
                g.setFont(new Font("Arial", Font.BOLD, 14));
                g.drawString("Asiento ocupado", 75,131);

            }
        };

        Simbologia.setBackground(new Color(0xFFFFFF));
        Simbologia.setBounds(65, 440, 310, 150);


        //En esta parte deberían aparecer los asientos que una persona tiene seleccionados.
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


                g.setColor(new Color(129, 122, 122));
                g.drawRect(x, y, rectWidth, rectHeight); // Dibujar el rectángulo centrado

                g.setColor(new Color(0, 0, 0));
                g.setFont(new Font("Arial", Font.BOLD, 14));
                g.drawString("Asientos Seleccionados", 20,22);

            }
        };
        AsientosSeleccionados.setBackground(new Color(0xFFFFFF));
        AsientosSeleccionados.setBounds(395, 440, 310, 150);

        // En este recuadro aparece el número del asiento seleccionado junto a su precio
        //y la suma del precio de todos los asientos representando el total
        //Además del botón pagar.
        JPanel MontoTotal = new JPanel() {
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

                g.setColor(new Color(129, 122, 122));
                g.drawRect(x, y, rectWidth, rectHeight); // Dibujar el rectángulo centrado

                g.setColor(new Color(0, 0, 0));
                g.setFont(new Font("Arial", Font.BOLD, 14));
                g.drawString("Monto Total", 20, 22);
            }
        };

// Usamos layout nulo para poder posicionar el botón manualmente
        MontoTotal.setLayout(null);
        MontoTotal.setBackground(new Color(0xFFFFFF));
        MontoTotal.setBounds(725, 440, 310, 150); // Posicionar el panel

// Botón para pagar, luego se selccionarlo se abre la ventana para confirmar la selección de asiento.
        //Cuando se aprieta confirmar se supone que se debe guardar ese asiento y luego cambiar el estado
        //del asiento comprado de "disponible" a "ocupado" y cambia el color del asiento igual...
        JButton pagar = new JButton("PAGAR") {
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
                String texto = "PAGAR";
                g2.setColor(new Color(0x3B0193));
                g2.setFont(new Font("Arial", Font.BOLD, 16));
                FontMetrics fm = g2.getFontMetrics();
                int x = (getWidth() - fm.stringWidth(texto)) / 2;
                int y = (getHeight() + fm.getAscent()) / 2 - fm.getDescent() + 2;
                g2.drawString(texto, x, y);
            }
        };
        pagar.setBorderPainted(false); // Desactiva la pintura del borde
        pagar.setFocusPainted(false);
        pagar.setBounds(MontoTotal.getWidth() - 100, MontoTotal.getHeight() - 40 , 80, 30);  // Ajusta la posición y tamaño del botón dentro del JPanel
        MontoTotal.add(pagar);

        // Al hacer click en el botón se va a abrir la ventana para confirmar la compra
        pagar.addActionListener(e -> {
            JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(MontoTotal);
            ConfirmarReservaVentana confirmarVentana = new ConfirmarReservaVentana(parentFrame);
            confirmarVentana.setVisible(true);  // Mostrar la ventana
        });


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
