import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Ocultarmensaje {
    BufferedImage imagen;
    int[][] bits;

    public Ocultarmensaje(BufferedImage imagen, int[][] bits) {
        this.imagen = imagen;
        this.bits = bits;
    }

    public void operacion() {

        int i = 0;

        int transparencia = imagen.getTransparency();

        if (transparencia == BufferedImage.TRANSLUCENT || transparencia == BufferedImage.BITMASK) {
            System.out.println("La imagen tiene transparencia.");

        } else {

                BufferedImage imagenConAlpha = new BufferedImage(imagen.getWidth(), imagen.getHeight(), BufferedImage.TYPE_INT_ARGB);

                imagenConAlpha.getGraphics().drawImage(imagen, 0, 0, null);

                imagen = imagenConAlpha;

        }

        for (int y = 0; y < imagen.getHeight(); y++) {
            for (int x = 0; x < imagen.getWidth(); ) {
                if (i >= bits[0].length) {
                    break;
                }

                int rgb = imagen.getRGB(x, y);

                int LSBalpha = ((rgb >> 24 & 254));
                int LSBrojo = ((rgb >> 16 & 254));
                int LSBverde = ((rgb >> 8 & 254));
                int LSBazul = ((rgb & 254));

                LSBalpha = LSBalpha | bits[0][i];
                LSBrojo = LSBrojo | bits[1][i];
                LSBverde = LSBverde | bits[2][i];
                LSBazul = LSBazul | bits[3][i];

                int rgbmod = (LSBalpha << 24 | LSBrojo << 16) | (LSBverde << 8) | LSBazul;

                imagen.setRGB(x, y, rgbmod);

                i++;
                x++;

            }
        }

        File archivoSalida = new File("imagenconsecreto.png");
        try {
            ImageIO.write(imagen, "png", archivoSalida);
            System.out.println("Imagen modificada guardada correctamente.");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
