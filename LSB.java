import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LSB {

    BufferedImage imagen;

    {
        try {
            imagen = ImageIO.read(new File("imagenconsecreto.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public LSB() {
        this.imagen = imagen;
    }

    public BufferedImage OperacionLSB() {

        for (int y = 0; y < 1; y++) {
            for (int x = 0; x < 1; x++) {

                int rgb = imagen.getRGB(x, y);


                int LSBalpha = ((rgb >> 24) & 254);
                int LSBrojo = ((rgb >> 16) & 254);
                int LSBverde = ((rgb >> 8) & 254);
                int LSBazul = (rgb & 254);




            }
        }
        return imagen;
    }
}
