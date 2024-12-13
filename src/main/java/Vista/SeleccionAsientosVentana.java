package Vista;

import Modelo.Asiento;
import Modelo.Autobus;
import Modelo.CategoriaAsiento;
import Modelo.Horario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Ventana que permite a los usuarios seleccionar asientos en un autobús de acuerdo con el piso elegido.
 * La clase maneja la interacción con los asientos, incluyendo la selección y reserva, y ofrece la opción de cambiar de piso.
 * Los asientos reservados se gestionan utilizando un conjunto estático de asientos reservados.
 */
public class SeleccionAsientosVentana extends JFrame {
    private final int TAMANO_ASIENTO = 50;
    private JPanel panelAsientos;
    private JButton btnConfirmar;
    private JButton btnCambiarPiso;
    private int pisoActual;  // Variable para controlar el piso actual
    private AutobusRenderer renderer = new AutobusRenderer();
    private Autobus autobus;

    /**
     * Conjunto de asientos reservados, accesible estáticamente.
     */
    public static Set<Asiento> asientosReservados = new HashSet<>();

    /**
     * Constructor que inicializa la ventana para la selección de asientos.
     * @param autobus Autobús cuya configuración de asientos se va a mostrar.
     */
    public SeleccionAsientosVentana(Autobus autobus) {
        this.autobus = autobus;
        this.pisoActual = 1;  // Empezamos en el primer piso

        // Configuración de la ventana
        setTitle("Selección de Asientos");
        setSize(1120, 652);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setBackground(new Color(0xF2F2F6));
        panelPrincipal.setLayout(null);

        // Subpanel para el diseño del autobús
        panelAsientos = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Seleccionar la lista de asientos según el piso actual
                List<Asiento> asientosActuales = (pisoActual == 1) ? autobus.getPrimerPiso() : autobus.getSegundoPiso();

                // Dibujar los asientos utilizando el método rediseñado
                renderer.dibujarAsientos(g, asientosActuales, TAMANO_ASIENTO);
            }
        };
        panelAsientos.setBackground(Color.WHITE);
        panelAsientos.setBounds(60, 50, 980, 380);

        // MouseListener para manejar click en asientos
        panelAsientos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                manejarClic(e.getX(), e.getY());
                repaint();  // Forzar el repintado después de manejar el clic
            }
        });

        // Agregar el botón de cambio de piso si el autobús tiene dos pisos
        if (autobus.getNumPisos() > 1) {
            btnCambiarPiso = new JButton("Piso " + pisoActual);
            btnCambiarPiso.setBounds(860, 450, 150, 40);
            btnCambiarPiso.addActionListener(e -> cambiarDePiso());
            panelPrincipal.add(btnCambiarPiso);
        }
        // Botón para confirmar la reserva
        btnConfirmar = new JButton("Confirmar Reserva");
        btnConfirmar.setBounds(860, 500, 150, 40);
        btnConfirmar.addActionListener(e -> abrirVentanaConfirmacion());
        panelPrincipal.add(btnConfirmar);

        panelPrincipal.add(panelAsientos);
        add(panelPrincipal);
    }

    /**
     * Cambia el piso actual entre el primero y segundo.
     */
    private void cambiarDePiso() {
        if (pisoActual == 1) {
            pisoActual = 2;
        } else {
            pisoActual = 1;
        }

        // Actualizar el texto del botón
        btnCambiarPiso.setText("Piso " + pisoActual);

        // Redibujar la interfaz para mostrar los asientos del nuevo piso
        repaint();
    }

    /**
     * Clase interna que permite la creación y configuración de asientos con propiedades específicas.
     */
    private class AsientoBuilder {
        private String tipoAsiento;
        private Color colorBase;
        private Color colorSemiCama;
        private Color colorSalonCama;
        private Color colorReservado;

        /**
         * Establece el tipo de asiento (Semi-Cama, Salón-Cama, etc.).
         * @param tipoAsiento Tipo de asiento.
         * @return La instancia actual de AsientoBuilder.
         */
        public AsientoBuilder setTipoAsiento(String tipoAsiento) {
            this.tipoAsiento = tipoAsiento;
            return this;
        }

        /**
         * Establece el color base de los asientos.
         * @param colorBase Color base.
         * @return La instancia actual de AsientoBuilder.
         */
        public AsientoBuilder setColorBase(Color colorBase) {
            this.colorBase = colorBase;
            return this;
        }

        /**
         * Establece el color de los asientos Semi-Cama.
         * @param colorSemiCama Color de asientos Semi-Cama.
         * @return La instancia actual de AsientoBuilder.
         */
        public AsientoBuilder setColorSemiCama(Color colorSemiCama) {
            this.colorSemiCama = colorSemiCama;
            return this;
        }

        /**
         * Establece el color de los asientos Salón-Cama.
         * @param colorSalonCama Color de asientos Salón-Cama.
         * @return La instancia actual de AsientoBuilder.
         */
        public AsientoBuilder setColorSalonCama(Color colorSalonCama) {
            this.colorSalonCama = colorSalonCama;
            return this;
        }

        /**
         * Establece el color de los asientos reservados.
         * @param colorReservado Color de asientos reservados.
         * @return La instancia actual de AsientoBuilder.
         */
        public AsientoBuilder setColorReservado(Color colorReservado) {
            this.colorReservado = colorReservado;
            return this;
        }

        /**
         * Determina el color de un asiento basado en su tipo.
         * @return El color adecuado según el tipo de asiento.
         */
        private Color determinarColor() {
            if ("Semi-Cama".equals(tipoAsiento)) return colorSemiCama;
            if ("Salón-Cama".equals(tipoAsiento)) return colorSalonCama;
            return colorBase;
        }

        /**
         * Mapea el tipo de asiento a la categoría correspondiente.
         * @param tipoAsiento Tipo de asiento como cadena.
         * @return La categoría de asiento correspondiente.
         */
        private CategoriaAsiento obtenerCategoria(String tipoAsiento) {
            switch (tipoAsiento) {
                case "Semi-Cama":
                    return CategoriaAsiento.SEMI_CAMA;
                case "Salón-Cama":
                    return CategoriaAsiento.SALON_CAMA;
                default:
                    throw new IllegalArgumentException("Tipo de asiento desconocido: " + tipoAsiento);
            }
        }
    }

    /**
     * Abre una ventana de confirmación de reserva si se han seleccionado asientos.
     * Muestra un mensaje de error si no se han reservado asientos.
     */
    private void abrirVentanaConfirmacion() {
        if (asientosReservados.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay asientos reservados.", "Error", JOptionPane.WARNING_MESSAGE);
        } else {
            ConfirmarReservaVentana dialogo = new ConfirmarReservaVentana(this);
            dialogo.setVisible(true);
        }
    }

    /**
     * Maneja los clics sobre los asientos en la interfaz gráfica.
     * Cambia el estado de un asiento (ocupado o libre) y actualiza la lista de asientos reservados.
     * @param x Coordenada X del clic.
     * @param y Coordenada Y del clic.
     */
    public void manejarClic(int x, int y) {
        // Obtener la lista de asientos del piso actual
        List<Asiento> asientosActuales = (pisoActual == 1) ? autobus.getPrimerPiso() : autobus.getSegundoPiso();

        // Variables que indican si se ha hecho clic en un asiento
        boolean clicEnAsiento = false;

        for (Asiento asiento : asientosActuales) {
            // Verificar si el clic ocurrió dentro de las coordenadas del asiento
            if (x >= asiento.getX() && x <= asiento.getX() + TAMANO_ASIENTO &&
                    y >= asiento.getY() && y <= asiento.getY() + TAMANO_ASIENTO) {

                clicEnAsiento = true;

                // Cambiar el estado del asiento
                boolean nuevoEstado = !asiento.isOcupado();
                asiento.setOcupado(nuevoEstado);

                // Actualizar el Set de asientos reservados
                if (nuevoEstado) {
                    asientosReservados.add(asiento);
                    System.out.println("Reservado: Asiento " + asiento.getNumero());
                } else {
                    asientosReservados.remove(asiento);
                    System.out.println("Liberado: Asiento " + asiento.getNumero());
                }
                System.out.println(asientosReservados.size() + " asientos reservados.");
            }
        }
    }
}
