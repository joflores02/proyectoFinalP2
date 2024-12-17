package Modelo;

/**
 * Clase encargada de crear instancias de la clase Autobus según los parámetros especificados.
 */
public class AutobusFactory {

    /**
     * Crea un autobús basado en los parámetros proporcionados.
     *
     * @param id El identificador del autobús.
     * @param categoria La categoría del autobús.
     * @param numPisos El número de pisos del autobús.
     * @param lugarDeInicio El lugar de inicio del autobús.
     * @param lugarDeDestino El lugar de destino del autobús.
     * @param horario El horario del autobús.
     * @return Una instancia de la clase Autobus con los parámetros especificados.
     * @throws IllegalArgumentException Si la categoría o el número de pisos no son válidos.
     */
    public static Autobus crearAutobus(String id, String categoria, int numPisos,
                                       String lugarDeInicio, String lugarDeDestino,
                                       Horario horario) {
        if (numPisos == 1) {
            if (categoria.equals("Premium")) {
                return new Autobus(id, 32, "Premium", 1, lugarDeInicio, lugarDeDestino, horario, 4, 8);
            } else if (categoria.equals("Económico")) {
                return new Autobus(id, 32, "Económico", 1, lugarDeInicio, lugarDeDestino, horario, 4, 8);
            } else {
                throw new IllegalArgumentException("Categoría no soportada para autobuses de 1 piso: " + categoria);
            }
        } else if (numPisos == 2) {
            if (categoria.equals("Económico")) {
                return new Autobus(id, 64, "Económico", 2, lugarDeInicio, lugarDeDestino, horario, 4, 8);
            } else if (categoria.equals("Premium")) {
                return new Autobus(id, 64, "Premium", 2, lugarDeInicio, lugarDeDestino, horario, 4, 8);
            } else {
                throw new IllegalArgumentException("Categoría no soportada para autobuses de 2 pisos: " + categoria);
            }
        } else {
            throw new IllegalArgumentException("Número de pisos no soportado: " + numPisos);
        }
    }
}
