package Vista;

import Controlador.SistemaDeReserva;

import javax.swing.*;
import java.awt.*;

/**
 * VentanaIngresoSistema es un panel personalizado que se utiliza como la pantalla inicial del sistema.
 * Contiene un botón de ingreso y un fondo visual con texto de bienvenida.
 * Esta clase se encarga de dibujar una imagen de fondo, personalizar el diseño del botón de ingreso
 * y mostrar un mensaje de bienvenida al usuario.
 */
public class VentanaIngresoSistema extends JPanel {
    private JButton botonIngresar;
    private Image fondo;


    /**
     * Constructor que inicializa el panel, el botón de ingreso y configura el fondo.
     * Carga una imagen de fondo, define el diseño de la ventana y crea el botón de ingreso
     * con un diseño personalizado.
     */
    public VentanaIngresoSistema() {
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/Autobus-Image.jpg"));
        fondo = imageIcon.getImage();

        setLayout(new GridBagLayout());

        botonIngresar = new JButton("Ingresar") {
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
                    g2.setColor(new Color(100, 149, 237)); // Color predeterminado
                }
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30); // Fondo redondeado

                // Texto del botón
                String texto = "Ingresar";
                g2.setColor(Color.WHITE);
                g2.setFont(new Font("Arial", Font.BOLD, 16));
                FontMetrics fm = g2.getFontMetrics();
                int x = (getWidth() - fm.stringWidth(texto)) / 2;
                int y = (getHeight() + fm.getAscent()) / 2 - fm.getDescent();
                g2.drawString(texto, x, y);

            }
        };

        botonIngresar.setPreferredSize(new Dimension(200, 50));
        botonIngresar.setContentAreaFilled(false);
        botonIngresar.setFocusPainted(false);
        botonIngresar.setBorderPainted(false);

        // Agregar el botón al panel
        add(botonIngresar);
    }

    /**
     * Getter para acceder al botón de ingreso desde el controlador.
     *
     * @return El botón de ingreso
     */
    public JButton getBotonIngresar() {
        return botonIngresar;
    }

    /**
     * Método que dibuja el fondo y el texto de bienvenida en el panel.
     * Se utiliza para personalizar la apariencia de la ventana de ingreso.
     *
     * @param g El objeto Graphics utilizado para pintar el componente
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Dibujar la imagen de fondo
        if (fondo != null) {
            g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
        }
        //Se le agrega un rectangulo encima para hacerla más oscura.
        Graphics2D g2 = (Graphics2D) g;
        Color semiTransparente = new Color(53, 40, 98, 128);
        g2.setColor(semiTransparente);
        g2.fillRect(0, 0, getWidth(), getHeight());

        //Texto bienvenida
        g.setFont(new Font("Arial", Font.BOLD, 46));
        String texto = "¡Bienvenido/a al sistema!";
        FontMetrics fm = g.getFontMetrics();
        int x = (getWidth() - fm.stringWidth(texto)) / 2;
        int y = (getHeight() + fm.getAscent()) / 2 - fm.getDescent();
        g.setColor(new Color(0, 0, 0, 115));
        g.drawString(texto, x + 3, y - 60);
        g.setColor(Color.WHITE);
        g.drawString(texto, x, y - 65);
    }

    /**
     * Método que crea la ventana principal para el ingreso al sistema.
     * Configura las propiedades de la ventana, como el tamaño y el comportamiento de cierre.
     *
     * @return La ventana creada con la configuración adecuada
     */
    public JFrame crearVentana() {
        JFrame ventanaIngreso = new JFrame("Ventana de Ingreso al Sistema");
        ventanaIngreso.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaIngreso.setSize(1120, 652);
        ventanaIngreso.setContentPane(this);
        ventanaIngreso.setLocationRelativeTo(null);
        return ventanaIngreso;
    }


}
