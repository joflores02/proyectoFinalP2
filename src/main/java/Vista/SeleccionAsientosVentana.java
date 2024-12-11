package Vista;

import Modelo.Asiento;
import Modelo.Autobus;
import Modelo.CategoriaAsiento;
import Modelo.Horario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

public class SeleccionAsientosVentana extends JFrame {
    private final int TAMANO_ASIENTO = 50;  
    private Autobus autobus;
    private JPanel panelAsientos;
    private JButton btnCambiarPiso;
    private int pisoActual;  // Variable para controlar el piso actual
    List<Asiento> asientosPrimerPiso = new ArrayList<>();
    List<Asiento> asientosSegundoPiso = new ArrayList<>();
    private boolean mouseListenerRegistrado = false;

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

                // Dibujar la estructura del autobús
                dibujarEstructuraAutobus(g);

                // Generar los asientos con el Builder
                AsientoBuilder builder = new AsientoBuilder()
                        .setTipoAsiento(autobus.getTipoAsiento())
                        .setColorBase(Color.WHITE)
                        .setColorSemiCama(Color.YELLOW)
                        .setColorSalonCama(Color.PINK);

                // Dibujar asientos según el piso actual
                if (pisoActual == 1) {
                    builder.dibujarAsientos(g, 4, 9, 46, 20, 80, 60, 10);

                } else {
                    builder.dibujarAsientos(g, 4, 9, 46, 20, 80, 60, 10);  

                }

                //listener para q aparezca solo una vez
                if (!mouseListenerRegistrado) {
                    panelAsientos.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            manejarClic(e.getX(), e.getY());
                            repaint();
                            System.out.println("Clic detectado en posición: (" + e.getX() + ", " + e.getY() + ")");
                        }
                    });
                    mouseListenerRegistrado = true; // Evitar que se registre nuevamente
                }

            }
        };

        panelAsientos.setBackground(Color.WHITE);
        panelAsientos.setBounds(60, 50, 980, 380);

        // Agregar el botón de cambio de piso si el autobús tiene dos pisos
        if (autobus.getNumPisos() > 1) {
            btnCambiarPiso = new JButton("Piso " + pisoActual);
            btnCambiarPiso.setBounds(860, 450, 150, 40);
            btnCambiarPiso.addActionListener(e -> cambiarDePiso());
            panelPrincipal.add(btnCambiarPiso);
        }

        panelPrincipal.add(panelAsientos);
        add(panelPrincipal);
    }

    private void dibujarEstructuraAutobus(Graphics g) {
        int subPanelWidth = 980;
        int subPanelHeight = 380;

        int x = (subPanelWidth - 970) / 2;
        int y = (subPanelHeight - 370) / 2;


        // Dibujar segundo piso si el autobús tiene dos pisos
        if (pisoActual == 1) {
            g.setColor(new Color(135, 66, 255));
            g.drawRect(x, y, 970, 370);

            // Dibujar puerta
            g.setColor(Color.WHITE);
            g.fillRect(581, 366, 90, 12);
            g.setColor(new Color(135, 66, 255));
            g.drawRect(581, 366, 90, 12);

            // Dibujar escaleras
            g.setColor(Color.WHITE);
            g.fillRect(588, 235, 75, 120);
            g.setColor(new Color(135, 66, 255));
            g.drawRect(588, 235, 75, 120);

            // Letras "Escalera"
            // Crear Graphics2D para aplicar rotación
            Graphics2D g2d = (Graphics2D) g;
            AffineTransform originalTransform = g2d.getTransform();  // Guardar la transformación original

            Font font = new Font("Arial", Font.BOLD, 14); 
            g2d.setFont(font);

            // Aplicar rotación de 45 grados
            // Ajustamos las coordenadas para que el texto quede centrado sobre la escalera
            AffineTransform transform = new AffineTransform();
            transform.rotate(Math.toRadians(45), 588 + 37.5, 235 + 60);  
            g2d.setTransform(transform);

            // Dibujar texto en diagonal
            g.setColor(new Color(135, 66, 255));
            g2d.drawString("Escaleras", 675, 235 + 60);  // Ajustar las coordenadas para que el texto esté sobre la escalera

            // Restaurar la transformación original
            g2d.setTransform(originalTransform);

            if(autobus.getNumPisos()==2){
                // Dibujar escaleras
                g.setColor(Color.WHITE);
                g.fillRect(870, 160, 90, 60);
                g.setColor(new Color(135, 66, 255));
                g.drawRect(870, 160, 90, 60);

                // Añadir texto dentro del recuadro de la escalera
                g.setColor(new Color(135, 66, 255));
                Font font2 = new Font("Arial", Font.BOLD, 12);
                g.setFont(font2);

                // Dibujar texto "Escalera 2do piso" dentro del recuadro
                g.drawString("Escaleras", 885, 185);
                g.drawString("segundo piso", 880, 200);
            }

        }

        // Dibujar segundo piso si el autobús tiene dos pisos
        if (pisoActual == 2) {
            // Dibuja la estructura del segundo piso
            g.setColor(new Color(135, 66, 255));
            g.drawRect(x, y, 970, 370);


            // Dibujar escaleras
            g.setColor(Color.WHITE);
            g.fillRect(870, 160, 90, 60);
            g.setColor(new Color(135, 66, 255));
            g.drawRect(870, 160, 90, 60);

            // Añadir texto dentro del recuadro de la escalera
            g.setColor(new Color(135, 66, 255));
            Font font2 = new Font("Arial", Font.BOLD, 12);
            g.setFont(font2);

            // Dibujar texto "Escalera 2do piso" dentro del recuadro
            g.drawString("Escaleras", 885, 185);
            g.drawString("segundo piso", 880, 200);
        }
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

        // Determina el color del asiento según el tipo seleccionado
        private Color determinarColor() {
            if ("Semi-Cama".equals(tipoAsiento)) return colorSemiCama;
            if ("Salón-Cama".equals(tipoAsiento)) return colorSalonCama;
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


        // Dibuja los asientos en la interfaz gráfica con números de manera continua
        public void dibujarAsientos(Graphics g, int filas, int columnas, int inicioX, int inicioY, int ancho, int alto, int espacio) {
            Color color = determinarColor();  // Determina el color del asiento
            int numeroAsiento;  // Variable para el número de asiento
            int baseNumeroAsiento = (pisoActual == 1) ? 1 : (36 + 1);  // Establece un número base dependiendo del piso

            Font font = new Font("Arial", Font.BOLD, 14);  
            g.setFont(font);

            // Iteración para las filas
            for (int i = 0; i < filas; i++) {
                // Iteración para las columnas
                for (int j = 0; j < columnas; j++) {
                    // El número del asiento sigue el patrón de columna + (fila * número de columnas)
                    numeroAsiento = baseNumeroAsiento + (j * filas + i);  // Incremento basado en el piso

                    // Calcula las coordenadas (x, y) del asiento
                    int x = inicioX + (ancho + espacio) * j;
                    int y = inicioY + (alto + espacio) * i;


                    // Dibuja el asiento
                    g.setColor(color);  // Establece el color del asiento
                    g.fillRect(x, y, ancho, alto);  // Dibuja el asiento

                    // Dibuja el número dentro del asiento
                    g.setColor(Color.BLACK);  // Color del número
                    String numeroAsientoStr = Integer.toString(numeroAsiento);  // Convierte el número a String
                    g.drawString(numeroAsientoStr, x + ancho / 2 - g.getFontMetrics().stringWidth(numeroAsientoStr) / 2, y + alto / 2 + g.getFontMetrics().getHeight() / 4);

                    if (pisoActual == 1) {
                        // Agrega el asiento al primer piso
                        asientosPrimerPiso.add(new Asiento(
                                numeroAsiento,
                                obtenerCategoria(tipoAsiento), // Usamos el método para determinar la categoría
                                x,
                                y
                        ));
                    } else {
                        // Agrega el asiento al segundo piso
                        asientosSegundoPiso.add(new Asiento(
                                numeroAsiento,
                                obtenerCategoria(tipoAsiento), // Usamos el método para determinar la categoría
                                x,
                                y
                        ));
                    }
                }
                if(i == 1){
                    inicioY = inicioY + 65;
                }

            }
        }

    }

    private void manejarClic(int x, int y) {
        // Verificar clic en asientos del primer piso
        verificarClicEnAsientos(asientosPrimerPiso, x, y);

        // Verificar clic en asientos del segundo piso
        verificarClicEnAsientos(asientosSegundoPiso, x, y);
    }

    private void verificarClicEnAsientos(List<Asiento> asientos, int x, int y) {
        for (Asiento asiento : asientos) {
            // Verifica si el clic está dentro de los límites del asiento
            if (x >= asiento.getX() && x <= asiento.getX() + 80 &&
                    y >= asiento.getY() && y <= asiento.getY() + 60) {

                // mensaje si el asiento está disponible
                if (asiento.isOcupado()) {
                    System.out.println("Asiento seleccionado: " + asiento.getNumero() +
                            " - Categoría: " + asiento.getCategoria());
                } else {
                    // Mensaje de asiento no está disponible
                    System.out.println("El asiento " + asiento.getNumero() + " no está disponible.");
                }
            }
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
