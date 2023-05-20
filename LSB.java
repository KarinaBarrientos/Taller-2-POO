import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class LSB {

    BufferedImage imagen;

    public LSB(BufferedImage imagen) {
        this.imagen = imagen;
    }

    public BufferedImage OperacionLSB() {

        for (int y = 0; y < imagen.getHeight(); y++) {
            for (int x = 0; x < imagen.getWidth(); x++) {

                int rgb = imagen.getRGB(x, y);

                int LSBalpha = ((rgb >> 24) & 254);
                int LSBrojo = ((rgb >> 16) & 254);
                int LSBverde = ((rgb >> 8) & 254);
                int LSBazul = (rgb & 254);

                rgb = (LSBalpha << 24 | LSBrojo << 16) | (LSBverde << 8) | LSBazul;
                imagen.setRGB(x, y, rgb);
            }
        }
        return imagen;
    }
}
