package Vista;

import Modelo.Asiento;
import Modelo.Autobus;
import Modelo.CategoriaAsiento;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

/**
 * La clase AutobusRenderer se encarga de dibujar la representación gráfica de un autobús en la interfaz.
 * Esto incluye la estructura del autobús (como los pisos y escaleras) y los asientos con sus respectivas categorías y estados.
 */
public class AutobusRenderer {
    private Autobus autobus;
    private int pisoActual;
    private final int TAMANO_ASIENTO = 50;

    /**
     * Dibuja la estructura del autobús, incluyendo los pisos y escaleras.
     * @param g El objeto Graphics utilizado para dibujar la interfaz gráfica.
     */
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

    /**
     * Determina el color de un asiento dependiendo de su categoría y si está ocupado.
     * @param asiento El asiento cuyo color debe determinarse.
     * @return El color del asiento según su estado (ocupado o categoría).
     */
    public Color determinarColorAsiento(Asiento asiento) {
        if (asiento.isOcupado()) {
            return Color.BLACK; // Color para asientos ocupados
        }

        // Determinar el color según la categoría
        switch (asiento.getCategoria()) {
            case SEMI_CAMA:
                return Color.YELLOW; // Color para Semi-Cama
            case SALON_CAMA:
                return Color.PINK; // Color para Salón-Cama
            default:
                return Color.WHITE; // Color por defecto
        }
    }

    /**
     * Dibuja los asientos en la interfaz gráfica, asignando colores según su categoría.
     * @param g El objeto Graphics utilizado para dibujar los asientos.
     * @param asientos La lista de asientos a dibujar.
     * @param tamanoAsiento El tamaño de los asientos a dibujar.
     */
    public void dibujarAsientos(Graphics g, List<Asiento> asientos, int tamanoAsiento) {
        for (Asiento asiento : asientos) {
            // Determinar el color del asiento
            g.setColor(determinarColorAsiento(asiento));

            // Dibujar el asiento
            g.fillRect(asiento.getX(), asiento.getY(), TAMANO_ASIENTO, TAMANO_ASIENTO);

            // Dibujar el número del asiento
            g.setColor(Color.WHITE);
            String numeroAsiento = Integer.toString(asiento.getNumero());
            g.drawString(
                    numeroAsiento,
                    asiento.getX() + TAMANO_ASIENTO / 2 - g.getFontMetrics().stringWidth(numeroAsiento) / 2,
                    asiento.getY() + TAMANO_ASIENTO / 2 + g.getFontMetrics().getHeight() / 4
            );
        }
    }
}
