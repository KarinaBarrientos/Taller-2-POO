import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class LSB {

    BufferedImage imagen;
    File imagensalida;

    public LSB(BufferedImage imagen) {
        this.imagen = imagen;
    }

    public void OperacionLSB() {
        int i = 0;

        for (int y = 0; y < imagen.getHeight(); y++) {
            for (int x = 0; x < imagen.getWidth(); x++) {

                int rgb = imagen.getRGB(x, y);

                int LSBrojo = ((rgb >> 16) & 254);
                int LSBverde = ((rgb >> 8) & 254);
                int LSBazul = (rgb & 254);

                rgb = (LSBrojo << 16) | (LSBverde << 8) | LSBazul;
                Color color = Color.getColor(String.valueOf(rgb));
                imagen.setRGB(x, y, rgb);

            }
        }
    }
}
