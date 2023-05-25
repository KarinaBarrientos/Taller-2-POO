
public class Mensaje {
    String mensaje;
    byte[] bytemensaje;
    int filas;
    int columnas;
    int[][] bits;

    Mensaje(String mensaje) {
        this.mensaje = mensaje;
        this.bytemensaje = mensaje.getBytes();
        this.filas = (int) ((mensaje.length() * 8.0) / 4.0);
        this.columnas = 4;
        this.bits = new int[filas][columnas];
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
        System.out.println(bits[0][0]);
        System.out.println(bits[0][1]);
        System.out.println(bits[0][2]);
        System.out.println(bits[0][3]);

        System.out.println(bits[1][0]);
        System.out.println(bits[1][1]);
        System.out.println(bits[1][2]);
        System.out.println(bits[1][3]);


        return bits;
    }
}
