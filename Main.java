import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Clase principal que contiene el método de entrada para codificar y decodificar mensajes ocultos en imágenes.
 */
public class Main {

    /**
     * Método principal que maneja la codificación y decodificación de mensajes ocultos en imágenes.
     *
     * @param args Los argumentos de línea de comandos.
     */
    public static void main(String[] args) {

        if (args.length < 2) {
            System.out.println("Se requieren argumentos. Uso: java Main codificar <mensaje> <ruta_imagen_entrada> <ruta_imagen_salida> o java Main decodificar <ruta_imagen>");
            return;
        }

        String command = args[0];

        if (command.equals("codificar")) {
            if (args.length < 4) {
                System.out.println("Se requieren argumentos. Uso: java Main codificar <mensaje> <ruta_imagen_entrada> <ruta_imagen_salida>");
                return;
            }

            String mensaje = args[1];
            String inputImagePath = args[2];
            String outputImagePath = args[3];

            BufferedImage imagen;

            try {
                imagen = ImageIO.read(new File(inputImagePath));
            } catch (IOException e) {
                throw new RuntimeException("Error al leer la imagen de entrada: " + e.getMessage());
            }

            Mensaje mensajeObjeto = new Mensaje(mensaje);
            List<List<Integer>> bits = mensajeObjeto.operacion();

            OcultarMensaje mensajeOculto = new OcultarMensaje(imagen, bits);
            mensajeOculto.operacion(outputImagePath);

        } else if (command.equals("decodificar")) {
            if (args.length < 2) {
                System.out.println("Se requiere un argumento. Uso: java Main decodificar <ruta_imagen>");
                return;
            }

            String inputImagePath = args[1];

            BufferedImage imagen;
            try {
                imagen = ImageIO.read(new File(inputImagePath));

            } catch (IOException e) {
                throw new RuntimeException("Error al leer la imagen: " + e.getMessage());
            }

            LSB lsb = new LSB(imagen);
            String mensajeDecodificado = lsb.OperacionLSB();
            System.out.println("Mensaje decodificado: " + mensajeDecodificado);
        } else {
            System.out.println("Comando no válido. Use 'codificar' o 'decodificar'.");
        }
    }
}
