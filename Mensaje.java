
public class Mensaje {
    String mensaje;
    int largo;
    byte[] bytemensaje;
    int filas;
    int columnas;
    int[][] bits;

    Mensaje(String mensaje) {
        this.mensaje = mensaje;
        this.largo = mensaje.length();
        this.bytemensaje = mensaje.getBytes();
        this.bits = new int[(int) Math.ceil(mensaje.length() * 8.0 / 3.0)][3];
        this.filas = this.bits.length;
        this.columnas = 3;
    }

    int[][] operacion() {
        int fila = 0;
        int columna = 0;

        for (byte b : bytemensaje) {
            for (int i = 7; i >= 0; i--) {
                int bit = (b >> i) & 1;

                bits[fila][columna] = bit;
                fila++;

                if (fila == filas) {
                    fila = 0;
                    columna++;
                }
            }
        }
        return bits;
    }
}
