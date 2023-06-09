import java.util.ArrayList;
import java.util.List;

public class Mensaje {
    String mensaje;
    byte[] bytemensaje;
    int columnas;
    List<List<Integer>> bits;

    /**
     * Constructor de la clase Mensaje.
     *
     * @param mensaje El mensaje a ocultar en la imagen.
     */
    Mensaje(String mensaje) {
        this.mensaje = mensaje;
        this.bytemensaje = mensaje.getBytes();
        this.columnas = (int) Math.ceil((mensaje.length() * 8.0) / 4.0);
        this.bits = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            bits.add(new ArrayList<>(columnas));
        }
    }

    /**
     * Realiza la operación de conversión del mensaje en bytes a una lista de bits.
     *
     * @return La lista de bits generada a partir del mensaje.
     */
    List<List<Integer>> operacion() {
        int fila = 0;
        int columna = 0;

        for (byte b : bytemensaje) {
            for (int i = 7; i >= 0; i--) {
                int bit = (b >> i) & 1;
                bits.get(fila).add(bit);

                fila++;

                if (fila > bits.size() - 1) {
                    fila = 0;
                    columna++;
                }
            }
        }

        return bits;
    }
}
