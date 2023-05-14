import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LSB {
    BufferedImage imagen;

    {
        try {
            imagen = ImageIO.read(new File("download.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (int y = 0; y < imagen.getHeight(); y++) {
            for (int x = 0; x < imagen.getWidth(); x++) {

                int rgb = imagen.getRGB(x, y);

                int rojo = ((rgb >> 16) & 255) & 1;
                int verde = ((rgb >> 8) & 255) & 1;
                int azul = (rgb & 255) & 1;




            }
        }


    }


}
