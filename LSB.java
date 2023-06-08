import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LSB {

    private BufferedImage imagenconsecreto;
    private StringBuilder mensaje = new StringBuilder();

    public LSB() {
        try {
            imagenconsecreto = ImageIO.read(new File("imagenconsecreto.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void OperacionLSB() {

        System.out.println("Descifrando mensaje oculto...");

        for (int y = 0; y < imagenconsecreto.getHeight(); y++) {
            for (int x = 0; x < imagenconsecreto.getWidth(); x++) {

                int rgb = imagenconsecreto.getRGB(x, y);

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
    }

    private String binarioAString(String binary) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < binary.length(); i += 8) {
            String byteString = binary.substring(i, i + 8);
            int decimal = Integer.parseInt(byteString, 2);
            char character = (char) decimal;
            sb.append(character);

        }

        return sb.toString();
    }
}
