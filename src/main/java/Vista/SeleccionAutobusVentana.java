package Vista;

import Modelo.Autobus;
import Modelo.Recorrido;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import Controlador.SistemaDeReserva;

public class SeleccionAutobusVentana extends JPanel {
    private JButton irAtras;
    private JComboBox<String> lugarPartida;
    private JComboBox<String> destino;

    private String lugarPartidaSeleccionado;
    private String destinoSeleccionado;
    private List<Autobus> autobuses = new ArrayList<>(); // Lista para almacenar los autobuses disponibles
    private List<Autobus> autobusesFiltrados = new ArrayList<>(); // Lista para almacenar los autobuses filtrados


    public SeleccionAutobusVentana() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Panel superior con el botón "Atrás"
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelSuperior.setBackground(Color.WHITE);
        irAtras = new JButton("Atrás") {
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
        irAtras.setContentAreaFilled(false);
        irAtras.setFocusPainted(false);
        irAtras.setBorderPainted(false);
        irAtras.setPreferredSize(new Dimension(100, 30));
        panelSuperior.add(irAtras);

        // Panel central con "Busqueda" y "detallesAutobus"
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
        panelCentral.setBackground(Color.WHITE);

        // Panel "Busqueda"
        JPanel Busqueda = new JPanel();
        Busqueda.setLayout(null); // Usar diseño absoluto
        Busqueda.setBackground(Color.WHITE);

        // Margen general
        int margin = 10;

        // Dimensiones del panel
        int panelWidth = 900; // Cambia esto por el ancho real del panel
        int panelHeight = 200; // Cambia esto por el alto real del panel

        // Dimensiones del rectángulo principal
        int rectWidth = panelWidth - 2 * margin;
        int rectHeight = panelHeight - 2 * margin;

        // Coordenadas del rectángulo principal
        int x = margin + 150;
        int y = margin;

        // Dimensiones y posiciones de los JComboBox internos
        // "Lugar de partida"
        int partidaWidth = (int) (rectWidth * 0.2);
        int partidaHeight = (int) (rectHeight * 0.1);
        int partidaX = x + (int) (rectWidth * 0.05);
        int partidaY = y + (int) (rectHeight * 0.2);

        // "Destino"
        int destinoWidth = partidaWidth;
        int destinoHeight = partidaHeight;
        int destinoX = partidaX + partidaWidth + (int) (rectWidth * 0.05);
        int destinoY = partidaY;

        // "Fecha"
        int fechaWidth = destinoWidth;
        int fechaHeight = destinoHeight;
        int fechaX = destinoX + destinoWidth + (int) (rectWidth * 0.05);
        int fechaY = destinoY;

        // Crear y agregar los JComboBox
        lugarPartida = new JComboBox<>();
        lugarPartida.setBounds(partidaX, partidaY, partidaWidth, partidaHeight);
        Busqueda.add(lugarPartida);

        destino = new JComboBox<>();
        destino.setBounds(destinoX, destinoY, destinoWidth, destinoHeight);
        Busqueda.add(destino);

        JComboBox<String> fecha = new JComboBox<>(new String[] {
                "01/12/2024", "02/12/2024", "03/12/2024"
        });
        fecha.setBounds(fechaX, fechaY, fechaWidth, fechaHeight);
        Busqueda.add(fecha);

        // Agregar los títulos como etiquetas
        JLabel tituloPartida = new JLabel("Lugar de partida");
        tituloPartida.setFont(new Font("Arial", Font.BOLD, 16));
        tituloPartida.setBounds(partidaX, partidaY - 20, partidaWidth, 20);
        Busqueda.add(tituloPartida);

        JLabel tituloDestino = new JLabel("Destino");
        tituloDestino.setFont(new Font("Arial", Font.BOLD, 16));
        tituloDestino.setBounds(destinoX, destinoY - 20, destinoWidth, 20);
        Busqueda.add(tituloDestino);

        JLabel tituloFecha = new JLabel("Fecha");
        tituloFecha.setFont(new Font("Arial", Font.BOLD, 16));
        tituloFecha.setBounds(fechaX, fechaY - 20, fechaWidth, 20);
        Busqueda.add(tituloFecha);

        // Dimensiones y configuración general del panel
        Busqueda.setBounds(0, 0, panelWidth, panelHeight);
        Busqueda.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255)));
        JButton buscar = new JButton("BUSCAR") {
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
                String texto = "BUSCAR";
                g2.setColor(new Color(0x3B0193));
                g2.setFont(new Font("Arial", Font.BOLD, 16));
                FontMetrics fm = g2.getFontMetrics();
                int x = (getWidth() - fm.stringWidth(texto)) / 2;
                int y = (getHeight() + fm.getAscent()) / 2 - fm.getDescent() + 2;
                g2.drawString(texto, x, y);

            }
        };

        buscar.setContentAreaFilled(false);
        buscar.setFocusPainted(false);
        buscar.setBorderPainted(false);
        buscar.setPreferredSize(new Dimension(100, 30));
        buscar.setBounds(Busqueda.getWidth() - 20, 10, 100, 30); // Ubicación y tamaño del botón

        // Agregar el botón al panel "Busqueda"
        Busqueda.setLayout(null);
        Busqueda.add(buscar);

        Busqueda.setPreferredSize(new Dimension(1040, 150));
        Busqueda.setBackground(new Color(0xFFFFFF));

        // Panel "detallesAutobus"
        JPanel detallesAutobus = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Dimensiones del rectángulo
                int rectWidth = getWidth() - 20;
                int rectHeight = getHeight();

                // Calcular las coordenadas para centrar el rectángulo
                int x = 10;
                int y = 10;

                g.setColor(new Color(129, 122, 122));
                g.drawRect(x, y, rectWidth, rectHeight - 50); // Dibujar el rectángulo centrado
            }
        };
        detallesAutobus.setPreferredSize(new Dimension(1040, 500));
        detallesAutobus.setBackground(new Color(0xFFFFFF));

        // Agregar scroll bar a detallesAutobus
        JScrollPane scroll = new JScrollPane(detallesAutobus);
        scroll.setPreferredSize(new Dimension(1040, 500));

        panelCentral.add(Busqueda);
        panelCentral.add(scroll);

        // Agregar el panel central al panel principal
        add(panelSuperior, BorderLayout.NORTH);
        add(panelCentral, BorderLayout.CENTER);
    }

    public String obtenerLugarPartidaSeleccionado() {
        return (String) lugarPartida.getSelectedItem();
    }

    public String obtenerDestinoSeleccionado() {
        return (String) destino.getSelectedItem();
    }


    // Método para establecer los lugares de inicio
    public void setLugaresInicio(List<String> lugaresInicio) {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(lugaresInicio.toArray(new String[0]));
        lugarPartida.setModel(model);
    }

    // Método para establecer los destinos
    public void setDestinos(List<String> destinos) {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(destinos.toArray(new String[0]));
        destino.setModel(model);
    }

    public JButton getIrAtras() {
        return irAtras;
    }
}
