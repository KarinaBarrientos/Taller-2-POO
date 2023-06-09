import java.awt.image.BufferedImage;

/**
 * Clase que implementa la operación LSB (Least Significant Bit) para extraer un mensaje oculto de una imagen.
 */
public class LSB {

    private BufferedImage imagen;
    private StringBuilder mensaje = new StringBuilder();

    /**
     * Constructor de la clase LSB.
     *
     * @param imagen La imagen de la cual se extraerá el mensaje oculto.
     */
    public LSB(BufferedImage imagen) {
        this.imagen = imagen;
    }

    /**
     * Realiza la operación LSB para extraer el mensaje oculto de la imagen.
     *
     * @return El mensaje oculto extraído de la imagen.
     */
    public String OperacionLSB() {

        System.out.println("Descifrando mensaje oculto...");

        for (int y = 0; y < imagen.getHeight(); y++) {
            for (int x = 0; x < imagen.getWidth(); x++) {

                int rgb = imagen.getRGB(x, y);

                int LSBalpha = (rgb >> 24) & 1;
                int LSBrojo = (rgb >> 16) & 1;
                int LSBverde = (rgb >> 8) & 1;
                int LSBazul = rgb & 1;

                mensaje.append(LSBalpha);
                mensaje.append(LSBrojo);
                mensaje.append(LSBverde);
                mensaje.append(LSBazul);
            }
        }

        String mensajeExtraido = binarioAString(mensaje.toString());
        System.out.println("Mensaje extraído: " + mensajeExtraido);
        return mensajeExtraido;
    }

    /**
     * Convierte una cadena de bits en una cadena de caracteres.
     *
     * @param binario La cadena de bits a convertir.
     * @return La cadena de caracteres resultante.
     */
    private String binarioAString(String binario) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < binario.length(); i += 8) {
            String byteString = binario.substring(i, i + 8);
            int decimal = Integer.parseInt(byteString, 2);
            char character = (char) decimal;
            sb.append(character);

        }

        return sb.toString();
    }
}
