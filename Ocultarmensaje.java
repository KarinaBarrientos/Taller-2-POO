import javax.imageio.ImageIO;
import java.awt.*;
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

        for (int y = 0; y < imagen.getHeight(); y++) {
            for (int x = 0; x < imagen.getWidth(); x++) {
                for (int i = 0; i < bits.length; i++) {
                    for (int j = 0; j < bits[i].length; j++) {

                        if (x >= 0 && x < imagen.getWidth() && y >= 0 && y < imagen.getHeight()) {

                            int rgb = imagen.getRGB(x, y);

                            int LSBalpha = ((rgb >> 24 & 254) | bits[i][0]);
                            int LSBrojo = ((rgb >> 16 & 254) | bits[i][1]);
                            int LSBverde = ((rgb >> 8 & 254) | bits[i][2]);
                            int LSBazul = ((rgb & 254) | bits[i][3]);

                            rgb = (LSBalpha << 24 | LSBrojo << 16) | (LSBverde << 8) | LSBazul;

                            imagen.setRGB(x, y, rgb);

                            x++;

                            if (x == imagen.getWidth()) {
                                x = 0;
                                y++;
                            }
                        }
                    }
                }
            }
        }

        File imagenconsecreto = new File("imagenconsecreto.png");
        try {
            ImageIO.write(imagen, "png", imagenconsecreto);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}