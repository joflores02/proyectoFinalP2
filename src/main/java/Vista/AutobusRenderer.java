package Vista;

import Modelo.Asiento;
import Modelo.Autobus;
import Modelo.CategoriaAsiento;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;


public class AutobusRenderer {
    private Autobus autobus;
    private int pisoActual;
    private final int TAMANO_ASIENTO = 50;
    List<Asiento> asientosPrimerPiso = new ArrayList<>();
    List<Asiento> asientosSegundoPiso = new ArrayList<>();

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
    public void dibujarAsientos(Graphics g, int filas, int columnas, int inicioX, int inicioY, int ancho, int alto, int espacio) {
        // Limpiar solo si es la primera vez que se dibuja
        if ((pisoActual == 1 && asientosPrimerPiso.isEmpty()) ||
                (pisoActual == 2 && asientosSegundoPiso.isEmpty())) {

            int baseNumeroAsiento = (pisoActual == 1) ? 1 : 37;  // Correcto número base de asiento

            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    //CategoriaAsiento categoria = CategoriaAsiento.fromString(tipoAsiento);
                    // Calcular el número de asiento de manera correcta
                    int numeroAsiento = baseNumeroAsiento + j * filas + i;

                    int x = inicioX + (ancho + espacio) * j;
                    int y = inicioY + (alto + espacio) * i;

                    // Ajuste para separación de filas
                    if (i > 1) {
                        y += 65;
                    }
                    Asiento nuevoAsiento = new Asiento(numeroAsiento, SEMI_CAMA, x, y);

                    // Agregar asiento a la lista correspondiente
                    if (pisoActual == 1) {
                        asientosPrimerPiso.add(nuevoAsiento);
                    } else {
                        asientosSegundoPiso.add(nuevoAsiento);
                    }
                }
            }
        }

        // Dibujar asientos de la lista correspondiente
        List<Asiento> asientosActuales = (pisoActual == 1) ? asientosPrimerPiso : asientosSegundoPiso;

        for (Asiento asiento : asientosActuales) {
            // Establecer el color según el estado del asiento
            // g.setColor(asiento.isOcupado() ? Color.BLACK : determinarColor());
            g.fillRect(asiento.getX(), asiento.getY(), TAMANO_ASIENTO, TAMANO_ASIENTO);

            // Dibujar número de asiento
            g.setColor(Color.WHITE);
            String numeroAsientoStr = Integer.toString(asiento.getNumero());
            g.drawString(
                    numeroAsientoStr,
                    asiento.getX() + TAMANO_ASIENTO / 2 - g.getFontMetrics().stringWidth(numeroAsientoStr) / 2,
                    asiento.getY() + TAMANO_ASIENTO / 2 + g.getFontMetrics().getHeight() / 4
            );
        }
    }
}
