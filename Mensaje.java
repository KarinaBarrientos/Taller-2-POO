
public class Mensaje {
    String mensaje;
    byte[] bytemensaje;
    int filas;
    int columnas;
    int[][] bits;

    Mensaje(String mensaje) {
        this.mensaje = mensaje;
        this.bytemensaje = mensaje.getBytes();
        this.columnas = (int) ((mensaje.length() * 8.0) / 4.0);
        this.bits = new int[4][columnas];
    }

    int[][] operacion() {
        int fila = 0;
        int columna = 0;

        for (byte b : bytemensaje) {
            for (int i = 7; i >= 0; i--) {

                int bit = (b >> i) & 1;

                System.out.println("bit: " + bit);

                bits[fila][columna] = bit;

                fila++;


                if (fila > 3) {
                    fila = 0;
                    columna++;
                }
            }
        }

        System.out.println("a " + bits[0][0]);
        System.out.println("a " + bits[1][0]);
        System.out.println("a " + bits[2][0]);
        System.out.println("a " + bits[3][0]);

        System.out.println("a " + bits[0][1]);
        System.out.println("a " + bits[1][1]);
        System.out.println("a " + bits[2][1]);
        System.out.println("a " + bits[3][1]);


        return bits;
    }
}
