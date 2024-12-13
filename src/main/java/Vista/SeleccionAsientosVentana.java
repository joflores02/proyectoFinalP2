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

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import Vista.AutobusRenderer;


public class SeleccionAsientosVentana extends JFrame {
    private final int TAMANO_ASIENTO = 50;
    private JPanel panelAsientos;
    private JButton btnConfirmar;
    private JButton btnCambiarPiso;
    private int pisoActual;  // Variable para controlar el piso actual
    private AutobusRenderer renderer = new AutobusRenderer();
    private Autobus autobus;

    public static Set<Asiento> asientosReservados = new HashSet<>();


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

    // Método que se activa al presionar el botón de cambio de piso
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


    // Clase interna para manejar los asientos
    private class AsientoBuilder {
        private String tipoAsiento;
        private Color colorBase;
        private Color colorSemiCama;
        private Color colorSalonCama;
        private Color colorReservado;

        // Establece el tipo de asiento
        public AsientoBuilder setTipoAsiento(String tipoAsiento) {
            this.tipoAsiento = tipoAsiento;
            return this;
        }

        // Establece el color base de los asientos
        public AsientoBuilder setColorBase(Color colorBase) {
            this.colorBase = colorBase;
            return this;
        }

        // Establece el color de los asientos Semi-Cama
        public AsientoBuilder setColorSemiCama(Color colorSemiCama) {
            this.colorSemiCama = colorSemiCama;
            return this;
        }

        // Establece el color de los asientos Salón-Cama
        public AsientoBuilder setColorSalonCama(Color colorSalonCama) {
            this.colorSalonCama = colorSalonCama;
            return this;
        }
        // Establece el color de los asientos reservados
        public AsientoBuilder setColorReservado(Color colorReservado) {
            this.colorReservado = colorReservado;
            return this;
        }

        // Determina el color del asiento según el tipo seleccionado
        private Color determinarColor() {
            if ("Semi-Cama".equals(tipoAsiento)) return colorSemiCama;
            if ("Salón-Cama".equals(tipoAsiento)) return colorSalonCama;
            // if (asiento.isOcupado()) return colorReservado;
            return colorBase;
        }


        // Método para mapear el tipo de asiento a la enumeración
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
    // Método para abrir la ventana de confirmación
    private void abrirVentanaConfirmacion() {
        if (asientosReservados.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay asientos reservados.", "Error", JOptionPane.WARNING_MESSAGE);
        } else {
            ConfirmarReservaVentana dialogo = new ConfirmarReservaVentana(this);
            dialogo.setVisible(true);
        }
    }

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
                System.out.println(asientosReservados);

                // Forzar repintado
                repaint();
                break; // Salir del bucle después de encontrar el asiento clickeado
            }
        }
        // Si el clic no fue sobre un asiento, se podría manejar de alguna manera, como imprimir un mensaje
        if (!clicEnAsiento) {
            System.out.println("Clic fuera de los asientos.");
        }
    }



    public static void main(String[] args) {
        Horario horario2 = new Horario("02:00 PM", "08:30 PM");
        Autobus autobus1 = Autobus.Factory.crearAutobus("A1", 2, "Concepción", "Santiago", horario2, "Premium"); 

        SwingUtilities.invokeLater(() -> {
            SeleccionAsientosVentana ventana = new SeleccionAsientosVentana(autobus1);
            ventana.setVisible(true);
        });
    }
}