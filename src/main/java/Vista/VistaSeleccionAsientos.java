package Vista;

import Modelo.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.border.Border;

/**
 * Clase que representa la ventana de selección de asientos para un autobús.
 */
 public class VistaSeleccionAsientos extends JFrame {
    private Autobus autobus;
    private GestionReservas gestionReservas;
    private int pisoActual = 0;
    private JTextArea textAreaPrecios;
    private double totalPrecio = 0.0;
    private JLabel labelPisoActual;
    private static final int FILAS = 4;
    private static final int COLUMNAS = 8;

    /**
     * Constructor de la clase SeleccionAsientos
     *
     * Inicializa la ventana de selección de asientos.
     * También se configura el autobús con el que se trabajará para mostrar sus asientos.
     *
     * @param autobus El autobús que se utilizará para la selección de asientos.
     */
    public VistaSeleccionAsientos(Autobus autobus) {
        this.autobus = autobus;

        setTitle("Selección de Asientos");
        setSize(1120, 652);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        gestionReservas = new GestionReservas(autobus.getAsientos());


        JPanel panelAsientos = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                dibujarAsientos(g);

                g.setColor(new Color(0x635FD5));
                g.drawRect(28,37,990,270);

            }

        };
        panelAsientos.setBackground(Color.WHITE);
        panelAsientos.setLayout(null);

        labelPisoActual = new JLabel("Piso Actual: " + (pisoActual + 1));
        labelPisoActual.setBounds(20, 10, 200, 30);
        panelAsientos.add(labelPisoActual);

        panelAsientos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                manejarSeleccionAsiento(e.getX(), e.getY(), panelAsientos);
            }
        });

        JPanel panelPrecios = new JPanel();
        panelPrecios.setLayout(new BoxLayout(panelPrecios, BoxLayout.Y_AXIS));
        textAreaPrecios = new JTextArea(10, 20);
        textAreaPrecios.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textAreaPrecios);
        scrollPane.setBounds(730, 320, 250, 180);
        Border border = BorderFactory.createLineBorder(new Color(0x635FD5), 1);
        scrollPane.setBorder(border);
        panelAsientos.add(scrollPane);

        JPanel Simbologia = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g;

                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int xOffset = 10;
                int yOffset = 10;
                int radio = 15;

                for (int i = 0; i < 4; i++) {
                    if(i == 0){
                        g2d.setColor(Color.YELLOW);
                        g2d.fillOval(xOffset, yOffset, radio * 2, radio * 2);
                        g2d.setColor(Color.BLACK);
                        g2d.drawString("Asiento Semi-Cama", xOffset + radio * 2 + 10, yOffset + radio);
                    }
                    if(i == 1){
                        g2d.setColor(Color.PINK);
                        g2d.fillOval(xOffset, yOffset, radio * 2, radio * 2);
                        g2d.setColor(Color.BLACK);
                        g2d.drawString("Asiento Salón-Cama", xOffset + radio * 2 + 10, yOffset + radio);
                    }
                    if(i == 2){
                        g2d.setColor(Color.BLUE);
                        g2d.fillOval(xOffset, yOffset, radio * 2, radio * 2);
                        g2d.setColor(Color.BLACK);
                        g2d.drawString("Asiento Seleccionado", xOffset + radio * 2 + 10, yOffset + radio);
                    }
                    if(i == 3){
                        g2d.setColor(Color.gray);
                        g2d.fillOval(xOffset, yOffset, radio * 2, radio * 2);
                        g2d.setColor(Color.BLACK);
                        g2d.drawString("Asiento Ocupado", xOffset + radio * 2 + 10, yOffset + radio);
                    }
                    yOffset += 40;
                }
            }
        };

        Simbologia.setBackground(Color.WHITE);
        Simbologia.setBorder(BorderFactory.createLineBorder(new Color(0x635FD5)));
        Simbologia.setBounds(60, 320, 230, 180);
        panelAsientos.add(Simbologia);

        JButton btnReservar = new JButton("Reservar") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                if (getModel().isPressed()) {
                    g2.setColor(new Color(150, 150, 255));
                } else if (getModel().isRollover()) {
                    g2.setColor(new Color(180, 180, 255));
                } else {
                    g2.setColor(new Color(135, 66, 255, 50));
                }
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);

                g2.setColor(new Color(0x3B0193));
                g2.setFont(new Font("Arial", Font.BOLD, 16));
                FontMetrics fm = g2.getFontMetrics();
                int x = (getWidth() - fm.stringWidth("Reservar")) / 2;
                int y = (getHeight() + fm.getAscent()) / 2 - fm.getDescent() + 2;
                g2.drawString("Reservar", x, y);
            }
        };
        btnReservar.setContentAreaFilled(false);
        btnReservar.setFocusPainted(false);
        btnReservar.setBorderPainted(false);
        btnReservar.setBounds(385, 400, 100, 40);
        btnReservar.setPreferredSize(new Dimension(120, 40));
        btnReservar.addActionListener(e -> realizarReserva());
        panelAsientos.add(btnReservar);


        JButton btnCambiarPiso = new JButton("Cambiar Piso") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                if (getModel().isPressed()) {
                    g2.setColor(new Color(150, 150, 255));
                } else if (getModel().isRollover()) {
                    g2.setColor(new Color(180, 180, 255));
                } else {
                    g2.setColor(new Color(135, 66, 255, 50));
                }
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);

                g2.setColor(new Color(0x3B0193));
                g2.setFont(new Font("Arial", Font.BOLD, 16));
                FontMetrics fm = g2.getFontMetrics();
                int x = (getWidth() - fm.stringWidth("Cambiar Piso")) / 2;
                int y = (getHeight() + fm.getAscent()) / 2 - fm.getDescent() + 2;
                g2.drawString("Cambiar Piso", x, y);
            }
        };
        btnCambiarPiso.setContentAreaFilled(false);
        btnCambiarPiso.setFocusPainted(false);
        btnCambiarPiso.setBorderPainted(false);
        btnCambiarPiso.setBounds(510, 400, 120, 40);
        btnCambiarPiso.setPreferredSize(new Dimension(140, 40));
        btnCambiarPiso.addActionListener(e -> {
            cambiarPiso();
            panelAsientos.repaint();
            labelPisoActual.setText("Piso Actual: " + (pisoActual + 1));
        });
        panelAsientos.add(btnCambiarPiso);


        add(panelAsientos);
        setVisible(true);

    }


    /**
     * Este método se encarga de dibujar los asientos en el panel correspondiente
     * basándose en las características del autobús y el piso actual. Cada asiento se representa con un rectángulo, y su color
     * cambia dependiendo de su estado (seleccionado, disponible u ocupado) y su categoría (por ejemplo, salón-cama o semi-cama).
     * Además, se agrega una etiqueta con el número de fila y columna del asiento y el piso en el que se encuentra.
     *
     * @param g El objeto Graphics usado para dibujar los asientos en el panel.
     */
    private void dibujarAsientos(Graphics g) {
        int xInicial = 50;
        int yInicial = 50;
        int ancho = 100;
        int alto = 50;
        int espacioHorizontal = 20;
        int espacioVertical = 15;

        List<Asiento> asientos = autobus.getAsientosPorPiso(pisoActual);

        Color colorAsiento = Color.GREEN; //Color por defecto
        if (autobus.getTipo().equals("Premium")) {
            colorAsiento = Color.PINK; // Asientos Premium serán rosados
        } else if (autobus.getTipo().equals("Económico")) {
            colorAsiento = Color.YELLOW; // Asientos Económicos serán amarillos
        }

        // Dibujar asientos en filas y columnas
        for (Asiento asiento : asientos) {

            int x = xInicial + asiento.getColumna() * (ancho + espacioHorizontal);
            int y = yInicial + asiento.getFila() * (alto + espacioVertical);

            // Cambiar color según selección o disponibilidad
            if (asiento.isSeleccionado()) {
                g.setColor(Color.BLUE); // Seleccionado
            } else if (!asiento.isDisponible()) {
                g.setColor(Color.gray); // Ocupado
            } else {
                // Determinar color del asiento según su tipo
                if (asiento.getCategoriaAsiento() == CategoriaAsiento.SALON_CAMA) {
                    g.setColor(Color.PINK);
                } else if (asiento.getCategoriaAsiento() == CategoriaAsiento.SEMI_CAMA) {
                    g.setColor(Color.YELLOW);
                } else {
                    g.setColor(colorAsiento); // Color por defecto
                }
            }

            // Dibujar el asiento
            g.fillRect(x, y, ancho, alto);
            g.setColor(Color.BLACK);
            g.drawRect(x, y, ancho, alto);

            // Etiqueta del asiento que muestra el piso y la fila y columna en la q se encuentra el asiento
            g.setColor(Color.BLACK);
            if (pisoActual == 0) {
                g.drawString("P1: " + asiento.getFila() + "-" + asiento.getColumna(), x + ancho / 3, y + alto / 2);
            } else {
                g.drawString("P2: " + asiento.getFila() + "-" + asiento.getColumna(), x + ancho / 3, y + alto / 2);
            }
        }
    }

    /**
     * Maneja la selección o deselección de un asiento en el autobús.
     *
     * Si el asiento está disponible, se alterna entre seleccionarlo o deseleccionarlo, actualizando el precio total según corresponda.
     * Si el asiento está ocupado, se muestra un mensaje de error al usuario.
     *
     * @param x La coordenada horizontal del clic en el panel de asientos.
     * @param y La coordenada vertical del clic en el panel de asientos.
     * @param panelAsientos El panel que contiene los asientos, el cual será redibujado después de la selección.
     */
    public void manejarSeleccionAsiento(int x, int y, JPanel panelAsientos) {
        int xInicial = 50;
        int yInicial = 50;
        int ancho = 100;
        int alto = 50;
        int espacioHorizontal = 20;
        int espacioVertical = 15;

        int columna = (x - xInicial) / (ancho + espacioHorizontal);
        int fila = (y - yInicial) / (alto + espacioVertical);

        // Validar si clic dentro de un asiento
        if (columna >= 0 && columna < COLUMNAS && fila >= 0 && fila < FILAS) {
            Asiento asiento = autobus.obtenerAsiento(pisoActual, fila, columna);

            // Si el asiento está disponible, cambiar su estado de selección
            if (asiento.isDisponible()) {
                if (asiento.isSeleccionado()) {
                    asiento.deseleccionar(); // Deseleccionar el asiento
                    totalPrecio -= asiento.getCategoriaAsiento().getPrecio();
                } else {
                    asiento.seleccionar(); // Seleccionar el asiento
                    totalPrecio += asiento.getCategoriaAsiento().getPrecio();
                }
            }

            actualizarPrecios();
            panelAsientos.repaint();

            // Verificar si el asiento está ocupado
            if (!asiento.isDisponible()) {
                JOptionPane.showMessageDialog(this, "Este asiento ya está ocupado. Por favor seleccione otro.",
                        "Asiento Ocupado", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    /**
     * Actualiza el área de texto con los precios de los asientos seleccionados.
     *
     * Este método obtiene los asientos seleccionados en el autobús y recorre cada uno de ellos para mostrar en el área de texto
     * los detalles de cada asiento.
     *
     */
    private void actualizarPrecios() {
        textAreaPrecios.setText("");

        List<Asiento> asientosSeleccionados = autobus.obtenerAsientosSeleccionados();
        for (Asiento asiento : asientosSeleccionados) {
            textAreaPrecios.append(" Asiento " + (pisoActual == 0 ? "P1: " : "P2: ") +
                    asiento.getFila() + "-" + asiento.getColumna() +
                    ": $" + asiento.getCategoriaAsiento().getPrecio() + " CLP\n");
        }

        textAreaPrecios.append("\n Total: $" + totalPrecio + " CLP");
    }

    /**
     * método para la reserva de los asientos seleccionados en el autobús.
     *
     */
    private void realizarReserva() {
        List<Asiento> asientosSeleccionados = autobus.obtenerAsientosSeleccionados();

        if (asientosSeleccionados.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se ha seleccionado ningún asiento.");
        } else {
            for (Asiento asiento : asientosSeleccionados) {
                // Reservar el asiento
                if(pisoActual==0){
                    if (gestionReservas.disponibilidadAsiento(asiento)) {
                        gestionReservas.reservarAsiento(asiento);
                        System.out.println(" Asiento Piso 1: " + asiento.getFila() + "-" + asiento.getColumna() + " reservado.");
                    } else {
                        System.out.println(" Asiento Piso 1: " + asiento.getFila() + "-" + asiento.getColumna() + " ya está ocupado.");
                    }
                }
                else{
                    if (gestionReservas.disponibilidadAsiento(asiento)) {
                        gestionReservas.reservarAsiento(asiento);
                        System.out.println(" Asiento Piso 2: " + asiento.getFila() + "-" + asiento.getColumna() + " reservado.");
                    } else {
                        System.out.println(" Asiento Piso 2: " + asiento.getFila() + "-" + asiento.getColumna() + " ya está ocupado.");
                    }
                }
            }
            repaint();
            // Mostrar confirmación
            JOptionPane.showMessageDialog(this, "Reserva realizada correctamente!");

        }

        textAreaPrecios.setText("");
        totalPrecio = 0.0;

        actualizarPrecios();
    }


    /**
     * Cambia al siguiente piso del autobús si tiene más de un piso.
     *
     *  Si el autobús
     * solo tiene un piso, se muestra un mensaje de advertencia informando que no se puede cambiar de piso.
     *
     * Si el autobús tiene más de un piso, el valor de `pisoActual` se actualiza al siguiente piso; si ya se encuentra
     * en el último piso, se regresa al primer piso.
     */
    public void cambiarPiso() {
        if (autobus.getNumPisos() > 1) {
            pisoActual = (pisoActual + 1) % autobus.getNumPisos();
        } else {
            // Si solo hay un piso, no hacer nada!!
            JOptionPane.showMessageDialog(this, "Este autobús solo tiene un piso.");
        }

    }




    /**
     * Devuelve el piso actual que se está viendo en la interfaz.
     */
    public int getPisoActual(){
        return pisoActual;
    }
    public static void main(String[] args) {
        Horario horario2 = new Horario("02:00 PM", "08:30 PM");
        Autobus autobus1 = AutobusFactory.crearAutobus("A2", "Premium", 2, "Concepción", "Chillán", horario2);

        new VistaSeleccionAsientos(autobus1);
    }
}
